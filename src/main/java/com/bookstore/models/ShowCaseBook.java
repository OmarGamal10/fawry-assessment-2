package com.bookstore.models;

import com.bookstore.behaviour.IPurchasePolicy;

public class ShowCaseBook extends Book {
  public ShowCaseBook(String ISBN, String title, Double price, Integer publishYear, IPurchasePolicy purchasePolicy) {
    super(ISBN, title, price, publishYear, purchasePolicy);
  }
}
