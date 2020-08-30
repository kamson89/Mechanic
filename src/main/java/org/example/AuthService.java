package org.example;

public class AuthService {

    private static Long userId;

    public static void setAuthenticated(Long id) {

        userId = id;
    }

    public static Long getAuthentication() {

        return userId;
    }
}
