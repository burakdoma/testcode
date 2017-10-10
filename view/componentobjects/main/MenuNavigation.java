package tr.com.t2.bkmaaile.view.componentobjects.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by burakdoma on 18/10/16.
 */
public class MenuNavigation {
    final WebDriver driver;
    public MenuNavigation (WebDriver driver) { this.driver = driver; }

    @FindAll(@FindBy(css = "li[role='menuitem']"))
    List<WebElement> menuItems;

    public static String kullaniciIslemleri = "Kullanıcı İşlemleri";
    public static String kullaniciGrupIslemleri = "Kullanıcı Grup İşlemleri";
    public static String onayBekleyenKullaniciIslemleri = "Onay Bekleyen Kullanıcı İşlemleri";
    public static String marketYonetimi = "Market Yönetimi";
    public static String paraAktarimi = "Para Aktarımı";
    public static String gecmisIslemler = "Geçmiş İşlemler";

    public void menuTikla(String menuItem){
        int i = 0;
        while(i<menuItems.size()){
            if(menuItems.get(i).getText().equals(menuItem)){
                menuItems.get(i).click();
            }
            i++;
        }
    }
}
