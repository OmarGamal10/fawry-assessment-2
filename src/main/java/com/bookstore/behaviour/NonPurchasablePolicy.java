package com.bookstore.behaviour;

import com.bookstore.models.Book;
import com.bookstore.models.PurchaseInfo;

public class NonPurchasablePolicy implements IPurchasePolicy {
  public Double purchaseBook(Book book, PurchaseInfo purchaseInfo) {
    // Normally there would not be an option to buy demo/showcase books.
    throw new UnsupportedOperationException("This book cannot be purchased.");
  }

}
