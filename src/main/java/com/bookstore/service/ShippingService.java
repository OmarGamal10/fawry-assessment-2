package com.bookstore.service;

public class ShippingService {
  public void ship(String address, String bookTitle) {
    System.out.println("Shipping " + bookTitle + " to " + address);
  }
}
