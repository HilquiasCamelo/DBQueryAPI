package com.hilquiascamelo.dbqueryapi.security;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
			String name = (String) claims.get("sub");
			String email = (String) claims.get("email");
			String perfil = (String) claims.get("perfil"); // acessa o perfil do mapa de reivindicações
			long expiration = ((Number) claims.get("exp")).longValue() * 1000; // em milissegundos
			Date expirationDate = new Date(expiration);
			Date now = new Date();
			if (expirationDate.after(now)) {
				System.out.println("Token válido: name=" + name + ", email=" + email + ", perfil=" + perfil);
				return true;
			} else {
				System.out.println("Token expirado: name=" + name + ", email=" + email + ", perfil=" + perfil);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao validar token: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao validar token: " + e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		return false;
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			System.out.println("Erro ao obter claims do token: " + e.getMessage());
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return (String) claims.get("email"); // retorna o email do token
		} else {
			System.out.println("Claims é null");
			return null;
		}
	}
}

