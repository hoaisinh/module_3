package com.example.tekushop.common;

public class Validation {
    private static final String NUMBER_REGEX = "[0-9]+";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isNumber(String number) {
        return number.matches(NUMBER_REGEX);
    }
    public static boolean isEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isPrimaryId(String id) {
        if(id != null&&isNumber(id)) {
            int idInt = Integer.parseInt(id);
            return idInt > 0;
        }else {
            return false;
        }
    }
    public static boolean isCorrectLength(String text,  int min, int max) {
        String varCharRegex = "^.{" + min + "," + max + "}";
        return text.matches(varCharRegex);
    }
    public static boolean isNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }
    public static boolean isUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9_]{5,20}$";
        return username.matches(usernameRegex);
    }
    public static boolean isPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return password.matches(passwordRegex);
    }

}
