package com.cd.acceptance.dsl;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class Params
{
    private final String[] args;

    public Params(String[] args)
    {
        this.args = args;
    }

    public String Optional(String name, String defaultValue)
    {
        for (String arg : args)
        {
            int index = arg.indexOf(name + ": ");
            if (index != -1)
            {
                return arg.substring(index + name.length() + 2);
            }
        }
        return defaultValue;
    }

}
