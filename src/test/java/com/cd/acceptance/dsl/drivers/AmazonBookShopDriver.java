package com.cd.acceptance.dsl.drivers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class AmazonBookShopDriver implements BookShopDriver
{

    private final static String BOOKSHOP_URL = "http://www.amazon.co.uk";

    private WebDriver driver;

    public AmazonBookShopDriver()
    {
    }

    @Override
    public void findBooks(String title)
    {
        gotoPage(BOOKSHOP_URL, "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more");
        WebElement searchBox = driver().findElement(By.id("twotabsearchtextbox"));

        searchBox.sendKeys(title + "\n");
    }

    @Override
    public void selectBook(String author)
    {
        WebElement book =  driver().findElement(By.xpath(String.format("//div[@class=\"a-row a-spacing-none\"]/span/a[text()='%s']/../../../div/a[contains(@class, 's-access-detail-page')]", author)));

        book.click();
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        WebElement buyButton = driver().findElement(By.id("add-to-cart-button"));

        buyButton.click();
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        gotoPage("https://www.amazon.co.uk/gp/cart/view.html/ref=nav_cart", "Amazon.co.uk Shopping Basket");

        List<WebElement> found = driver().findElements(By.xpath("//span[@class=\"a-list-item\"]/*[contains(., \"Continuous Delivery\")]"));

        assertEquals(String.format("Item '%s' not found in shopping basket", item), 1, found.size());
    }

    private void gotoPage(String page, String expectedTitle)
    {
        driver().get(page);

        assertEquals(expectedTitle, driver().getTitle());
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
