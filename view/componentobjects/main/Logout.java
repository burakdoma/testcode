package tr.com.t2.bkmaaile.view.componentobjects.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by burakdoma on 24/11/2016.
 */
public class Logout {

    final WebDriver driver;

    public Logout(WebDriver driver) {
        this.driver = driver;
    }


//    span_class layout-tab-menu-link-text

    @FindBy (css="span[class='layout-tab-menu-link-text'")
    public WebElement sagUst;

    @FindBy(css = "a[href*='logout'")
    public WebElement cikisYap;

    public void cikisYap() {
        sagUst.click();
        cikisYap.click();
    }

}
