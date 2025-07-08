package com.bookstore.models;

import com.bookstore.behaviour.IPurchasePolicy;

public abstract class Book {
  private String ISBN;
  private String title;
  private Double price;
  private Integer publishYear;
  private IPurchasePolicy purchasePolicy;

  public Book(String ISBN, String title, Double price, Integer publishYear, IPurchasePolicy purchasePolicy) {
    this.ISBN = ISBN;
    this.title = title;
    this.price = price;
    this.publishYear = publishYear;
    this.purchasePolicy = purchasePolicy;
  }

  public Double purchaseBook(PurchaseInfo purchaseInfo) {
    return purchasePolicy.purchaseBook(this, purchaseInfo);
  }

  public String getISBN() {
    return ISBN;
  }

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getPublishYear() {
    return publishYear;
  }

  public IPurchasePolicy getPurchasePolicy() {
    return purchasePolicy;
  }

}
