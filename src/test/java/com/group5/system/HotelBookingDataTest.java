/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.group5.system;

import com.group5.database.HotelDatabase;
import com.group5.exceptions.MismatchingCredentialsException;
import com.group5.hotel.Account;
import com.group5.hotel.AccountPermission;
import com.group5.hotel.Booking;
import com.group5.hotel.Credential;
import com.group5.hotel.Room;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
public class HotelBookingDataTest {
    
    private static HotelDatabase hd;
    
    public HotelBookingDataTest() {
        
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

    /**
     * Test of book method, of class HotelBookingData.
     */
    @Test
    public void t1_testBook() {
        System.out.println("book");
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(1, 'A'));
        Account user = new Account("user", "user", "user", "user", "user", AccountPermission.USER);
        Booking booking = new Booking("1", new Date(), new Date(), rooms, 100.00, user, user);
        HotelBookingData.book(booking);
        
        Booking match = HotelBookingData.getBookingsWhere(x -> x.bookingID.equals("1")).get(0);
        assertEquals(booking, match);
    }

    /**
     * Test of getUsernameList method, of class HotelBookingData.
     */
    @Test
    public void t2_testGetUsernameList() {
        System.out.println("getUsernameList");
        Set<String> expResult = new HashSet<String>();
        expResult.add("user");
        expResult.add("admin");
        Set<String> result = HotelBookingData.getUsernameList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountsWhere method, of class HotelBookingData.
     */
    @Test
    public void t3_testGetAccountsWhere() {
        System.out.println("getAccountsWhere");
        Account result = HotelBookingData.getAccountsWhere(x -> x.username.equals("admin")).get(0);
        assertEquals("admin", result.username);
    }

    /**
     * Test of getAccountsByUsername method, of class HotelBookingData.
     */
    @Test
    public void t4_testGetAccountsByUsername() {
        System.out.println("getAccountsByUsername");
        List<String> result = HotelBookingData.getAccountsByUsername("user").stream().map(x -> x.username).collect(Collectors.toList());
        assertEquals(true, result.contains("user"));
    }

    /**
     * Test of getBookingsWhere method, of class HotelBookingData.
     */
    @Test
    public void t5_testGetBookingsWhere() {
        System.out.println("getBookingsWhere");
        List<Booking> result = HotelBookingData.getBookingsWhere(x -> x.bookingID.equals("1"));
        assertEquals((Object)100.00, (Object)result.get(0).totalPrice);
    }

    /**
     * Test of getBookingsForAccount method, of class HotelBookingData.
     */
    @Test
    public void t6_testGetBookingsForAccount() {
        System.out.println("getBookingsForAccount");
        Account account = new Account("user", null, null, null, null, null);
        List<String> result = HotelBookingData.getBookingsForAccount(account).stream().map(x -> x.bookingID).collect(Collectors.toList());
        assertEquals(true, result.contains("1"));
    }
    
    /**
     * Test of testRegister method, of class HotelBookingData.
     */
    @Test(expected = MismatchingCredentialsException.class)
    public void t7_testRegisterFailure() throws MismatchingCredentialsException {
        System.out.println("register");
        Credential credentials = new Credential("tester", "tester");
        Account account = new Account("not work", "fTest", "lTest", "phone", "email", AccountPermission.USER);
        HotelBookingData.register(credentials, account);
        List<String> usernames = HotelBookingData.getAccountsByUsername("tester").stream().map(x -> x.username).collect(Collectors.toList());
        assertEquals(false, usernames.contains(account.username));
    }
    
    @Test
    public void t8_testRegisterSuccess() throws MismatchingCredentialsException {
        Credential credentials = new Credential("tester", "tester");
        Account account = new Account("tester", "fTest", "lTest", "phone", "email", AccountPermission.USER);
        HotelBookingData.register(credentials, account);
        List<String> usernames = HotelBookingData.getAccountsByUsername("tester").stream().map(x -> x.username).collect(Collectors.toList());
        assertEquals(true, usernames.contains(account.username));
    }

    /**
     * Test of getBookings method, of class HotelBookingData.
     */
    @Test
    public void t9_testGetBookings() {
        System.out.println("getBookings");
        List<Booking> result = HotelBookingData.getBookings();
        assertEquals(true, result.size() != 0);
    }

    /**
     * Test of getAccounts method, of class HotelBookingData.
     */
    @Test
    public void t9_testGetAccounts() {
        System.out.println("getAccounts");
        List<Account> result = HotelBookingData.getAccounts().stream().filter(x -> x.getAccountType() == AccountPermission.ADMIN).collect(Collectors.toList());
        assertEquals(true, result.size() == 1);
    }

    /**
     * Test of getCredentials method, of class HotelBookingData.
     */
    @Test
    public void t99_testGetCredentials() {
        System.out.println("getCredentials");
        Credential user = new Credential("user", "user");
        Credential admin = new Credential("admin", "admin");
        Credential tester = new Credential("tester", "tester");
        Set<String> result = HotelBookingData.getCredentials().stream().map(x -> x.getPassword()).collect(Collectors.toSet());
        
        assertEquals(true, result.contains(user.getUsername()));
        assertEquals(true, result.contains(admin.getUsername()));
        assertEquals(true, result.contains(tester.getUsername()));
    }
    
}
