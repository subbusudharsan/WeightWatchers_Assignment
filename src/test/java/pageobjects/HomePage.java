package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @FindBy(className = "find-a-meeting")
    public static WebElement findAStudio;
}
