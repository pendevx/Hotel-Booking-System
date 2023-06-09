/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.group5.system;

import com.group5.database.HotelDatabase;
import com.group5.exceptions.AccountNotFoundException;
import com.group5.hotel.Account;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.shared.utils.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author guang
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountManagerTest {
    
    private static HotelDatabase hd;
    
    public AccountManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            FileUtils.forceDelete(new File("hotel_ebd"));
        } catch (IOException ex) {
            Logger.getLogger(HotelBookingDataTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        hd = new HotelDatabase();
    }
    
    @AfterClass
    public static void tearDownClass() {
        hd.closeConnection();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void t1_testCheckUsernameExists() {
        System.out.println("checkUsernameExists");
        String username = "abcdefghijklmnopqrstvuwxyz";
        boolean result = AccountManager.checkUsernameExists(username);
        assertEquals(false, result);
    }

    @Test
    public void t2_testCreateAccount() throws AccountNotFoundException {
        System.out.println("createAccount1");
        String username = "a";
        String password = "a";
        String fname = "a";
        String lname = "a";
        String phone = "a";
        String email = "a";
        
        Account registerResult = AccountManager.createAccount(username, password, fname, lname, phone, email);
        Account loginResult = AccountManager.login(username, password).getAccount();
        
        assertEquals(registerResult, loginResult);
    }
    
    @Test(expected = NullPointerException.class)
    public void t3_testCreateSameAccount() throws AccountNotFoundException {
        System.out.println("createAccount2");
        String username = "a";
        String password = "a";
        String fname = "a";
        String lname = "a";
        String phone = "a";
        String email = "a";
        
        Account registerResult = AccountManager.createAccount(username, password, fname, lname, phone, email);
        Account loginResult = AccountManager.login(username, password).getAccount();
        
        assertEquals(registerResult, loginResult);
    }
    
    @Test
    public void t4_testPresetData() throws AccountNotFoundException {
        Account user = AccountManager.login("user", "user").getAccount();
        Account admin = AccountManager.login("admin","admin").getAccount();
        
        assertEquals(user.username, "user");
        assertEquals(admin.username, "admin");
    }

    @Test
    public void t5_testLogin() throws Exception {
        System.out.println("login");
        Account result = AccountManager.login("a", "a").getAccount();
        assertEquals("a", "a");
    }
    
}
