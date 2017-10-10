package tr.com.t2.bkmaaile.view.testcases.main;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;

/**
 * Created by burakdoma on 24/11/2016.
 */
@Ignore
public class LoginUITest extends BaseUITestCase {

    Login login;

    @Before
    public void beforeMethod() {
        super.beforeMethod();
        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test case: "+description.getMethodName());
        }

        protected void finished(Description description) {
            System.out.println("Finished test case: "+description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println(description.getMethodName() + " " + "test case is failed!\n");
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println(description.getMethodName() + " " + "test case is succeeded!\n");
        }
    };

    @Test
    public void basariliGiris() {
        login.girisYap(dogruEposta, dogruSifre);
        login.acilanSayfayiKontrolEt(loginPage);
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
