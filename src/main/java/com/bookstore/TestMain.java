package com.bookstore;

import com.bookstore.behaviour.MailablePolicy;
import com.bookstore.behaviour.NonPurchasablePolicy;
import com.bookstore.behaviour.ShippablePolicy;
import com.bookstore.models.BookStore;
import com.bookstore.models.EBook;
import com.bookstore.models.PaperBook;
import com.bookstore.models.PurchaseInfo;
import com.bookstore.models.ShowCaseBook;
import com.bookstore.service.EmailService;
import com.bookstore.service.ShippingService;

public class TestMain {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        EmailService emailService = new EmailService();
        BookStore bookStore = new BookStore();
        // populating books
        EBook ebook1 = new EBook("123", "Quantum Physics ebook", 120.0, 2021, "pdf", new MailablePolicy(emailService));
        EBook ebook2 = new EBook("456", "Mechanics ebook", 150.0, 2019, "epub", new MailablePolicy(emailService));
        ShowCaseBook demo = new ShowCaseBook("1234", "demo book", null, 2015, new NonPurchasablePolicy());
        PaperBook paperBook1 = new PaperBook("789", "Cooking handbook", 200.0, 2020, 20,
                new ShippablePolicy(shippingService));
        PaperBook paperBook2 = new PaperBook("781", "Programming handbook", 300.0, 2017, 30,
                new ShippablePolicy(shippingService));
        bookStore.addBook(ebook1);
        bookStore.addBook(ebook2);
        bookStore.addBook(demo);
        bookStore.addBook(paperBook1);
        bookStore.addBook(paperBook2);

        // tests
        // test 1 successful purchase ebook
        System.out.println("Test 1");
        try {
            bookStore.purchaseBook("123", new PurchaseInfo("123", null, "email@gmail.com", null));
        } catch (Exception e) {
            System.out.println("error from test 1 : " + e.getMessage());
        }

        // test 2 successful purchase paper book
        System.out.println("Test 2");
        try {
            bookStore.purchaseBook("789", new PurchaseInfo("789", 2, null, "123 Street, City"));
        } catch (Exception e) {
            System.out.println("error from test 2 : " + e.getMessage());
        }

        // test 3 trying to buy a showcase book
        System.out.println("Test 3");
        try {
            bookStore.purchaseBook("1234", new PurchaseInfo("1234", 1, null, null));
        } catch (Exception e) {
            System.out.println("error from test 3 : " + e.getMessage());
        }

        // test 4 trying to buy a book that does not exist
        System.out.println("Test 4");
        try {
            bookStore.purchaseBook("999", new PurchaseInfo("999", 1, null, null));
        } catch (Exception e) {
            System.out.println("error from test 4 : " + e.getMessage());
        }

        // test 5 trying to buy with quantity more than available
        System.out.println("Test 5");
        try {
            bookStore.purchaseBook("789", new PurchaseInfo("789", 100, null, "123 Street, City"));
        } catch (Exception e) {
            System.out.println("error from test 5 : " + e.getMessage());
        }

        // test 6 trying to buy ebook without email
        System.out.println("Test 6");
        try {
            bookStore.purchaseBook("123", new PurchaseInfo("123", null, null, null));
        } catch (Exception e) {
            System.out.println("error from test 6 : " + e.getMessage());
        }

        // test 7 trying to buy paper book without address
        System.out.println("Test 7");
        try {
            bookStore.purchaseBook("789", new PurchaseInfo("789", 1, null,
                    null));
        } catch (Exception e) {
            System.out.println("error from test 7 : " + e.getMessage());
        }

        // test 8 trying to add a book with existing ISBN
        System.out.println("Test 8");
        try {
            bookStore.addBook(new EBook("123", "Duplicate ISBN ebook", 100.0, 2022, "pdf",
                    new MailablePolicy(emailService)));
        } catch (Exception e) {
            System.out.println("error from test 8 : " + e.getMessage());
        }

        // test 9 removing old books (with specified year)
        System.out.println("Test 9");
        try {
            bookStore.removeBooks(2020);
        } catch (Exception e) {
            System.out.println("error from test 9 : " + e);
        }

    }
}