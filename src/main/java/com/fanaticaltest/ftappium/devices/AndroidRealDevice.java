package com.fanaticaltest.ftappium.devices;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidRealDevice extends MobileDevice{

    public AndroidRealDevice(String platformVersion, String deviceName, String urlMobileApp, String appiumVersion, String appiumServerUrl) {
        this.setPlatformName("Android");
        this.setPlatformVersion(platformVersion);
        this.setDeviceName(deviceName);
        this.setUrlMobileApp(urlMobileApp);
        this.setAutomationName("UiAutomator2");
        this.setNoReset(true);
        this.setAppiumVersion(appiumVersion);
        this.setAppiumServerUrl(appiumServerUrl);
    }

    public AndroidDriver connect() throws MalformedURLException
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, this.getPlatformName());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, this.getPlatformVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.getDeviceName());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, this.getUrlMobileApp());
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, this.getAutomationName());
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, this.isNoReset());
        desiredCapabilities.setCapability("appiumVersion", this.getAppiumVersion());

        URL urlAppiumServer = new URL(this.getAppiumServerUrl());
        return (new AndroidDriver(urlAppiumServer, desiredCapabilities));
    }

    public void disconnect(AndroidDriver driver)
    {
        driver.quit();
    }
}
