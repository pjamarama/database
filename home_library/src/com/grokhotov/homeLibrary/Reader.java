package com.grokhotov.homeLibrary;

public class Reader {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Reader(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s, тел: %s", firstName, lastName, phoneNumber);
    }
}
