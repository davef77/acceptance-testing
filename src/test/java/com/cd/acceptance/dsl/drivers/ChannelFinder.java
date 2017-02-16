package com.cd.acceptance.dsl.drivers;


import com.cd.acceptance.dsl.Channel;
import com.cd.acceptance.dsl.Channels;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class ChannelFinder
{
    public static List<String> listChannels()
    {
        ArrayList<String> channels = new ArrayList<String>();

        Channel c = findTestMethod().getAnnotation(Channel.class);

        for (Channels channel : c.value())
        {
            channels.add(channel.name());
        }

        return channels;
    }

    private static Method findTestMethod()
    {
        StackTraceElement[] elements = new Throwable().fillInStackTrace().getStackTrace();

        for (int i = 1; i < elements.length; i++)
        {
            StackTraceElement element = elements[i];

            try
            {
                Class clz = Class.forName(element.getClassName());
                Method method = clz.getMethod(element.getMethodName(), new Class[0]);

                for (Annotation annotation : method.getAnnotations())
                {
                    if (annotation.annotationType() == Test.class)
                    {
                        return method;
                    }
                }
            }
            catch (NoSuchMethodException ignored)
            {

            }
            catch (SecurityException ignored)
            {

            }
            catch (ClassNotFoundException ignored)
            {

            }

        }
        return null;
    }
}
