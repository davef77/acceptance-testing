package com.cd.acceptance.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class PollWithTimeOut
{
    private long duration;
    private long waitDuration = 10L;
    private long start;

    public static PollWithTimeOut await()
    {
        return new PollWithTimeOut();
    }

    public PollWithTimeOut atMost(long millis)
    {
        duration = millis;

        return this;
    }


    public void until(Supplier<List<WebElement>> supplier, IntConsumer consumer)
    {
        List<WebElement> found;
        start = System.currentTimeMillis();

        do
        {
            found = supplier.get();

        } while (found.size() == 0 && !timedOut());

        consumer.accept(found.size());
    }

    private boolean timedOut()
    {
        long now = System.currentTimeMillis();
        boolean isTimedOut = now - start >= duration;

        if (!isTimedOut)
            try
            {
                Thread.sleep(waitDuration);
            }
            catch (InterruptedException e)
            {
                // Intentionally Ignored
            }

        return isTimedOut;
    }
}
