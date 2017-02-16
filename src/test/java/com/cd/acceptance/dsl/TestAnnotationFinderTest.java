package com.cd.acceptance.dsl;

import com.cd.acceptance.dsl.drivers.ChannelFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.cd.acceptance.dsl.Channels.*;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class TestAnnotationFinderTest extends Dsl
{
    @Channel({Amazon, BookDepository})
    @Test
    public void shouldReportChannelList() throws Exception
    {
        Assert.assertEquals(Arrays.asList("Amazon", "BookDepository"), ChannelFinder.listChannels());
    }
}
