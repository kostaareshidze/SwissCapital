package tbcitacademy.data;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int fail = 0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        RetryFail annotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().
                getAnnotation(RetryFail.class);
        if (annotation != null && fail < annotation.count()){
            fail++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("mogesalmebit");
        System.out.println("kostaa");
    }
}
