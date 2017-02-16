package com.cd.acceptance.dsl.drivers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.cd.acceptance.utils.PollWithTimeOut.await;
import static junit.framework.TestCase.assertEquals;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class BookDepositoryBookShopDriver implements BookShopDriver
{

    private final static String BOOKSHOP_URL = "http://www.bookdepository.com/";

    private WebDriver driver;

    @Override
    public void findBooks(String title)
    {
        gotoPage(BOOKSHOP_URL, "Book Depository: Millions of books with free delivery worldwide");
        WebElement searchBox = driver().findElement(By.name("searchTerm"));

        searchBox.sendKeys(title + "\n");
    }


    @Override
    public void selectBook(String author)
    {
        WebElement book =  driver().findElement(By.xpath(String.format("//div[@class=\"book-item\"]/div/p/a[text()='%s']/../../../div[@class=\"item-info\"]/h3/a", getAuthor(author))));

        book.click();
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        WebElement buyButton = driver().findElement(By.className("add-to-basket"));

        buyButton.click();
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        gotoPage("https://www.bookdepository.com/basket", "Your basket");

        await()
                .atMost(1000)
                .until(() -> driver().findElements(By.xpath("//div[@class=\"basket-item\"]/*[contains(., \"Continuous Delivery\")]")),
                             found -> assertEquals(String.format("Item '%s' not found in shopping basket", item), 1, found));
    }

    private void gotoPage(String url, String expectedTitle)
    {
        driver().get(url);

        String title = driver().getTitle();

        assertEquals(expectedTitle, title);
    }

    private String getAuthor(String author)
    {
        if ("David Farley".equals(author))
            return "Jez Humble";
        else
            return author;
    }

    private WebDriver driver()
    {
        if (driver == null)
        {
            try
            {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), DesiredCapabilities.chrome());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }

        return driver;
    }
}
