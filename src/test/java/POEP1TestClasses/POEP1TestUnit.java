/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package POEP1TestClasses;


import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;


import com.mycompany.poep1.Login;

public class POEP1TestUnit {

    Login login = new Login();

    @Test
    public void testUsername() {
    
        assertTrue(login.checkUserName("kyl_1"));
     
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPassword() {
       
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));

        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testPhone() {
      
        assertTrue(login.checkCellPhoneNumber("+27838968976"));

        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}