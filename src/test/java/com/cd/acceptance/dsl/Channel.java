package com.cd.acceptance.dsl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Channel
{
    Channels[] value() default Channels.Unknown;
}

