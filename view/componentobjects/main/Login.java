package tr.com.t2.bkmaaile.view.componentobjects.main;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by burakdoma on 29/11/2016.
 */
public class Login {

    final WebDriver driver;

    public Login(WebDriver driver) { this.driver = driver; }

    @FindBy(id = "username")
    private WebElement kullaniciAdiAlani;

    @FindBy (id = "password")
    private WebElement sifreAlani;

    @FindBy (id = "btnLogin")
    private WebElement girisYapButonu;

    @FindBy (className = "ui-messages-error-summary")
    private WebElement hataMesajAlani;

    public String hataliKullaniciSifre = "Kullanıcı adı veya şifre hatalıdır";
    public String hataliEpostaAdresi = "Girilen e-posta adresi hatalıdır";

    public void kullaniciAdiGir(String kullaniciAdi) {
        kullaniciAdiAlani.sendKeys(kullaniciAdi);
    }

    public void sifreGir(String sifre) {
        sifreAlani.sendKeys(sifre);
    }

    public void girisYapButonunaBas() {
        girisYapButonu.click();
    }

    public void girisYap(String kullaniciAdi, String sifre) {
        kullaniciAdiGir(kullaniciAdi);
        sifreGir(sifre);
        girisYapButonunaBas();
    }

    public void hataMesajiGosterimKontrolu (String hataMesaji) {
        hataMesajAlani.getText().contains(hataMesaji);
    }

    public void acilanSayfayiKontrolEt (String url) {
        Assert.assertTrue(url.equals(driver.getCurrentUrl()));
    }

}
