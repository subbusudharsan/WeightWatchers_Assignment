package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class BaseClass {
    protected static WebDriver driver;

    public static void setDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ File.separator+"driver/chromedriver");
            driver=new ChromeDriver();
            driver.manage().deleteAllCookies();
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + File.separator
                            +"driver/geckodriver");
            driver=new FirefoxDriver();
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
