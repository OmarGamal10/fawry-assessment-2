package com.bookstore.models;

import com.bookstore.behaviour.IPurchasePolicy;
import com.bookstore.behaviour.PhysicalProduct;

public class PaperBook extends Book implements PhysicalProduct {

  private Integer stock;

  public PaperBook(String ISBN, String title, Double price, Integer publishYear, Integer stock,
      IPurchasePolicy purchasePolicy) {
    super(ISBN, title, price, publishYear, purchasePolicy);
    this.stock = stock;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public void reduceStock(Integer quantity) {
    if (quantity < 0 || quantity > stock) {
      throw new IllegalArgumentException("Invalid quantity to reduce stock");
    }
    this.stock -= quantity;
  }

}
