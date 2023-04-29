package org.cucumbertaf.utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ExtentReportingService {

    private static ExtentReports extent;
    private static final String reportFileName = "cucumbertaf";

    private static String report_path = "";
    private static String screenshot_path = "";

    public static String getReportFileName() {
        return reportFileName;
    }

    public static String getScreenshot_path() {
        return screenshot_path;
    }

    public static String get_report_path() {
        return report_path;
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static void createInstance() {
        String fileNameWithPath = getReportFileLocation();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileNameWithPath);
        htmlReporter.config().setDocumentTitle("cucumbertaf");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    private static String getReportFileLocation() {
        String basePath = System.getProperty("user.dir");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        report_path = basePath + File.separator + "test-output" + File.separator + "HtmlReport_" + sdf.format(timestamp);
        screenshot_path = report_path + File.separator + "screenshots" + File.separator;
        createReportPath(report_path);
        createReportPath(screenshot_path);
        return report_path + File.separator + reportFileName + ".html";
    }

    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

}
