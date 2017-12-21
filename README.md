# ft-appium

## Pre requirements
- com.fanaticaltest:ft-config:0.1.1
- io.appium:java-client:5.0.4
- org.seleniumhq.selenium:selenium-java:3.6.0
- org.hamcrest:hamcrest-core:1.3
- Appium Desktop 1.7.1
- Plug a Mobile Android device for unit test and update the `application.properties` in the section `android.*` accordingly
- Xcode 9.2 with iOS 10.3.1 Simulator for TestApp8.0.app.zip
- Need a real Android and real iPhone to run Unit Test.

## Know limitation
Actually running test on Simulator and real device on the same server will not work.

### Java client reference
- `https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.appium%22%20AND%20a%3A%22java-client%22`

## Run

* Ensure your Appium Desktop is running.

### Start the build and run
```
./gradlew build
```
Note : When you run for the first time the project with a Real device, it may fail due to allow the computer to connect to the device.

### Install in local repository
```
gradle install
```

## Use the library

Mainly we use By to access an element. We could use id, xpath, name, etc. Here in the example we use only id, but it could be any other attribute.

### Tap on a button or link
```
MobUI mu = new MobUI(driver);
mu.tapButtonBy(By.name("button.name"));
```

### Swipe a slider
```
MobUI mu = new MobUI(driver);
mu.swipeSliderBy(By.name("swiper-name), value);
```

### Assert value in a field
```
MobUI mu = new MobUI(driver);
// Using By
mu.assertTextInElementBy(valueToAssert,By.name("field-name"));
// Using ByAccessibilityId

```

### Get screenshot
You can call the method when it requires. Let assume you want to store the picture in the test resources folder. The file name will contains a timestamp.
```
MobUI mu = new MobUI(driver);
// without prefix  but with timestamp
mu.getScreenshot("./src/test/resources/");
// with prefix
mu.getScreenshot("./src/test/resources/", "prefix-value");
// without timestamp
mu.getScreenshot("./src/test/resources/", "filename-value", true);
```

### Assert if an element is visible
```
MobUI mu = new MobUI(driver);
// Using By
Assert.assertTrue(mu.isVisibleElementBy(By.name("title-name")));
// Using ByAccessibilityId
Assert.assertTrue(mu.isVisibleElementByAccessibilityId("title-name"));
```

### Handle alert pop-up
Often your app is poping notification and you want to handle them without failing your test.
```
MobUI mu = new MobUI(driver);
// Using By
mu.handleAlertMessageBy(By.name("Alert-Title"),By.name("OK"));
// Using ByAccessibilityId
mu.handleAlertMessageByAccessibilityId("this alert is so cool.","OK");
```

### Freeze test process
Just for continence, but not recommended except for work around you could pause a test a some seconds.
In this example we wait for 2 seconds. The L is to convert the value in Long.
```
MobUI mu = new MobUI(driver);
mu.freezeProcess(2L);
```

## Device setup
Actually we expose 2 way of using the devices:
-iOS Simulator
-Android Real Device

### iOS Simulator
```
IOSDriver driver;
IosSimulator iosSimulator = new IosSimulator(platformVersion,deviceName,urlAppUnderTest,appiumVersion,urlAppium);
iosSimulator.setNoReset(noReset);           //by default is set to true
iosSimulator.setDeviceName(deviceName);     //by default the value is "iPhone Simulator"
driver = iosSimulator.connect();            //connect
iosSimulator.disconnect(driver);            //disconnect
```

### Android Real Device
```
AndroidDriver driver;
AndroidDriver androidRealDevice = new IosSimulator(platformVersion,deviceName, urlAppUnderTest,appiumVersion,urlAppium);
androidRealDevice.setNoReset(noReset);              //by default is set to true
androidRealDevice.setAutomationName(automationName) //by default the value is "UiAutomator2"
driver = androidRealDevice.connect();               //connect
androidRealDevice.disconnect(driver);               //disconnect
```

### iPhone Real Device
By default NoReset is set to false
```
osRealDevice iosRealDevice = new IosRealDevice(iosPlatformVersion,realDeviceName,iosUrlAppUnderTest, xcodeOrgId,xcodeSigningId, udid,appiumVersion,urlAppium);
iosRealDriver = iosRealDevice.connect();
iosRealDevice.disconnect(iosRealDriver);
```

## XCode
### Setup Emulator version on Xcode 9.2
* Open Xcode
* Select `Xcode` in the top menu
* Select `Open developer Tool`
* Select `Simulators`
* Then the Simulator starts
* Select `Hardware` in the top menu
* Select `Devices`
* Select `Manage Devices...`
* Select `Simulators` in the middle top of the window
* Click on `+` bottom left of the window corner
* Select the drop-down `OS Version`
* Click on `Download more simulator runtimes...`
* Select the version you need.

### Create a Simulator for the unit test
We need a iPhone 7 using iOS 10.3.x So create a Simulator with the name `iPhone 7 iOS10.3 Simulator`

