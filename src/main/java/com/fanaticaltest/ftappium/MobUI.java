package com.fanaticaltest.ftappium;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class MobUI {

    private RemoteWebDriver driver;

    public MobUI(RemoteWebDriver driver) {
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

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
        MobileElement selectedField = (MobileElement) driver.findElement(by);
        selectedField.sendKeys(value);
        return ("Fill field " + by + " with value " + value);
    }

    @Deprecated
    // Not anymore supported with Java client above 4.x
    // fingers and durationInMillisecond are not anymore used
    public String tapButtonBy(By by, int fingers, int durationInMillisecond)
    {
        MobileElement selectedButton = (MobileElement) driver.findElement(by);
        //selectedButton.tap(fingers,durationInMillisecond);
        new TouchAction((MobileDriver) driver).tap(selectedButton).perform();
        return ("Tap button "+by+" with "+fingers+" finger(s) with a duration "+durationInMillisecond+" millisecond.");
    }

    public String tapButtonBy(By by)
    {
        MobileElement selectedButton = (MobileElement) driver.findElement(by);
        new TouchAction((MobileDriver) driver).tap(selectedButton).perform();
        return ("Tap button "+by+".");
    }

    public String assertTextInElementBy(String value, By by)
    {
        MobileElement resultField = (MobileElement) driver.findElement(by);
        assertThat(resultField.getText(), containsString(value));
        return ("Assert text in the element "+by+" with value " + value);
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
        MobileElement selectedSlider = (MobileElement) driver.findElement(by);
        selectedSlider.sendKeys(value);
        return ("Swipe slider " + by + " to value " +value );

    }

    public boolean isVisibleElementBy(By by)
    {
        if (driver.findElements(by).size()>0)
            return true;
        else
            return false;
    }

    public String handleAlertMessage(By byAlert, By byOk)
    {
        if (isVisibleElementBy(byAlert))
            return tapButtonBy(byOk);
        else
            return ("Element " + byAlert + " is not visible");
    }
}
