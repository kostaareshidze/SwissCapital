package tbcitacademy.suiteListener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.xml.XmlSuite;

import java.util.Date;
import java.util.List;

public class SuiteListener implements ISuiteListener {
    private Date startDate;
    @Override
    public void onStart(ISuite suite) {
        startDate = new Date();
        System.out.printf("%s STARTED ON %s\n", suite.getName(), new Date(System.currentTimeMillis()));
    }

    @Override
    public void onFinish(ISuite suite) {
        Date endDate = new Date();
        long totalTimeMillis = endDate.getTime() - startDate.getTime();
        long totalTimeSeconds = totalTimeMillis / 1000;
        long hours = totalTimeSeconds / 3600;
        long minutes = (totalTimeSeconds % 3600) / 60;
        long seconds = totalTimeSeconds % 60;
        System.out.printf("%s FINISHED ON %s\n", suite.getName(), endDate);
        System.out.printf("Total time taken for %s: %d hours, %d minutes, %d seconds%n",
                suite.getName(), hours, minutes, seconds);
        List<XmlSuite> xmlSuites = suite.getXmlSuite().getParentSuite() != null ?
                suite.getXmlSuite().getParentSuite().getChildSuites() :
                suite.getXmlSuite().getChildSuites();
        if (xmlSuites != null && !xmlSuites.isEmpty()) {
            System.out.println("Included Tests:");
            for (XmlSuite xmlSuite : xmlSuites) {
                List<String> includedTests = xmlSuite.getIncludedGroups();
                if (includedTests != null && !includedTests.isEmpty()) {
                    System.out.printf(" - %s%n", includedTests);
                }
            }
        }
        System.out.println();
    }
}
