package com.example.usereadreplicademo.annotation;

import java.lang.annotation.*;

/**
 * This is the annotation used on the repositories which needs to connect to READONLY instance of the database.
 * It's then being used by READONLY data source configuration to scan required repositories
 *
 * @author pgayal
 * created on 02/20/2022
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ReadOnlyRepository {
}
