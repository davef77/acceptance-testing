package com.cd.acceptance.dsl.drivers;

import com.cd.acceptance.examples.Book;
import com.cd.acceptance.examples.MyLocalBookStore;
import org.junit.Assert;

import java.util.List;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class MyLocalBookStoreBookShopDriver implements BookShopDriver
{
    private MyLocalBookStore store = new MyLocalBookStore();
    private List<Book> booksByTitle;
    private Book selectedBook;

    public MyLocalBookStoreBookShopDriver()
    {
        store.addBook(new Book("Continuous Delivery", "David Farley & Jez Humble"));
        store.addBook(new Book("Continuous Delivery for Dummies", "Someone Else"));
        store.addBook(new Book("Continuous Delivery of Insulin", "Another Person"));
    }


    @Override
    public void findBooks(String title)
    {
        booksByTitle = store.findBooksByTitle(title);
    }

    @Override
    public void selectBook(String author)
    {
        for (Book book : booksByTitle)
        {
            if (book.isWrittenBy(author))
            {
                selectedBook = book;
            }
        }
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        store.addToBasket(selectedBook);
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        List<Book> basket = store.listBasketItems();

        for (Book book : basket)
        {
            if (item.equals(book.title()))
            {
                return;
            }
        }

        Assert.fail(String.format("Item '%s' not found in shopping basket", item));
    }
}
