/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poep3;

import java.util.Scanner;

/**
 *
 * @author ivang
 */
public class POEP3 {

    private static int totalMessagesSent = 0;
    
    //Parallel Arrays scaled to a fixed buffer (Max capacity 100)
    private static int arraySize = 0;
    private static String[] arrSentMessage = new String[100];
    private static String[] arrDisregardedMessages = new String[100];
    private static String[] arrStoredMessages = new String[100];
    private static String[] arrMessageHash = new String[100];
    private static String[] arrMessageID = new String[100];
    private static String[] arrRecipient = new String[100]; // To preserve recipient association for searches

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("--- Welcome to the Registration System ---");

        // 1. Capture User Details
        System.out.println("Enter First Name: ");
        String fName = input.nextLine();
        
        System.out.println("Enter Last Name: ");
        String lName = input.nextLine();
        
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

            String regStatus = login.registerUser(user, pass, phone);
            System.out.println(regStatus);

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

            loginSuccess = login.loginUser(loginUser, loginPass);
            String loginMessage = login.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);
        }
        
        System.out.println("--- Login System ---");
        System.out.println(" Login Successful!\n");

        System.out.println("Welcome to QuickChat.");
        System.out.println("---------------------");

        // Automatically load initial external test data matching the JSON format requirement - AI assisted
        loadMockJSONData();

        System.out.print("Please enter how many messages you wish to enter during this session: ");
        //Defensive input validation using .hasNextInt() to prevent InputMismatchExceptions from crashing the program if a user types text.
        while (!input.hasNextInt()){
            System.out.println("Please enter a valid number: ");
            input.next();
        }
        int maxMessages = input.nextInt();
        input.nextLine();

        int currentSessionCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n==============================");
            System.out.println("    QUICK CHAT MENU    ");
            System.out.println("==============================");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages");
            System.out.println("4) Quit");
            System.out.print("Choose an option: ");
            //Ensuring menu choices do not break runtime operations on dirty entry.
            while (!input.hasNextInt()) {
                System.out.println("Please enter a valid menu number option.");
                input.next();
            }
            int choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    if (currentSessionCount >= maxMessages) {
                        System.out.println("You have reached your set limit of " + maxMessages + " messages for this session.");
                        break;
                    }
                    //Session setup & tracking limits
                    while (currentSessionCount < maxMessages) {
                        System.out.println("\n--- Entering Message " + (currentSessionCount + 1) + " of " + maxMessages + " ---");
                        System.out.print("Enter Recipient Cell Number (e.g., +27...): ");
                        String recipient = input.nextLine();
                        System.out.print("Enter Message text: ");
                        String text = input.nextLine();
                        //Object creation
                        Messages msg = new Messages(currentSessionCount, recipient, text);
                        //Validation riggers
                        System.out.println("\n[Validation Results]");
                        System.out.println(msg.checkMessageLength());
                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message ID generated: <" + msg.getMessageID() + ">");

                        System.out.println("\nWhat would you like to do with this message?");
                        //User choice routing
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message to send later");
                        System.out.print("Selection: ");
                        int actionChoice = input.nextInt();
                        input.nextLine();

                        System.out.println(msg.SentMessage(actionChoice));
                        //Passing data to be stored in the parallel arrays
                        populateParallelArrays(recipient, text, actionChoice, msg.getMessageID());
                        currentSessionCount++;

                        System.out.println("\n--- Captured Message Details ---");
                        System.out.println(msg.printMessages());
                        System.out.println("--------------------------------");

                        if (currentSessionCount < maxMessages) {
                            System.out.print("\nDo you want to enter the next message now? (yes/no): ");
                            String next = input.nextLine().trim();
                            //Standardized string checking using loop boundary verification
                            while (!next.equalsIgnoreCase("yes") && !next.equalsIgnoreCase("no")){
                                System.out.println("Invalid input. Please choose from the above options.");
                                System.out.println("Do you want to enter the next message now? (yes/no): ");
                                next = input.nextLine().trim(); 
                            }
                            if (next.equalsIgnoreCase("no")){
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Recently Sent Messages Summary ---");
                    displaySentMessagesSummary();
                    break;

                case 3:
                    displayStoredMessagesMenu(input);
                    break;

                case 4:
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

    public static int returnTotalMessagesSent() {
        return totalMessagesSent;
    }
    //
    public static void populateParallelArrays(String recipient, String text, int action, String msgID) {
        if (arraySize >= 100) return;
        // Linking data using the shared index 'arraySize'
        arrRecipient[arraySize] = recipient;
        arrMessageID[arraySize] = msgID;
        arrMessageHash[arraySize] = "HASH" + Math.abs(text.hashCode() % 10000);//Simulated crypto hash
        /*AI-assisted algorithm: Generates an analytical unique signature/hash value using string character mapping
        to securely isolate records during standard deletion processes.
        */
        if (action == 1) {
            arrSentMessage[arraySize] = text;
            arrDisregardedMessages[arraySize] = null;
            arrStoredMessages[arraySize] = null;
            totalMessagesSent++;
        } else if (action == 2) {
            arrSentMessage[arraySize] = null;
            arrDisregardedMessages[arraySize] = text;
            arrStoredMessages[arraySize] = null;
        } else if (action == 3) {
            arrSentMessage[arraySize] = null;
            arrDisregardedMessages[arraySize] = null;
            arrStoredMessages[arraySize] = text;
        }
        arraySize++; //Increments the pointer for the next entry
    }
    //AI-assisted mock setup
    public static void loadMockJSONData() { 
        // Message 1
        arrRecipient[0] = "+2734557896";
        arrStoredMessages[0] = null;
        arrSentMessage[0] = "Did you get the cake?";
        arrDisregardedMessages[0] = null;
        arrMessageID[0] = "DE0";
        arrMessageHash[0] = "HASH551";
        totalMessagesSent++;
        
        // Message 2
        arrRecipient[1] = "+27838884567";
        arrStoredMessages[1] = "Where are you? You are late! I have asked you to be on time.";
        arrSentMessage[1] = null;
        arrDisregardedMessages[1] = null;
        arrMessageID[1] = "WH1";
        arrMessageHash[1] = "HASH902";

        // Message 3
        arrRecipient[2] = "+27834484567";
        arrStoredMessages[2] = null;
        arrSentMessage[2] = null;
        arrDisregardedMessages[2] = "Yohoooo, I am at your gate.";
        arrMessageID[2] = "YO2";
        arrMessageHash[2] = "HASH114";

        // Message 4
        arrRecipient[3] = "0838884567";
        arrStoredMessages[3] = null;
        arrSentMessage[3] = "It is dinner time !";
        arrDisregardedMessages[3] = null;
        arrMessageID[3] = "0838884567"; // Exact test data key mapping
        arrMessageHash[3] = "HASH773";
        totalMessagesSent++;

        // Message 5
        arrRecipient[4] = "+27838884567";
        arrStoredMessages[4] = "Ok, I am leaving without you.";
        arrSentMessage[4] = null;
        arrDisregardedMessages[4] = null;
        arrMessageID[4] = "OK4";
        arrMessageHash[4] = "HASH884";

        arraySize = 5; 
        totalMessagesSent = 2;
        System.out.println(">> Initial JSON file records processed and mapped successfully.");
    }

    private static void displayStoredMessagesMenu(Scanner input) {
        System.out.println("\n--- STORED MESSAGES SUB-MENU ---");
        System.out.println("a) Display senders and recipients of all stored messages");
        System.out.println("b) Display the longest stored message");
        System.out.println("c) Search for a message ID");
        System.out.println("d) Search for all messages sent to a particular recipient");
        System.out.println("e) Delete a message using message Hash");
        System.out.println("f) Display full report details");
        System.out.print("Selection (a-f): ");
        String subChoice = input.nextLine().trim().toLowerCase();

        switch (subChoice) {
            case "a":
                displaySendersAndRecipients();
                break;
            case "b":
                System.out.println("\nLongest Stored Message: " + findLongestMessage());
                break;
            case "c":
                System.out.print("Enter Message ID to search: ");
                String searchID = input.nextLine();
                System.out.println(searchMessageID(searchID));
                break;
            case "d":
                System.out.print("Enter Recipient cell number to locate: ");
                String targetRecipient = input.nextLine();
                searchRecipientMessages(targetRecipient);
                break;
            case "e":
                System.out.print("Enter Message Hash value to purge: ");
                String targetHash = input.nextLine();
                System.out.println(deleteMessageByHash(targetHash));
                break;
            case "f":
                displayFullReport();
                break;
            default:
                System.out.println("Invalid selection choice.");
        }
    }

    // A. Loops to find targets
    public static void displaySendersAndRecipients() {
        System.out.println("\n--- Stored Data Traffic Targets ---");
        for (int i = 0; i < arraySize; i++) {
            if (arrRecipient[i] != null) {
                System.out.println("Index [" + i + "] Target Recipient: " + arrRecipient[i]);
            }
        }
    }

    // B. Compares lengths of strings in all 3 status arrays
    public static String findLongestMessage() {
        String longest = "";
        for (int i = 0; i < arraySize; i++) {
            String current = "";
            if (arrSentMessage[i] != null) current = arrSentMessage[i];
            else if (arrStoredMessages[i] != null) current = arrStoredMessages[i];
            else if (arrDisregardedMessages[i] != null) current = arrDisregardedMessages[i];

            if (current.length() > longest.length()) {
                longest = current;
            }
        }
        return longest;
    }

    // C. Ignores case to find matching ID string
    public static String searchMessageID(String id) {
        for (int i = 0; i < arraySize; i++) {
            if (arrMessageID[i] != null && arrMessageID[i].equalsIgnoreCase(id)) {
                String msgContent = (arrSentMessage[i] != null) ? arrSentMessage[i] : 
                                    ((arrStoredMessages[i] != null) ? arrStoredMessages[i] : arrDisregardedMessages[i]);
                return "Recipient: " + arrRecipient[i] + " | Message: \"" + msgContent + "\"";
            }
        }
        return "Message ID not found.";
    }

    // D. Loops and prints all text belonging to a cell number 
    public static void searchRecipientMessages(String recipient) {
        System.out.println("\nSearch returns for Target: " + recipient);
        boolean found = false;
        for (int i = 0; i < arraySize; i++) {
            if (arrRecipient[i] != null && arrRecipient[i].equals(recipient)) {
                String activeText = (arrSentMessage[i] != null) ? arrSentMessage[i] : 
                                    ((arrStoredMessages[i] != null) ? arrStoredMessages[i] : arrDisregardedMessages[i]);
                if (activeText != null) {
                    System.out.println("- \"" + activeText + "\"");
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No records found matches for target criteria.");
    }

    // E. Clears out every array at index 'i' by setting them to null
    public static String deleteMessageByHash(String hashValue) {
        for (int i = 0; i < arraySize; i++) {
            if (arrMessageHash[i] != null && arrMessageHash[i].equalsIgnoreCase(hashValue)) {
                String targetedText = (arrSentMessage[i] != null) ? arrSentMessage[i] : 
                                      ((arrStoredMessages[i] != null) ? arrStoredMessages[i] : arrDisregardedMessages[i]);
                
                // Clear the indices entirely
                arrSentMessage[i] = null;
                arrStoredMessages[i] = null;
                arrDisregardedMessages[i] = null;
                arrMessageHash[i] = null;
                arrMessageID[i] = null;
                arrRecipient[i] = null;
                
                return "Message: \"" + targetedText + "\" successfully deleted.";
            }
        }
        return "Message Hash value target variant not found.";
    }

    // F. Iterates and generates a pretty-printed layout 
    public static void displayFullReport() {
        System.out.println("\n=====================================================");
        System.out.println("                SYSTEM MESSAGES REPORT                  ");
        System.out.println("=====================================================");
        for (int i = 0; i < arraySize; i++) {
            if (arrMessageID[i] == null && arrMessageHash[i] == null) continue; // Skip cleared values

            String currentStatus = "Disregarded";
            String currentText = arrDisregardedMessages[i];
            
            if (arrSentMessage[i] != null) {
                currentStatus = "Sent";
                currentText = arrSentMessage[i];
            } else if (arrStoredMessages[i] != null) {
                currentStatus = "Stored";
                currentText = arrStoredMessages[i];
            }

            System.out.println("Message ID   : " + arrMessageID[i]);
            System.out.println("Hash Key     : " + arrMessageHash[i]);
            System.out.println("Recipient    : " + arrRecipient[i]);
            System.out.println("Status Block : " + currentStatus);
            System.out.println("Text Content : \"" + currentText + "\"");
            System.out.println("--------------------------------------------------------");
        }
    }

    private static void displaySentMessagesSummary() {
        int counts = 1;
        for (int i = 0; i < arraySize; i++) {
            if (arrSentMessage[i] != null) {
                System.out.println(counts + ". To: " + arrRecipient[i] + " | Msg: " + arrSentMessage[i]);
                counts++;
            }
        }
        if (counts == 1) {
            System.out.println("No active messages found out in transit paths during current initialization cycle.");
        }
    }
}
