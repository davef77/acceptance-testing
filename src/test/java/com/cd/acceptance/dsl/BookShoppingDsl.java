package com.cd.acceptance.dsl;

import com.cd.acceptance.dsl.drivers.BookShopDrivers;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class BookShoppingDsl
{
    private final BookShopDrivers driver;

    public BookShoppingDsl(BookShopDrivers drivers)
    {
        this.driver = drivers;
    }

    public void searchForBook(String... args)
    {
        Params params = new Params(args);
        String title = params.Optional("title", "Continuous Delivery");

        driver.findBooks(title);
    }

    public void selectBook(String... args)
    {
        Params params = new Params(args);
        String author = params.Optional("author", "David Farley");

        driver.selectBook(author);
    }

    public void addSelectedItemToShoppingBasket()
    {
        driver.addSelectedItemToShoppingBasket();
    }

    public void assertItemListedInShoppingBasket(String... args)
    {
        Params params = new Params(args);
        String item = params.Optional("item", "Continuous Delivery");

        driver.assertListedInShoppingBasket(item);
    }
}
