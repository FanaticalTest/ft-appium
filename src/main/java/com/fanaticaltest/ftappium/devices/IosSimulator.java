package com.fanaticaltest.ftappium.devices;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class IosSimulator extends MobileDevice{

    public IosSimulator(String platformVersion, String deviceName, String urlMobileApp, String appiumVersion, String appiumServerUrl) {
        this.setPlatformName("iOS");
        this.setPlatformVersion(platformVersion);
        this.setDeviceName(deviceName);
        this.setUrlMobileApp(urlMobileApp);
        this.setNoReset(true);
        this.setAppiumVersion(appiumVersion);
        this.setAppiumServerUrl(appiumServerUrl);
    }

    public IOSDriver connect() throws MalformedURLException
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, this.getPlatformName());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, this.getPlatformVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.getDeviceName());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, this.getUrlMobileApp());
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, this.isNoReset());
        desiredCapabilities.setCapability("appiumVersion", this.getAppiumVersion());

        URL urlAppiumServer = new URL(this.getAppiumServerUrl());
        return (new IOSDriver(urlAppiumServer, desiredCapabilities));
    }

    public void disconnect(IOSDriver driver)
    {
        driver.quit();
    }
}
