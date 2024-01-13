package com.model;

public class Restaurant { 
String name ;
String address;
String phoneNumber;
int capacity;


public Restaurant(String name, String address, String phoneNumber, int capacity) {
	this.name = name;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.capacity = capacity;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}

}
