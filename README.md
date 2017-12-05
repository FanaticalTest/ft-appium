# ft-appium

## Pre requirements
- com.fanaticaltest:ft-config:0.1.1
- io.appium:java-client:3.4.1
- org.hamcrest:hamcrest-core:1.3
- Appium Desktop 1.6.5

## Run

* Ensure your Appium Desktop is running.

### Start the build and run
```
./gradlew build
```

### Install in local repository
```
gradle install
```

## Use the library

Mainly we use By to access an element. We could use id, xpath, name, etc. Here in the example we use only id, but it could be any other attribute.

### Tap on a button or link
```
MobUI mu = new MobUI(driver);
mu.tapButtonBy(By.name("button.name"),numberOfFinger,tapDurationMillisecond);
```

### Swipe a slider
```
MobUI mu = new MobUI(driver);
mu.swipeSliderBy(By.name("swiper-name), value);
```

### Assert value in a field
```
MobUI mu = new MobUI(driver);
mu.assertTextInElementBy(valueToAsseert,By.name("field-name"));
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
Assert.assertTrue(mu.isVisibleElementBy(By.name("title-name")));
```

### Freeze test process
Just for continence, but not recommended except for work around you could pause a test a some seconds.
In this example we wait for 2 seconds. The L is to convert the value in Long.
```
MobUI mu = new MobUI(driver);
mu.freezeProcess(2L);
```