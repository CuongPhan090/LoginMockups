package com.example.loginmockups.Model;

public class Authenticator {

    public static boolean isMatch(String username, String password) {
        if ((username.compareTo("tester") == 0) && (password.compareTo("123456") == 0))
            return true;
        return false;
    }
}
