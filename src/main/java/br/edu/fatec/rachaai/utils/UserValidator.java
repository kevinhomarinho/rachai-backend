package br.edu.fatec.rachaai.utils;

import br.edu.fatec.rachaai.enums.StatusCode;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Predicate<String> isValidUsername = username ->
            Pattern.compile("^[A-Za-z0-9_]{3,50}$").matcher(username).matches();

    private static final Predicate<String> isValidRa = ra ->
            Pattern.compile("^\\d{2,13}$").matcher(ra).matches();

    private static final Predicate<String> isValidEmail = email ->
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(email).matches();

    private static final Predicate<String> isValidPassword = password ->
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!1@#$%^&+=])(?=\\S+$).{6,}$").matcher(password).matches();

    private static final Predicate<String> isValidHorarios = horarios ->
            Pattern.compile("^.{3,100}$").matcher(horarios).matches();

    public static StatusError validateUser(String username, String ra, String email, String password) {
        if (!isValidUsername.test(username)) return new StatusError(StatusCode.USERNAME_INVALID);
        if (isValidRa.test(ra)) return new StatusError(StatusCode.RA_INVALID);
        if (isValidEmail.test(email)) return new StatusError(StatusCode.EMAIL_INVALID);
        if (!isValidPassword.test(password)) return new StatusError(StatusCode.PASSWORD_INVALID);
        return null;
    }

    public static StatusError validateUserUpdate(String username, String horarios){
        if (username != null && !isValidUsername.test(username)) return new StatusError(StatusCode.USERNAME_INVALID);
        if (horarios != null && !isValidHorarios.test(horarios)) return new StatusError(StatusCode.HORARIOS_INVALID);
        return null;
    }
}

