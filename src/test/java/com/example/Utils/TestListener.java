package com.example.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent= ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        currentTest.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().log(Status.PASS , "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest.get().log(Status.FAIL , result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().log(Status.SKIP , result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
