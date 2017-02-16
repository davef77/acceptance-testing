package com.cd.acceptance.dsl.drivers;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public interface BookShopDriver
{
    void findBooks(String title);

    void selectBook(String author);

    void addSelectedItemToShoppingBasket();

    void assertListedInShoppingBasket(String item);
}
