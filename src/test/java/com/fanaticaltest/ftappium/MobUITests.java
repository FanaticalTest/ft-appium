package com.fanaticaltest.ftappium;

import com.fanaticaltest.ftappium.devices.IosSimulator;
import com.fanaticaltest.ftconfig.Property;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import java.net.MalformedURLException;


public class MobUITests {

    private IOSDriver driver;
    private Property p = new Property("./src/main/resources/application.properties");
    private String urlAppium = p.read("appium.server_url");
    private String urlAppUnderTest = p.read("sut.app_under_test_url");
    private String platformVersion = p.read("sut.platform_version");
    private String deviceName = p.read("sut.device_name");
    private boolean noReset = Boolean.parseBoolean(p.read("sut.capability_no_reset"));
    private String appiumVersion = p.read("appium.version");
    private String screenshotPath = p.read("appium.screenshot_path");
    private int tapDurationMillisecond = Integer.parseInt(p.read("user.tap_duration_millisecond"));

    @Test
    public void checkGetScreenShot()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(platformVersion,urlAppUnderTest,appiumVersion,urlAppium);
        iosSimulator.setNoReset(noReset);
        iosSimulator.setDeviceName(deviceName);
        driver = iosSimulator.connect();

        MobUI mu = new MobUI(driver);

        try {
            mu.getScreenshot(screenshotPath);
            mu.getScreenshot(screenshotPath, "screenshot-name", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(driver);
    }

    @Test
    public void checkFillFieldBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(platformVersion,urlAppUnderTest,appiumVersion,urlAppium);
        driver = iosSimulator.connect();

        MobUI mu = new MobUI(driver);

        mu.fillFieldBy("3", By.name("IntegerA"));
        mu.fillFieldBy("3", By.name("IntegerB"));
        mu.tapButtonBy(By.name("ComputeSumButton"),1,tapDurationMillisecond);
        mu.assertTextInElementBy("6",By.name("Answer"));

        try {
            mu.getScreenshot(screenshotPath, "checkFillFieldBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mu.freezeProcess(2L);

        iosSimulator.disconnect(driver);
    }

    @Test
    public void checkSwipeSlideBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(platformVersion,urlAppUnderTest,appiumVersion,urlAppium);
        driver = iosSimulator.connect();

        MobUI mu = new MobUI(driver);

        mu.swipeSliderBy(By.xpath("//XCUIElementTypeSlider[@name=\"AppElem\"]"), "0.2");

        try {
            mu.getScreenshot(screenshotPath, "checkSwipeSlideBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(driver);
    }

    @Test
    public void checkIsElementVisible()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(platformVersion,urlAppUnderTest,appiumVersion,urlAppium);
        driver = iosSimulator.connect();

        MobUI mu = new MobUI(driver);

        mu.tapButtonBy(By.name("show alert"),1,tapDurationMillisecond);
        mu.handleAlertMessage(By.name("Cool title"),By.name("OK"));
        mu.handleAlertMessage(By.name("Unknown alert for negative test"),By.name("OK"));

        iosSimulator.disconnect(driver);
    }
}
