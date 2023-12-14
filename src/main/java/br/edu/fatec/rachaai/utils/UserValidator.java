package br.edu.fatec.rachaai.utils;

import br.edu.fatec.rachaai.enums.StatusCode;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Predicate<String> isValidUsername = username ->
            Pattern.compile("^[A-Za-z0-9_]{3,50}$").matcher(username).matches();

    private static final Predicate<String> isValidRa = ra ->
            Pattern.compile("^\\d{2,13}$").matcher(ra).matches();

    private static final Predicate<String> isValidPassword = password ->
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$").matcher(password).matches();

    private static final Predicate<String> isValidHorarios = horarios ->
            Pattern.compile("^[A-Za-z0-9:_]{3,100}$").matcher(horarios).matches();

    public static StatusError validateUser(String username, String ra, String password) {
        if (!isValidUsername.test(username)) return new StatusError(StatusCode.USERNAME_INVALID);
        if (!isValidRa.test(ra)) return new StatusError(StatusCode.RA_INVALID);
        if (!isValidPassword.test(password)) return new StatusError(StatusCode.PASSWORD_INVALID);
        return null;
    }
}

