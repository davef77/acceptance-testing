package com.cd.acceptance.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class MyLocalBookStore
{
    private List<Book> allBooks = new ArrayList<>();
    private List<Book> shoppingBasket = new ArrayList<>();


    public void addBook(Book book)
    {
        allBooks.add(book);
    }

    public List<Book> findBooksByTitle(String title)
    {
        List<Book> books = new ArrayList<>();

        for (Book book : allBooks)
        {
            if (book.title().contains(title))
            {
                books.add(book);
            }
        }
        return books;
    }

    public void addToBasket(Book selectedBook)
    {
        shoppingBasket.add(selectedBook);
    }

    public List<Book> listBasketItems()
    {
        return shoppingBasket;
    }
}
