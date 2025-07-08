package com.bookstore.models;

import com.bookstore.behaviour.IPurchasePolicy;

public class EBook extends Book {
  private String fileType;

  public EBook(String ISBN, String title, Double price, Integer publishYear, String fileType,
      IPurchasePolicy purchasePolicy) {
    super(ISBN, title, price, publishYear, purchasePolicy);
    this.fileType = fileType;
  }

  public String getFileType() {
    return fileType;
  }

}
