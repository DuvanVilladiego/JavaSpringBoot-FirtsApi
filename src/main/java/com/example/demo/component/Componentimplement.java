package com.example.demo.component;

import org.springframework.stereotype.Component;

@Component
public class Componentimplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("Hola Mundo desde mi componente");
    }
}
