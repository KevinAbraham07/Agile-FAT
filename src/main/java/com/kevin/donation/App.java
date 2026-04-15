package com.kevin.donation;

import java.util.ArrayList;
import java.util.List;

public class App {

    private final List<Donation> donations = new ArrayList<>();

    public boolean processDonation(String donorName, double amount, String paymentMethod) {

        if (donorName == null || donorName.trim().length() < 2) {
            throw new IllegalArgumentException("Invalid donor name");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than 0");
        }

        if (paymentMethod == null ||
            !(paymentMethod.equalsIgnoreCase("Credit Card")
              || paymentMethod.equalsIgnoreCase("UPI")
              || paymentMethod.equalsIgnoreCase("PayPal"))) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        Donation donation = new Donation(donorName, amount, paymentMethod);
        donations.add(donation);

        System.out.println("Donation processed successfully: "
                + donorName + " donated ₹" + amount);

        return true;
    }

    public int getDonationCount() {
        return donations.size();
    }

    public double getTotalDonations() {
        return donations.stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }

    public static void main(String[] args) {

        App app = new App();

        try {
            app.processDonation("Kevin", 1000, "UPI");
            app.processDonation("John", 500, "Credit Card");

            System.out.println("Total Donations: ₹" + app.getTotalDonations());
            System.out.println("Donation Count: " + app.getDonationCount());

            // Keeps container alive for Kubernetes Deployment
            Thread.sleep(Long.MAX_VALUE);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class Donation {
    private final String donorName;
    private final double amount;
    private final String paymentMethod;

    public Donation(String donorName, double amount, String paymentMethod) {
        this.donorName = donorName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }
}