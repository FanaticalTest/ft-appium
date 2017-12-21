package com.fanaticaltest.ftappium;

import com.fanaticaltest.ftappium.devices.AndroidRealDevice;
import com.fanaticaltest.ftappium.devices.IosRealDevice;
import com.fanaticaltest.ftappium.devices.IosSimulator;
import com.fanaticaltest.ftconfig.Property;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import java.net.MalformedURLException;


public class MobUITests {

    private IOSDriver iosSimDriver;
    private IOSDriver iosRealDriver;
    private AndroidDriver androidDriver;
    private Property p = new Property("./src/main/resources/application.properties");
    private Property lic = new Property("./src/main/resources/licences.properties");
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
    private String realDeviceName = lic.read("lic.deviceName");
    private String xcodeOrgId = lic.read("lic.xcodeOrgId");
    private String xcodeSigningId = lic.read("lic.xcodeSigningId");
    private String udid = lic.read("lic.udid");
    private String iosUrlAppUnderTest = p.read("ios.app_under_test_url");
    private String iosPlatformVersion = p.read("ios.platform_version");

    @Test
    public void checkGetScreenShot()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosSimulator.setNoReset(iosSimNoReset);
        iosSimulator.setDeviceName(iosSimDeviceName);
        iosSimDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosSimDriver);

        try {
            mu.getScreenshot(screenshotPath);
            mu.getScreenshot(screenshotPath, "screenshot-name", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(iosSimDriver);
    }

    @Test
    public void checkFillFieldBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosSimDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosSimDriver);

        mu.fillFieldByAccessibilityId("4", "IntegerA");
        mu.fillFieldBy("3", By.name("IntegerB"));
        mu.tapButtonBy(By.name("ComputeSumButton"));
        mu.assertTextInElementBy("7",By.name("Answer"));

        try {
            mu.getScreenshot(screenshotPath, "checkFillFieldBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mu.freezeProcess(2L);

        iosSimulator.disconnect(iosSimDriver);
    }

    @Test
    public void checkSwipeSlideBy()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosSimDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosSimDriver);

        mu.swipeSliderBy(By.xpath("//XCUIElementTypeSlider[@name=\"AppElem\"]"), "0.2");

        try {
            mu.getScreenshot(screenshotPath, "checkSwipeSlideBy");
        } catch (Exception e) {
            e.printStackTrace();
        }

        iosSimulator.disconnect(iosSimDriver);
    }

    @Test
    public void checkIsElementVisible()throws MalformedURLException
    {
        IosSimulator iosSimulator = new IosSimulator(iosSimPlatformVersion, iosSimDeviceName,iosSimUrlAppUnderTest,appiumVersion,urlAppium);
        iosSimDriver = iosSimulator.connect();

        MobUI mu = new MobUI(iosSimDriver);

        mu.tapButtonBy(By.name("show alert"));
        mu.handleAlertMessageBy(By.name("Cool title"),By.name("OK"));
        mu.handleAlertMessageBy(By.name("Unknown alert for negative test"),By.name("OK"));

        mu.tapButtonByAccessibilityId("show alert");
        mu.handleAlertMessageByAccessibilityId("this alert is so cool.","OK");
        mu.handleAlertMessageByAccessibilityId("Unknown alert for negative test","OK");


        iosSimulator.disconnect(iosSimDriver);
    }

    // You need to connect a real device
    @Test
    public void checkAndroidConfig()throws MalformedURLException
    {
        AndroidRealDevice androidRealDevice = new AndroidRealDevice(androidPlatformVersion,androidDeviceName,androidUrlAppUnderTest,appiumVersion,urlAppium);
        androidRealDevice.setNoReset(androidNoReset);
        androidDriver = androidRealDevice.connect();
        androidRealDevice.disconnect(androidDriver);
    }

    // You need to connect a real device and setup the licences.properties (licences-sample.properties)
    @Test
    public void checkIosConfig()throws MalformedURLException
    {
        IosRealDevice iosRealDevice = new IosRealDevice(iosPlatformVersion,realDeviceName,iosUrlAppUnderTest, xcodeOrgId,xcodeSigningId, udid,appiumVersion,urlAppium);
        iosRealDriver = iosRealDevice.connect();
        iosRealDevice.disconnect(iosRealDriver);
    }


}
