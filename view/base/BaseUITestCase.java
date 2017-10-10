package tr.com.t2.bkmaaile.view.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by burakdoma on 29/11/2016.
 */
public class BaseUITestCase {

    public static String loginCoinGeneratorPage = "http://localhost:8080/coingenerator/";
    public static String marketManagement = "http://localhost:8080/coingenerator/coinadmin/marketManagement.xhtml";
    public static String coinManagement = "http://localhost:8080/coingenerator/coinadmin/coinManagement.xhtml";
    public static String coinGenerator = "http://localhost:8080/coingenerator/coinadmin/coinCreation.xhtml";
    public static String operationHistory = "http://localhost:8080/coingenerator/coinadmin/story.xhtml";

    public static String dogruEposta = "coinadmin@t2.com.tr";
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
