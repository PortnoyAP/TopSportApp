package com.example.topsportapp.clubs;

public class Club {

    private String city;
    private String sportStyle;
    private String clubName;
    private String address;
    private String email;
    private String phoneNumber;


    public Club() {
    }

    public Club(String city, String sportStyle,
                String clubName, String address,
                String email, String phoneNumber) {
        this.city = city;
        this.sportStyle = sportStyle;
        this.clubName = clubName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSportStyle() {
        return sportStyle;
    }

    public void setSportStyle(String sportStyle) {
        this.sportStyle = sportStyle;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
