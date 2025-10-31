package com.example.demo.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addresses;

    public User(int id, String firstName, String lastName, String email, List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addresses = addresses;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public static  List<User> getSampleUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Akshaya", "S", "akshaya@gmail.com",
                Arrays.asList(
                        new Address("32 Leipsiger strase", "Fulda", "12345", "Germany"),
                        new Address("456 Bahnofstrase", "Freiburg", "12345", "Germany")
                )
        ));
        users.add(new User(2, "Chris", "schaer", "chris@yahoo.com",
                Arrays.asList(
                        new Address("43 Stadtschloss", "Frankfurt", "54321", "Germany")
                )
        ));
        return users;
    }


}
