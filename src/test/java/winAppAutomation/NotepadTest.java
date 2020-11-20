package winAppAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import io.appium.java_client.windows.WindowsDriver;

public class NotepadTest {

    public static WindowsDriver driver = null;

    @BeforeClass
    public static void setUp() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        cap.setCapability("plateform", "Windows");
        cap.setCapability("deviceName", "WindowsPC");

        try {
            driver =
                    new WindowsDriver(new URL("http://127.0.0.1:4723"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void cleanUp() {
        driver.quit();
    }

    @Test
    public void checkHelpAboutNowTest() {
        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        driver.findElementByName("OK").click();
    }

    @Test
    public void sendTextTest() {
        driver.findElementByName("Text Editor").sendKeys("This is Notepad automating by Jigar "
                + "\n This is my 1st Desktop Automation");
        driver.findElementByName("Text Editor").clear();
    }

    @Test
    public void getTimeAndDate() {
        driver.findElementByName("Edit").click();
        //driver.findElementByName("Time/Date").click();
        driver.findElementByAccessibilityId("26").click();
        //driver.findElementByName("Text Editor").clear();
        String value = driver.findElementByName("Text Editor").getAttribute("Value.Value");
        System.out.println(value);
        Assert.assertTrue(value.contains("20-11-2020"));
    }
}
