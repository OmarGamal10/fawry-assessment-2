package com.bookstore.behaviour;

import com.bookstore.models.Book;
import com.bookstore.models.PurchaseInfo;
import com.bookstore.service.ShippingService;

public class ShippablePolicy implements IPurchasePolicy {
  ShippingService shippingService;

  public ShippablePolicy(ShippingService shippingService) {
    this.shippingService = shippingService;
  }

  public Double purchaseBook(Book book, PurchaseInfo purchaseInfo) {
    if (purchaseInfo.address() == null || purchaseInfo.address().isEmpty()) {
      throw new IllegalArgumentException("Shipping address is required for shippable books.");
    }
    return book.getPrice() * purchaseInfo.quantity();
  }

}
