/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poep1;

import java.util.Scanner;

/**
 *
 * @author Student
 */



import java.util.Scanner;

public class POEP1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("--- Welcome to the Registration System ---");

        // 1. Capture User Details
        System.out.println("Enter First Name: ");
        String fName = input.nextLine();
        
        System.out.println("Enter Last Name: ");
        String lName = input.nextLine();
        
        // Pass names to the Login class for the welcome message later
        login.setUserDetails(fName, lName);

        // 2. Registration Loop
        boolean isRegistered = false;
        while (!isRegistered) {
            System.out.println("Enter Username: ");
            String user = input.nextLine();

            System.out.println("Enter Password: ");
            String pass = input.nextLine();

            System.out.println("Enter Cell Phone Number (e.g., +27...): ");
            String phone = input.nextLine();

            // Use the registerUser method to get the status message
            String regStatus = login.registerUser(user, pass, phone);
            System.out.println(regStatus);

            // If the message indicates success, break the loop
            if (regStatus.equals("Username and password successfully captured.")) {
                isRegistered = true;
            }
        }

        // 3. Login Section
        System.out.println("\n--- Login to your account ---");
        boolean loginSuccess = false;
        
        while (!loginSuccess) {
            System.out.println("Enter Username: ");
            String loginUser = input.nextLine();

            System.out.println("Enter Password: ");
            String loginPass = input.nextLine();

            // Check credentials
            loginSuccess = login.loginUser(loginUser, loginPass);
            
            // Display the appropriate message based on login status
            String loginMessage = login.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);
        }

        input.close();
    }
}