package com.cd.acceptance.dsl.drivers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class BookShopDrivers implements BookShopDriver
{
    private Map<String, BookShopDriver> drivers = new HashMap<String, BookShopDriver>();

    public BookShopDrivers()
    {
        drivers.put("Amazon", new AmazonBookShopDriver());
        drivers.put("BookDepository", new BookDepositoryBookShopDriver());
        drivers.put("MyLocalBookStore", new MyLocalBookStoreBookShopDriver());
    }

    private BookShopDriver driver()
    {
        List<String> channels = ChannelFinder.listChannels();

        return drivers.get(channels.get(0));    // Hack to run only first channel for now
    }

    @Override
    public void findBooks(String title)
    {
        driver().findBooks(title);
    }

    @Override
    public void selectBook(String author)
    {
        driver().selectBook(author);
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        driver().addSelectedItemToShoppingBasket();
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        driver().assertListedInShoppingBasket(item);
    }
}
