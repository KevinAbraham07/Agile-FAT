package com.kevin.donation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void testValidDonation() {
        assertTrue(app.processDonation("Kevin", 1000, "UPI"));
        assertEquals(1, app.getDonationCount());
        assertEquals(1000, app.getTotalDonations());
    }

    @Test
    void testMultipleDonations() {
        app.processDonation("Kevin", 1000, "UPI");
        app.processDonation("John", 500, "Credit Card");

        assertEquals(2, app.getDonationCount());
        assertEquals(1500, app.getTotalDonations());
    }

    @Test
    void testInvalidDonorName() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> app.processDonation("", 1000, "UPI")
        );

        assertTrue(ex.getMessage().contains("Invalid donor name"));
    }

    @Test
    void testInvalidAmount() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> app.processDonation("Kevin", -100, "UPI")
        );

        assertTrue(ex.getMessage().contains("greater than 0"));
    }

    @Test
    void testZeroAmount() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> app.processDonation("Kevin", 0, "UPI")
        );

        assertTrue(ex.getMessage().contains("greater than 0"));
    }

    @Test
    void testInvalidPaymentMethod() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> app.processDonation("Kevin", 1000, "Cash")
        );

        assertTrue(ex.getMessage().contains("Invalid payment method"));
    }
}