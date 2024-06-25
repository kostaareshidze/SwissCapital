package tbcitacademy.reportListener;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public class ReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                if (!testContext.getFailedTests().getAllMethods().isEmpty()) {
                    System.out.println("Failed Tests in Suite: " + suiteResult.getTestContext().getName());
                    for (ITestNGMethod failedMethod : testContext.getFailedTests().getAllMethods()) {
                        System.out.println(" - " + failedMethod.getMethodName());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("kostaaa");
    }
}
