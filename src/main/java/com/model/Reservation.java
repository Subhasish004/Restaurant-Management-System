package com.model;

import java.sql.Date;

public class Reservation {
int id;
Date date;
String time;
int partySize;
String customerName;
String phoneNumber;
int tableId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getPartySize() {
	return partySize;
}
public void setPartySize(int partySize) {
	this.partySize = partySize;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getTableId() {
	return tableId;
}
public void setTableId(int tableId) {
	this.tableId = tableId;
}

}
