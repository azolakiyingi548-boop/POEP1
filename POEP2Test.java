/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.poep2.Messages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivang
 */
public class POEP2Test {
    
    @Test
    public void testMessageLengthSuccess() {
        Messages msg = new Messages(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(255); 
        Messages msg = new Messages(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {
        Messages msg = new Messages(0, "+27718693002", "Valid recipient");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Messages msg = new Messages(1, "08575975889", "Invalid recipient format");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreation() {
        Messages msg = new Messages(0, "+27718693002", "Hi Tonight");

        String hash = msg.createMessageHash();

        assertTrue(hash.contains(":0:HITONIGHT"));
    }
}