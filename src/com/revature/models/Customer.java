package com.revature.models;

class Customer {
    String username = new String();
    String password = new String();

    // personal info
    String firstName = new String();
    String lastName = new String();
    int phone; 
    String email = new String();

    // request all fields
    public Customer(String username, String password, String firstName, String lastName, int phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNumber;
        this.email = email;
    }

    // request ONLY username & password
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    void setFirstName(String name) {
        this.firstName = name;
    }

    void setLastName(String name) {
        this.lastName = name;
    }

    void setPhone(int phoneNumber) {
        this.phone = number;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getInfo() {
        String info = new String();

        info = "****** CUSTOMER INFORMATION ******\n"
                + "Username: " + username + "\n"
                + "Password: " + password + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Phone Number: " + phone + "\n\n";

        return info;
    }
}