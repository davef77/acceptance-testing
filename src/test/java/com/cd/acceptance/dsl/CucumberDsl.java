package com.cd.acceptance.dsl;

import com.cd.acceptance.dsl.drivers.BookShopDrivers;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class CucumberDsl
{
    public BookShoppingDsl shopping;

    public CucumberDsl()
    {
        shopping = new BookShoppingDsl(new BookShopDrivers());
    }
}
