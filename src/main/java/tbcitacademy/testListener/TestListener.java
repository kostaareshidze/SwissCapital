package tbcitacademy.testListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {
    private Date testStartDate;
    private Date testMethodStartDate;

    @Override
    public void onStart(ITestContext context) {
        System.out.printf("TEST '%s' STARTED ON: %s%n\n", context.getName(), context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        Date testEndDate = new Date();
        long totalTime = testEndDate.getTime() - testStartDate.getTime();
        System.out.printf("TEST '%s' FINISHED ON: %s. TOTAL TIME: %d ms%n%n",
                context.getName(), testEndDate, totalTime);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("TEST '%s' STARTED AT: %s\n", result.getName(), new Date(result.getStartMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (testMethodStartDate != null && result.getStatus() == ITestResult.FAILURE) {
            long totalTime = new Date().getTime() - testMethodStartDate.getTime();
            System.out.printf("TEST METHOD '%s' HAS FAILED. TOTAL TIME: %d ms%n",
                    result.getName(), totalTime);
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        if (testMethodStartDate != null) {
            long totalTime = new Date().getTime() - testMethodStartDate.getTime();
            System.out.printf("TEST METHOD '%s' HAS FAILED. TOTAL TIME: %d ms%n",
                    result.getName(), totalTime);
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.printf("TEST METHOD '%s' HAS BEEN SKIPPED.%n", result.getName());
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.printf("TEST METHOD '%s' HAS FAILED BUT WITHIN SUCCESS PERCENTAGE.%n", result.getName());
    }


}