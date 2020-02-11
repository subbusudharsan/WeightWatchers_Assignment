package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FindAStudio {

    @FindBy(id = "meetingSearch")
    public static WebElement searchInputBoc;

    @FindBy(xpath = "(//button[contains(text(),'Submit')])[1]")
    public static WebElement searchButton;

    @FindBy(className = "meeting-location")
    public static List<WebElement> searchResult;

    @FindBy(className = "bx-wrap")
    public static WebElement modal ;

    @FindBy(className = "bx-close bx-close-link bx-close-inside")
    public static List<WebElement> modalClose ;
}
