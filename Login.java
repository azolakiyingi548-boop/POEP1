/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poep2;

/**
 *
 * @author ivang
 */
import java.util.regex.Pattern;

public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    // 1. Check Username: contains underscore and <= 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // 2. Check Password Complexity
    public boolean checkPasswordComplexity(String password) {
        boolean hasCap = !password.equals(password.toLowerCase());
        boolean hasNum = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        boolean isLongEnough = password.length() >= 8;

        return hasCap && hasNum && hasSpecial && isLongEnough;
    }

    // 3. Check Cell Phone Number (Regex for International Code)
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Regex for + followed by digits, total length around 11-13
        String regex = "^\\+\\d{1,3}\\d{9}$"; 
        return Pattern.matches(regex, phoneNumber);
    }

    // 4. Register User Messaging
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } 
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // If checks pass, "save" the user (for this task's scope)
        this.registeredUsername = username;
        this.registeredPassword = password;
        return "Username and password successfully captured.";
    }

    // 5. Login User: verify credentials
    public boolean loginUser(String username, String password) {
        return username.equals(this.registeredUsername) && password.equals(this.registeredPassword);
    }

    // 6. Return Login Status Messaging
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Setters for names (to be used in the welcome message)
    public void setUserDetails(String fName, String lName) {
        this.firstName = fName;
        this.lastName = lName;
    }
}