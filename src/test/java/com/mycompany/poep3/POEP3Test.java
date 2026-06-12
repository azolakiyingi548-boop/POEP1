/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poep3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivang
 */
public class POEP3Test {


    @BeforeEach
    public void setUp() {
        // Reset and prime data environments identically before running evaluations
        POEP3.loadMockJSONData();
    }

    @Test
    public void testSentMessagesArrayPopulation() {
        // Test 1: Confirm array contains expected validation patterns
        // Total count should track 2 messages initially based on data definitions
        assertEquals(2, POEP3.returnTotalMessagesSent());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Test 2: Target content string match checks
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expectedLongest, POEP3.findLongestMessage());
    }

    @Test
    public void testSearchMessageID() {
        // Test 3: Locate target developer entry via specific mock parameter "0838884567"
        String result = POEP3.searchMessageID("0838884567");
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testDeleteMessageByHash() {
        // Test 5: Targeting Message 2 (HASH902) verification sequence 
        String expectedDeletionConfirmation = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expectedDeletionConfirmation, POEP3.deleteMessageByHash("HASH902"));
    }
}