package tr.com.t2.bkmaaile.view.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by burakdoma on 12/10/16.
 */
public class BaseUITestCase {

    public static String loginPage = "http://localhost:8080/market/";
    public static String userGroupPage = "http://localhost:8080/market/admin/userGroups.xhtml";
    public static String adayIslemleriPage = "http://localhost:8080/market/admin/user.xhtml";
    public static String marketPage = "http://localhost:8080/market/admin/market.xhtml";

    public static String dogruEposta = "seyda.gunduz@t2.com.tr";
    public static String hataliEposta = "hatali";

    public static String dogruSifre = "bkm";
    public static String hataliSifre = "bkm2";

    protected static WebDriver driver = null;

    public void beforeMethod() {
//        FirefoxProfile profile = new FirefoxProfile(new File("profile/folder/path"));
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","/Users/burakdoma/Documents/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void afterMethod() {
        driver.quit();
    }

}
