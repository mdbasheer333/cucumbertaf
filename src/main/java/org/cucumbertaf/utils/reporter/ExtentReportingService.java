package org.cucumbertaf.utils.reporter;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.Platform;

import java.io.File;

public class ExtentReportingService {

    private static ExtentReports extent;
    private static Platform platform;
    private static final String reportFileName = "cucumbertaf.html";
    private static final String macPath = System.getProperty("user.dir") + "/test-output/HtmlReport";
    private static final String windowsPath = System.getProperty("user.dir") + "\\test-output\\HtmlReport\\";

    public static String getScreenshot_path() {

        return screenshot_path;
    }

    private static final String screenshot_path = System.getProperty("user.dir") + "\\test-output\\HtmlReport\\screenshots\\";
    private static final String macReportFileLoc = macPath + "/" + reportFileName;
    private static final String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static synchronized ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                createReportPath(screenshot_path);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
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

    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }

}
