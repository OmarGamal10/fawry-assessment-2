package com.bookstore.models;

public record PurchaseInfo(String ISBN, Integer quantity, String emailAddress, String address) {
}
