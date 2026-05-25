/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poep2;

/**
 *
 * @author ivang
 */
import java.util.Scanner;
import java.util.ArrayList;

public class POEP2 {


    private static int totalMessagesSent = 0;

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
        
        System.out.println("--- Login System ---");
        System.out.println(" Login Successful!\n");

        System.out.println("Welcome to QuickChat.");
        System.out.println("---------------------");

        //Ask how many messages the user wants to send
        System.out.print("Please enter how many messages you wish to enter during this session: ");
        while (!input.hasNextInt()){
            System.out.println("Please enter a valid number: ");
            input.next();
        }
        int maxMessages = input.nextInt();
        input.nextLine();

        ArrayList<Messages> messageList = new ArrayList<>();
        int currentSessionCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n==============================");
            System.out.println("    QUICK CHAT MENU    ");
            System.out.println("==============================");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            
            int choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    if (currentSessionCount >= maxMessages) {
                        System.out.println("You have reached your set limit of " + maxMessages + " messages for this session.");
                        break;
                    }

                    while (currentSessionCount < maxMessages) {
                        System.out.println("\n--- Entering Message " + (currentSessionCount + 1) + " of " + maxMessages + " ---");
                        
                        System.out.print("Enter Recipient Cell Number (e.g., +27...): ");
                        String recipient = input.nextLine();

                        System.out.print("Enter Message text: ");
                        String text = input.nextLine();

                        Messages msg = new Messages(currentSessionCount, recipient, text);

                        System.out.println("\n[Validation Results]");
                        System.out.println(msg.checkMessageLength());
                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message ID generated: <" + msg.getMessageID() + ">");

                        System.out.println("\nWhat would you like to do with this message?");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message to send later");
                        System.out.print("Selection: ");
                        int actionChoice = input.nextInt();
                        input.nextLine();

                        System.out.println(msg.SentMessage(actionChoice));
                        if (actionChoice == 1) {
                            totalMessagesSent++;
                        }
                        
                        messageList.add(msg);
                        currentSessionCount++;

                        System.out.println("\n--- Captured Message Details ---");
                        System.out.println(msg.printMessages());
                        System.out.println("--------------------------------");

                        if (currentSessionCount < maxMessages) {
                            System.out.print("\nDo you want to enter the next message now? (yes/no): ");
                            String next = input.nextLine().trim();
                            
                            //Loop runs only if the text is NOT "yes" AND  NOT "no"
                            while (!next.equalsIgnoreCase("yes") && !next.equalsIgnoreCase("no")){
                                System.out.println("Invalid input.Please choose from the above options.");
                                System.out.println("Do you want to enter the next message now? (yes/no): ");
                                next = input.nextLine().trim(); //This updatesthe variable so the loop can exit!
                            }
                            if (next.equalsIgnoreCase("no")){
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    running = false;
                    System.out.println("\nExiting Application...");
                    System.out.println("Total number of messages successfully sent: " + returnTotalMessagesSent());
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // Method: returnTotalMessagesSent()
    public static int returnTotalMessagesSent() {
        return totalMessagesSent;
    }
}


    

        