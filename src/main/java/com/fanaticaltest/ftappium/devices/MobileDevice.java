package com.fanaticaltest.ftappium.devices;


public abstract class MobileDevice {

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String urlMobileApp;
    private boolean noReset;
    private String appiumVersion;
    private String appiumServerUrl;
    private String automationName;
    private String xcodeOrgId;
    private String xcodeSigningId;
    private String udid;


    //Setter
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setUrlMobileApp(String urlMobileApp) {
        this.urlMobileApp = urlMobileApp;
    }

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public void setAppiumVersion(String appiumVersion) {
        this.appiumVersion = appiumVersion;
    }

    public void setAppiumServerUrl(String appiumServerUrl) {
        this.appiumServerUrl = appiumServerUrl;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public void setXcodeOrgId(String xcodeOrgId) {
        this.xcodeOrgId = xcodeOrgId;
    }

    public void setXcodeSigningId(String xcodeSigningId) {
        this.xcodeSigningId = xcodeSigningId;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    //Getter
    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUrlMobileApp() {
        return urlMobileApp;
    }

    public boolean isNoReset() {
        return noReset;
    }

    public String getAppiumVersion() {
        return appiumVersion;
    }

    public String getAppiumServerUrl() {
        return appiumServerUrl;
    }

    public String getAutomationName() {
        return automationName;
    }

    public String getXcodeOrgId() {
        return xcodeOrgId;
    }

    public String getXcodeSigningId() {
        return xcodeSigningId;
    }

    public String getUdid() {
        return udid;
    }

    //Constructor
    public MobileDevice() {}
}
