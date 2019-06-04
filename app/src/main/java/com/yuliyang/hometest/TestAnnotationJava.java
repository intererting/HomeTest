package com.yuliyang.hometest;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TestAnnotationJava {

    public final static String TYPE_A = "type_a";
    public final static String TYPE_B = "type_b";

    @StringDef({TYPE_A, TYPE_B})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TestAnnotation {

    }

    public static void main(String[] args) {
        new TestAnnotationJava().test(TYPE_A);

    }

    void test(@TestAnnotation String type) {
        System.out.println(type);
    }
}