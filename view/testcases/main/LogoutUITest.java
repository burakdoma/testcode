package tr.com.t2.bkmaaile.view.testcases.main;

import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.main.Logout;

/**
 * Created by burakdoma on 24/11/2016.
 */

@Ignore
public class LogoutUITest extends BaseUITestCase{

    Logout logout;
    Login login;

    @Before
    public void beforeMethod() {
        super.beforeMethod();

        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        login.girisYap(dogruEposta, dogruSifre);
    }

    @Test
    public void cikisYap() {
        logout.cikisYap();
    }

    @After
    public void afterMethod() {
//        driver.quit();
    }

}
