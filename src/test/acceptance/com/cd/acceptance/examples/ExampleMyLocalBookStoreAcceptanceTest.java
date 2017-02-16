package com.cd.acceptance.examples;

import com.cd.acceptance.dsl.Channel;
import com.cd.acceptance.dsl.Dsl;
import org.junit.Test;

import static com.cd.acceptance.dsl.Channels.Amazon;
import static com.cd.acceptance.dsl.Channels.BookDepository;
import static com.cd.acceptance.dsl.Channels.MyLocalBookStore;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class ExampleMyLocalBookStoreAcceptanceTest extends Dsl
{
        @Test
        @Channel({MyLocalBookStore, BookDepository, Amazon})
        public void shouldAddBookToShoppingBasket() throws Exception
        {
            shopping.searchForBook("title: Continuous Delivery");
            shopping.selectBook("author: David Farley");

            shopping.addSelectedItemToShoppingBasket();

            shopping.assertItemListedInShoppingBasket("item: Continuous Delivery");
        }
}
