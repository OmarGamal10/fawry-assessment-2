package com.bookstore.behaviour;

import com.bookstore.models.Book;
import com.bookstore.models.PurchaseInfo;
import com.bookstore.service.EmailService;

public class MailablePolicy implements IPurchasePolicy {
  private final EmailService emailService;

  public MailablePolicy(EmailService emailService) {
    this.emailService = emailService;
  }

  public Double purchaseBook(Book book, PurchaseInfo purchaseInfo) {
    if (purchaseInfo.emailAddress() == null || purchaseInfo.emailAddress().isEmpty()) {
      throw new IllegalArgumentException("Email address is required for this purchase.");
    }
    // would validate the email too, responsibiliy of the email service
    emailService.sendEmail(purchaseInfo.emailAddress());
    return book.getPrice();
  }

}
