package com.hilquiascamelo.dbqueryapi.security;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

	private static final Logger LOG = Logger.getLogger(JWTUtil.class);
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 *
	 * @param email
	 * @return
	 */
	public String generateToken(String username, String email, String perfil) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);
		claims.put("email", email);
		claims.put("perfil", perfil); // adiciona o perfil ao mapa de reivindicações
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + expiration);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith( SignatureAlgorithm.HS256 , secret.getBytes() )
				.compact();
	}
	//Validating token


	/**
	 *
	 * @param token
	 * @return
	 */
	public boolean tokenValido(String token) {
		try {
			String[] tokenParts = token.split("\\.");
			if (tokenParts.length != 3) {
				throw new IllegalArgumentException("Token inválido: número de partes inválido");
			}
			String claimsString = new String(Base64.getDecoder().decode(tokenParts[1]), StandardCharsets.UTF_8);
			Map<String, Object> claims = new Gson().fromJson(claimsString, new TypeToken<Map<String, Object>>(){}.getType());
			boolean isRevogado = (Boolean) claims.getOrDefault("aspirado", false); // Verifica se o token está revogado
			if (isRevogado) {
				LOG.info("Token revogado !");
				return false; // Retorna false se o token está revogado
			}
			long expiration = ((Number) claims.get("exp")).longValue() * 1000;
			Date expirationDate = new Date(expiration);
			Date now = new Date();
			if (expirationDate.before(now)) { // Verifica se o token está expirado
				LOG.info("Token expirado !");
				return false;
			}
			LOG.info("Token válido !");
			return true;
		} catch (IllegalArgumentException e) {
			LOG.info("Erro ao validar token: " + e.getMessage());
		} catch (Exception e) {
			LOG.info("Erro ao validar token: " + e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		return false;
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			LOG.info("Erro ao obter claims do token: " + e.getMessage());
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return (String) claims.get("email"); // retorna o email do token
		} else {
			LOG.info("Claims é null");
			return null;
		}
	}
}

