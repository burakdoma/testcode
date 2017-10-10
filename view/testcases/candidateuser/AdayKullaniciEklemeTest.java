package tr.com.t2.bkmaaile.view.testcases.candidateuser;

import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.common.EmailAddressGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAdayEkleme;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.main.MenuNavigation;

/**
 * Created by burakdoma on 18/10/16.
 */
@Ignore
public class AdayKullaniciEklemeTest extends BaseUITestCase {

    Login login;
    MenuNavigation menuNavigation;
    AdayIslemleriAnasayfa adayIslemleri;
    AdayIslemleriAdayEkleme adayIslemleriAdayEkleme;
    EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();

    @Before
    public void beforeMethod(){
        super.beforeMethod();
        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        menuNavigation = PageFactory.initElements(driver, MenuNavigation.class);
        adayIslemleri = PageFactory.initElements(driver, AdayIslemleriAnasayfa.class);
        adayIslemleriAdayEkleme = PageFactory.initElements(driver, AdayIslemleriAdayEkleme.class);

        //Set up
        login.girisYap(dogruEposta, dogruSifre);
        menuNavigation.menuTikla("Kullanıcı İşlemleri");
        adayIslemleriAdayEkleme.ekleButonunaTikla();
    }

    // Kullanici ekleme basarili
    // Kullanici ekleme alan validasyon

    // Mevcut kullaniciyi ayni email ile ekle

    // Kullanici dogru bilgi ile kaydediliyor mu?
    // Ekleme pop up kapa (x ile, "kapat" butonu ile)

    @Test
    public void kullaniciEklemeBasarili(){
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddressGenerator.validEmailGenerator());
        adayIslemleri.mesajGoruntuleme(adayIslemleri.kullaniciEklemeBasariliMesaji);
    }

    @Test
    public void kullaniciEklemeBasariliDataDogrulama(){
        String emailAddress = emailAddressGenerator.validEmailGenerator();
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddress);
        adayIslemleri.mesajGoruntuleme(adayIslemleri.kullaniciEklemeBasariliMesaji);
        adayIslemleri.emailFiltrele(emailAddress);
        Assert.assertTrue(adayIslemleri.dataTableIceriginBulunduguSatirBilgisiGetir(emailAddress).contains("Üye olabilir"));
        adayIslemleri.dataTableHucreIcerigiDogrulama(0, 2, "");
    }

    @Test
    public void kullaniciEklemeBosData(){
        adayIslemleriAdayEkleme.kullaniciEkle("");
        adayIslemleriAdayEkleme.hataMesajiGoruntuleme(adayIslemleriAdayEkleme.emailGecersizHataMesaji);
    }

    @Test // "Invalid email address: no '@' sign")
    public void kullaniciEklemeGecersizEmailNoAtSign(){
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddressGenerator.randomInvalidEmailGeneratorWithNoAtSign());
        adayIslemleriAdayEkleme.hataMesajiGoruntuleme(adayIslemleriAdayEkleme.emailGecersizHataMesaji);
    }

    @Test // "Invalid email address: no domain")
    public void kullaniciEklemeGecersizEmailNoDomain(){
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddressGenerator.randomInvalidEmailGeneratorWithNoDomain());
        adayIslemleriAdayEkleme.hataMesajiGoruntuleme(adayIslemleriAdayEkleme.emailGecersizHataMesaji);
    }

    @Test // "Invalid email address: longer than max length") //TODO: confirm max length
    public void kullaniciEklemeGecersizEmailLongerThanMaxLength(){
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddressGenerator.randomInvalidEmailGeneratorLargerThanMaxLengthAllowed());
        adayIslemleriAdayEkleme.hataMesajiGoruntuleme(adayIslemleriAdayEkleme.emailGecersizHataMesaji);
    }

    @Test // Ayni email ile kullanici ekleme
    public void kullaniciEklemeAyniEmailIle(){
        String emailAdress = emailAddressGenerator.validEmailGenerator();
        System.out.println(emailAdress);
        adayIslemleriAdayEkleme.kullaniciEkle(emailAdress);
        adayIslemleri.mesajGoruntuleme(adayIslemleri.kullaniciEklemeBasariliMesaji);
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(emailAdress);
        adayIslemleriAdayEkleme.hataMesajiGoruntuleme("E-Posta kullanımda");
    }

    @Test
    public void kullaniciEklemeCancel(){
        adayIslemleriAdayEkleme.kapatButonunaTikla();
        adayIslemleriAdayEkleme.popUpKapandiginiDogrula();
    }

//
//    @After
//    public void afterMethod(){
//        super.afterMethod();
//    }

}

