package com.bookstore.behaviour;

import com.bookstore.models.Book;
import com.bookstore.models.PurchaseInfo;

public interface IPurchasePolicy {
  Double purchaseBook(Book book, PurchaseInfo purchaseInfo);
}
