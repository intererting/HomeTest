package com.yuliyang.hometest;

import android.widget.TextView;

public class Test {

    static Test test = new Test();

    static {
        System.out.println("test static");
    }

    {
        System.out.println("test init");
    }

    public static void main(String[] args) {
        new Test();
    }
}
