package com.bookstore.models;

import java.util.concurrent.ConcurrentHashMap;

import com.bookstore.behaviour.PhysicalProduct;

public class BookStore {
  private ConcurrentHashMap<String, Book> bookInventory; // isbn -> book

  public BookStore() {
    this.bookInventory = new ConcurrentHashMap<String, Book>();
  }

  public void addBook(Book book) {
    if (bookInventory.containsKey(book.getISBN())) {
      throw new IllegalArgumentException("Book with ISBN " + book.getISBN() + " already exists in inventory");
    }
    bookInventory.put(book.getISBN(), book);
  }

  public void removeBooks(Integer year) {
    if (bookInventory == null) {
      return;
    }
    bookInventory.entrySet().forEach(entry -> {
      Integer publishYear = entry.getValue().getPublishYear();
      if (publishYear <= year) {
        System.out.println("Removing book: " + entry.getValue().getTitle() + " (ISBN: " + entry.getKey() + ")");
        bookInventory.remove(entry.getKey());
      }
    });
  }

  public Double purchaseBook(String ISBN, PurchaseInfo purchaseInfo) {
    if (bookInventory == null || bookInventory.isEmpty() || !bookInventory.containsKey(ISBN)) {
      throw new IllegalArgumentException("Book not found in inventory");
    }
    Book book = bookInventory.get(ISBN);
    if (book instanceof PhysicalProduct physicalBook) {
      if (purchaseInfo.quantity() == null || purchaseInfo.quantity() <= 0) {
        throw new IllegalArgumentException("Invalid quantity for purchase");
      }
      if (physicalBook.getStock() < purchaseInfo.quantity()) {
        throw new IllegalArgumentException("Insufficient stock for the book");
      }
    }
    Double totalPrice = book.getPurchasePolicy().purchaseBook(book, purchaseInfo);
    if (book instanceof PhysicalProduct physicalBook) {
      physicalBook.reduceStock(purchaseInfo.quantity());
    }
    logPurchase(book, purchaseInfo, totalPrice);
    return totalPrice;
  }

  private void logPurchase(Book book, PurchaseInfo purchaseInfo, Double totalPrice) {
    System.out.println("Purchased book: " + book.getTitle() + " (ISBN: " + book.getISBN() + ")");
    if (book instanceof PhysicalProduct) {
      System.out.println("Quantity: " + purchaseInfo.quantity());
      System.out.println("Shipping Address: " + purchaseInfo.address());
    }
    if (book instanceof EBook) {
      System.out.println("Email Address: " + purchaseInfo.emailAddress());
    }
    System.out.println("Total Price: " + totalPrice);
  }

}
