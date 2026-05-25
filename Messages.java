/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poep2;
/**
 *
 * @author ivang
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Random;

public class Messages {
    // Attributes required for each message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor
    public Messages(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Helper method to generate a random 12-digit number as a String
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Ensures the message ID is not more than twelve characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 12;
    }

    // Validation matching the required unit test success/failure outputs
    public String checkRecipientCell() {
        if (this.recipient != null && this.recipient.length() <= 12 && this.recipient.startsWith("+27")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Combines first two numbers of messageID, a colon, messageNumber, a colon, and the first and last words of the message in ALL CAPS.
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2 || this.messageText == null || this.messageText.trim().isEmpty()) {
            return "00:0:EMPTY";
        }

        // Get first two digits of Message ID
        String idPart = this.messageID.substring(0, 2);

        // Split message to isolate the first and last words
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        // Format: ID_PART:MSG_NUM:FIRSTWORDLASTWORD
        return idPart + ":" + this.messageNumber + ":" + firstWord + lastWord;
    }

    // Helper method for checking message character length limit (250)
    public String checkMessageLength() {
        if (this.messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceededBy = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // Formats and displays full message details in the strict required order
    public String printMessages() {
        return "Message ID: " + this.messageID + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipient + "\n" +
               "Message: " + this.messageText;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }

    // Placeholder for required Research JSON component
    public void storeMessage() {
        /* RESEARCH ATTRIBUTION: 
       Code adapted from Oracle Java Documentation on FileWriter and standard JSON structures.
       Reference: https://docs.oracle.com/en/java/javase/
    */
    
    // Define the filename where the messages will save
    String fileName = "messages_archive.json";
    
    // Manually formatting the object variables into a clean JSON string block
    String jsonOutput = "{\n" +
            "  \"messageID\": \"" + this.messageID + "\",\n" +
            "  \"messageNumber\": " + this.messageNumber + ",\n" +
            "  \"recipient\": \"" + this.recipient + "\",\n" +
            "  \"messageText\": \"" + this.messageText + "\",\n" +
            "  \"messageHash\": \"" + this.messageHash + "\"\n" +
            "}\n";

    // Try-with-resources automatically handles opening and closing the file writer safely
    try (FileWriter writer = new FileWriter(fileName, true)) {
        writer.write(jsonOutput);
        System.out.println("System Notification: Message data successfully backed up to " + fileName);
    } catch (IOException e) {
        System.out.println("System Error: Could not write data to JSON log file: " + e.getMessage());
    }
}
    }
