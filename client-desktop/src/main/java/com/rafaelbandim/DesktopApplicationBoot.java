package com.rafaelbandim;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class DesktopApplicationBoot {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("com.rafaelbandim");
    }
}
