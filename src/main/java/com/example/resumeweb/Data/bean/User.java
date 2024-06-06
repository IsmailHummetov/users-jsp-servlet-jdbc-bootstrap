package com.example.resumeweb.Data.bean;

import java.sql.Date;

public class User {
    Integer id;
    String firstname;
    String lastname;
    String email;
    String password;
    String phone;
    String ProfileDescription;
    Date BirthDate;
    Country birthPlace;
    Country nationality;
    String address;

    public User(Integer id, String firstname, String lastname, String email, String phone, String profileDescription, Date birthDate, Country birthPlace, Country nationality, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.ProfileDescription = profileDescription;
        this.BirthDate = birthDate;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDescription() {
        return ProfileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.ProfileDescription = profileDescription;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.BirthDate = birthDate;
    }

    public Country getbirthPlace() {
        return birthPlace;
    }

    public void setbirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ProfileDescription='" + ProfileDescription + '\'' +
                ", BirthDate=" + BirthDate +
                ", birthPlace=" + birthPlace.getBirthPlace() +
                ", nationality=" + nationality.getNationality() +
                ", address='" + address + '\'' +
                '}';
    }
}
