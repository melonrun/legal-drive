package com.wordstalk.legal.drive.inject.annotation;

import java.lang.annotation.*;

/**
 * Created by y on 2018/1/16.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PartnerRequired {


}
