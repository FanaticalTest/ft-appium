package com.fanaticaltest.ftappium;

import com.fanaticaltest.ftappium.devices.AndroidRealDevice;
import com.fanaticaltest.ftappium.devices.IosSimulator;
import com.fanaticaltest.ftconfig.Property;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import java.net.MalformedURLException;


public class MobUITests {

    private IOSDriver iosDriver;
    private AndroidDriver androidDriver;
    private Property p = new Property("./src/main/resources/application.properties");
    private String urlAppium = p.read("appium.server_url");
    private String iosSimUrlAppUnderTest = p.read("iossim.app_under_test_url");
    private String androidUrlAppUnderTest = p.read("android.app_under_test_url");
    private String iosSimPlatformVersion = p.read("iossim.platform_version");
    private String androidPlatformVersion = p.read("android.platform_version");
    private String iosSimDeviceName = p.read("iossim.device_name");
    private String androidDeviceName = p.read("android.device_name");
    private boolean iosSimNoReset = Boolean.parseBoolean(p.read("iossim.capability_no_reset"));
    private boolean androidNoReset = Boolean.parseBoolean(p.read("android.capability_no_reset"));
    private String appiumVersion = p.read("appium.version");
    private String screenshotPath = p.read("appium.screenshot_path");

    @Test
    public void checkGetScreenShot()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosSimulator.setNoReset(iosSimNoReset);
        iosSimulator.setDeviceName(iosSimDeviceName);
        iosDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosDriver);

        try {
            mu.getScreenshot(screenshotPath);
            mu.getScreenshot(screenshotPath, "screenshot-name", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(iosDriver);
    }

    @Test
    public void checkFillFieldBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosDriver);

        mu.fillFieldBy("3", By.name("IntegerA"));
        mu.fillFieldBy("3", By.name("IntegerB"));
        mu.tapButtonBy(By.name("ComputeSumButton"));
        mu.assertTextInElementBy("6",By.name("Answer"));

        try {
            mu.getScreenshot(screenshotPath, "checkFillFieldBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mu.freezeProcess(2L);

        iosSimulator.disconnect(iosDriver);
    }

    @Test
    public void checkSwipeSlideBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosDriver);

        mu.swipeSliderBy(By.xpath("//XCUIElementTypeSlider[@name=\"AppElem\"]"), "0.2");

        try {
            mu.getScreenshot(screenshotPath, "checkSwipeSlideBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(iosDriver);
    }

    @Test
    public void checkIsElementVisible()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosDriver);

        mu.tapButtonBy(By.name("show alert"));
        mu.handleAlertMessage(By.name("Cool title"),By.name("OK"));
        mu.handleAlertMessage(By.name("Unknown alert for negative test"),By.name("OK"));

        iosSimulator.disconnect(iosDriver);
    }

    @Test
    public void checkAndroidConfig()throws MalformedURLException
    {
        AndroidRealDevice androidRealDevice = new AndroidRealDevice(androidPlatformVersion,androidDeviceName,androidUrlAppUnderTest,appiumVersion,urlAppium);
        androidRealDevice.setNoReset(androidNoReset);
        androidDriver = androidRealDevice.connect();
        androidRealDevice.disconnect(androidDriver);
    }
}
