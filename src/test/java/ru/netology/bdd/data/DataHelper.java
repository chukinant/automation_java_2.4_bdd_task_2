package ru.netology.bdd.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class AuthInfo {
        String username;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}
