package com.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.example.Utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        DriverFactory.getDriver();
    }

    @After
    public void tearDown() {
        System.out.println("Closing WebDriver...");
        DriverFactory.quitDriver();
    }
}
