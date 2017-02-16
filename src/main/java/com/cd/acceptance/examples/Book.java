package com.cd.acceptance.examples;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class Book
{
    private final String title;
    private final String author;

    public Book(String title, String author)
    {
        this.title = title;
        this.author = author;
    }

    public boolean isWrittenBy(String author)
    {
        return this.author.contains(author);
    }

    public String title()
    {
        return title;
    }
}
