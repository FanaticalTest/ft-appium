package com.fanaticaltest.ftappium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


public class MobUI {

    private AppiumDriver driver;

    public MobUI(AppiumDriver driver) {
        this.driver = driver;
    }

    private String getScreenshot (String screenshotPath, String fileName, boolean hasPrefix, boolean hasTimestamp) throws Exception {
        SimpleDateFormat sdfScreenshot = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String pngFileName;

        if (hasPrefix == false) {
            pngFileName = sdfScreenshot.format(timestamp) + ".png";
        }
        else if (hasTimestamp == false)
        {
            pngFileName = fileName + ".png";
        }
        else {
            pngFileName = fileName + "-" + sdfScreenshot.format(timestamp) + ".png";
        }

        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPath + pngFileName));
        return ("Screenshot taken " + screenshotPath + pngFileName);
    }

    public String getScreenshot(String screenshotPath, String fileNamePrefix) throws Exception {
        return (getScreenshot(screenshotPath, fileNamePrefix, true, true));
    }

    public String getScreenshot(String screenshotPath) throws Exception {
        return (getScreenshot(screenshotPath, "empty", false, true));
    }

    public String getScreenshot(String screenshotPath, String fileNamePrefix, boolean hasTimestamp) throws Exception {
        return (getScreenshot(screenshotPath, fileNamePrefix, true, hasTimestamp));
    }

    public String fillFieldBy(String value, By by)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        mobileElement.clear();
        mobileElement.sendKeys(value);
        return ("Fill field " + by + " with value " + value);
    }

    public String fillFieldByAccessibilityId(String value, String elemName)
    {
        MobileElement mobileElement = (MobileElement) driver.findElementByAccessibilityId(elemName);
        mobileElement.clear();
        mobileElement.sendKeys(value);
        return ("Fill field " + elemName + " with value " + value);
    }

    @Deprecated
    // Not anymore supported with Java client above 4.x
    // fingers and durationInMillisecond are not anymore used
    public String tapButtonBy(By by, int fingers, int durationInMillisecond)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        //selectedButton.tap(fingers,durationInMillisecond);
        new TouchAction(driver).tap(mobileElement).perform();
        return ("Tap button "+by+" with "+fingers+" finger(s) with a duration "+durationInMillisecond+" millisecond.");
    }

    public String tapButtonBy(By by)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        mobileElement.click();
        return ("Tap button "+by+".");
    }

    public String tapButtonByAccessibilityId(String elem)
    {
        MobileElement mobileElement = (MobileElement) driver.findElementByAccessibilityId(elem);
        mobileElement.click();
        return ("Tap button "+elem+".");
    }

    public String assertTextInElementBy(String value, By by)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        assertThat(mobileElement.getText(), containsString(value));
        return ("Assert text in the element "+by+" with value " + value);
    }

    public String assertTextInElementByAccessibilityId(String value, String elem)
    {
        MobileElement mobileElement = (MobileElement) driver.findElementByAccessibilityId(elem);
        assertThat(mobileElement.getText(), containsString(value));
        return ("Assert text in the element "+elem+" with value " + value);
    }

    public String freezeProcess(long timeInSecond)
    {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ("Freezing process for "+timeInSecond+" seconds");
    }

    public String swipeSliderBy(By by, String value)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        mobileElement.sendKeys(value);
        return ("Swipe slider " + by + " to value " +value );

    }

    public boolean isVisibleElementBy(By by)
    {
        if (driver.findElements(by).size()>0)
            return true;
        else
            return false;
    }

    public boolean isVisibleElementByAccessibilityId(String elem)
    {
        if (driver.findElementsByAccessibilityId(elem).size()>0)
            return true;
        else
            return false;
    }

    @Deprecated
    public String handleAlertMessage(By byAlert, By byOk)
    {
        if (isVisibleElementBy(byAlert))
            return tapButtonBy(byOk);
        else
            return ("Element " + byAlert + " is not visible");
    }

    public String handleAlertMessageBy(By byAlert, By byOk)
    {
        if (isVisibleElementBy(byAlert))
            return tapButtonBy(byOk);
        else
            return ("Element " + byAlert + " is not visible");
    }

    public String handleAlertMessageByAccessibilityId(String elemAlert, String elemOk)
    {
        if (isVisibleElementByAccessibilityId(elemAlert))
            return tapButtonByAccessibilityId(elemOk);
        else
            return ("Element " + elemAlert + " is not visible");
    }

    public String tapOnOffSwitch(By by)
    {
        MobileElement mobileElement = (MobileElement) driver.findElement(by);
        String initialValue = mobileElement.getText();
        mobileElement.click();
        String finalValue = mobileElement.getText();
        assertThat(initialValue,is(not(finalValue)));
        return ("Tap On/Off switch " + by + " - initial value was : " + initialValue + " - final value is : " + finalValue);
    }
}
