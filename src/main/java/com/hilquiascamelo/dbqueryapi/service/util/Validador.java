package com.hilquiascamelo.dbqueryapi.service.util;


import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validador {
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        // Verifica os dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : (11 - resto);

        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : (11 - resto);

        return (digitoVerificador1 == Character.getNumericValue(cpf.charAt(9))) &&
               (digitoVerificador2 == Character.getNumericValue(cpf.charAt(10)));
    }

    public static boolean isMobileBrazil(String phoneNumber) {
        String regex = "^(\\+55)?\\s*(\\([1-9][1-9]\\)|[1-9][1-9])\\s*9?[6-9]\\d{3}-?\\d{4}$";
        return Pattern.matches(regex, phoneNumber);
    }

    public static boolean isEmail(CharSequence email) {
        if (email == null) {
            return false;
        }

        String emailStr = email.toString();
        // Expressão regular para validar endereços de e-mail
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, emailStr);
    }

    public static boolean isMatchRegex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
