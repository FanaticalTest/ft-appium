package com.fanaticaltest.ftappium.devices;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class IosSimulator {

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String urlMobileApp;
    private boolean noReset;
    private String appiumVersion;
    private String appiumServerUrl;

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public IosSimulator(String platformVersion, String urlMobileApp, String appiumVersion, String appiumServerUrl) {
        this.platformName ="iOS";
        this.platformVersion = platformVersion;
        this.deviceName="iPhone Simulator";
        this.urlMobileApp=urlMobileApp;
        this.noReset=true;
        this.appiumVersion=appiumVersion;
        this.appiumServerUrl=appiumServerUrl;
    }

    public IOSDriver connect() throws MalformedURLException
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, this.platformName);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, this.platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, this.urlMobileApp);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, this.noReset);
        desiredCapabilities.setCapability("appiumVersion", this.appiumVersion);

        URL urlAppiumServer = new URL(this.appiumServerUrl);
        return (new IOSDriver(urlAppiumServer, desiredCapabilities));
    }

    public void disconnect(IOSDriver driver)
    {
        driver.quit();
    }
}
