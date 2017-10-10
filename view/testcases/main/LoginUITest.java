package tr.com.t2.bkmaaile.view.testcases.main;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;

/**
 * Created by burakdoma on 29/11/2016.
 */
@Ignore
public class LoginUITest extends BaseUITestCase{

    Login login;

    @Before
    public void beforeMethod() {
        super.beforeMethod();
        driver.get(loginCoinGeneratorPage);
        login = PageFactory.initElements(driver, Login.class);
    }

    @Test
    public void basariliGiris() {
        login.girisYap(dogruEposta, dogruSifre);
        login.acilanSayfayiKontrolEt(loginCoinGeneratorPage);
    }

    @Test
    public void hataliKullaniciAdiGir() {
        login.girisYap(hataliEposta, dogruSifre);
        login.hataMesajiGosterimKontrolu(login.hataliKullaniciSifre);
    }

    @Test
    public void hataliSifreGir() {
        login.girisYap(dogruEposta, hataliSifre);
        login.hataMesajiGosterimKontrolu(login.hataliKullaniciSifre);
    }

    @After
    public void afterMethod() {
        driver.quit();
    }

}
