package com.fanaticaltest.ftappium.devices;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidRealDevice {

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String urlMobileApp;
    private String automationName;
    private boolean noReset;
    private String appiumVersion;
    private String appiumServerUrl;


    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public AndroidRealDevice(String platformVersion, String deviceName, String urlMobileApp, String appiumVersion, String appiumServerUrl) {
        this.platformName ="Android";
        this.platformVersion = platformVersion;
        this.deviceName=deviceName;
        this.urlMobileApp=urlMobileApp;
        this.automationName="UiAutomator2";
        this.noReset=true;
        this.appiumVersion=appiumVersion;
        this.appiumServerUrl=appiumServerUrl;
    }

    public AndroidDriver connect() throws MalformedURLException
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, this.platformName );
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, this.platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, this.urlMobileApp);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, this.automationName);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, this.noReset);
        desiredCapabilities.setCapability("appiumVersion", this.appiumVersion);

        URL urlAppiumServer = new URL(this.appiumServerUrl);
        return (new AndroidDriver(urlAppiumServer, desiredCapabilities));
    }

    public void disconnect(AndroidDriver driver)
    {
        driver.quit();
    }
}
