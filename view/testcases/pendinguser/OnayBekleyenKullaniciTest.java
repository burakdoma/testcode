package tr.com.t2.bkmaaile.view.testcases.pendinguser;

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.common.ComboBox;
import tr.com.t2.bkmaaile.view.common.EmailAddressGenerator;
import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAdayEkleme;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.main.MenuNavigation;
import tr.com.t2.bkmaaile.view.componentobjects.pendinguser.OnayBekleyenKullanici;
import tr.com.t2.bkmaaile.view.componentobjects.usergroup.GrupIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.userstatuschange.ActivateUser;

import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 03/11/16.
 */
@Ignore
public class OnayBekleyenKullaniciTest extends BaseUITestCase {

    Login login;
    MenuNavigation menuNavigation;
    AdayIslemleriAnasayfa adayIslemleri;
    AdayIslemleriAdayEkleme adayIslemleriAdayEkleme;
    GrupIslemleriAnasayfa groupPage;
    OnayBekleyenKullanici onayBekleyenKullanici;
    ActivateUser activateUser = new ActivateUser();
    RandomGenerator randomGenerator = new RandomGenerator();
    NameGenerator nameGenerator = new NameGenerator();
    EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();

    @Before
    public void beforeMethod(){
        super.beforeMethod();
        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        menuNavigation = PageFactory.initElements(driver, MenuNavigation.class);
        adayIslemleri = PageFactory.initElements(driver, AdayIslemleriAnasayfa.class);
        adayIslemleriAdayEkleme = PageFactory.initElements(driver, AdayIslemleriAdayEkleme.class);
        groupPage = PageFactory.initElements(driver, GrupIslemleriAnasayfa.class);
        onayBekleyenKullanici = PageFactory.initElements(driver, OnayBekleyenKullanici.class);

        //Set up
        login.girisYap(dogruEposta, dogruSifre);
        menuNavigation.menuTikla("Kullanıcı İşlemleri");
    }

    // @Burak: Pager?
    // TODO: Dogrulama kodunu gonder @Burak ?


    //FILTERING
    @Test // Inc. tum filtrleme & parcali filtreleme & negatif filtreleme
    public void emailFiltreleme(){
        String email1 = emailAddressGenerator.validEmailGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();

        activateUser.kullaniciAktiveEtEmailIle(email1);
        activateUser.kullaniciAktiveEtEmailIle(email2);

        // Tum email filtreleme
        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.emailFiltrele(email1);
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 0, email1);

        //Parcali email filtreleme
        onayBekleyenKullanici.emailAddressAlaniniTemizle();
        onayBekleyenKullanici.emailFiltrele(StringUtils.left(email1,(email1.length())-2));
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 0, email1);

        //Negatif email filtreleme
        onayBekleyenKullanici.emailAddressAlaniniTemizle();
        onayBekleyenKullanici.emailFiltrele(email1);
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 0);
    }

    @Test // Inc. tum filtrleme & parcali filtreleme & negatif filtreleme
    public void adFiltreleme(){
        String ad1 = nameGenerator.randomNameGenerator();
        String soyad1 = nameGenerator.randomNameGenerator();
        String email1 = emailAddressGenerator.validEmailGenerator();

        String ad2 = nameGenerator.randomNameGenerator();
        String soyad2 = nameGenerator.randomNameGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad1, soyad1, email1, "5394032872");

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad2, soyad2, email2, "5394032872");

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");

        // Tum ad ile filtreleme
        onayBekleyenKullanici.adFiltrele(ad1);
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 1, ad1);

        // Parcali ad ile filtreleme
        onayBekleyenKullanici.adAlaniniTemizle();
        onayBekleyenKullanici.adFiltrele(StringUtils.left(ad1,(ad1.length())-1));
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 1, ad1);

        // Negatif filtreleme
        onayBekleyenKullanici.adAlaniniTemizle();
        onayBekleyenKullanici.adFiltrele(nameGenerator.randomNameGenerator());
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 0);
    }

    @Test // Inc. tum filtrleme & parcali filtreleme & negatif filtreleme
    public void soyadFiltreleme(){
        String ad1 = nameGenerator.randomNameGenerator();
        String soyad1 = nameGenerator.randomNameGenerator();
        String email1 = emailAddressGenerator.validEmailGenerator();

        String ad2 = nameGenerator.randomNameGenerator();
        String soyad2 = nameGenerator.randomNameGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad1, soyad1, email1, "5394032872");

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad2, soyad2, email2, "5394032872");

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");

        // Tum soyad ile filtreleme
        onayBekleyenKullanici.soyadFiltrele(soyad1);
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 1, soyad1);

        // Parcali soyad ile filtreleme
        onayBekleyenKullanici.soyadAlaniniTemizle();
        onayBekleyenKullanici.soyadFiltrele(StringUtils.left(soyad1,(soyad1.length())-1));
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 1);
        onayBekleyenKullanici.dataTableHucreIcerigiDogrulama(0, 1, soyad1);

        // Negatif filtreleme
        onayBekleyenKullanici.soyadAlaniniTemizle();
        onayBekleyenKullanici.soyadFiltrele(nameGenerator.randomNameGenerator());
        Assert.assertTrue(onayBekleyenKullanici.dataTableSatirSayisiGetir() == 0);
    }


    //SORTING
    @Test
    public void epostaSorting() {
        int i = 0;
        while (i < 10) {
            menuNavigation.menuTikla("Kullanıcı İşlemleri");

            String email = emailAddressGenerator.validEmailGenerator();
            adayIslemleriAdayEkleme.ekleButonunaTikla();
            adayIslemleriAdayEkleme.kullaniciEkle(email);
            activateUser.kullaniciAktiveEtEmailIle(email);
            i++;
        }
            menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
            onayBekleyenKullanici.emailHeaderTikla();
            onayBekleyenKullanici.emailSiralamaKontrolu();
    }

    @Test
    public void adSorting(){
        int i = 0;
        while (i < 10) {
            menuNavigation.menuTikla("Kullanıcı İşlemleri");

            String email = emailAddressGenerator.validEmailGenerator();
            String ad = nameGenerator.randomNameGenerator();

            adayIslemleriAdayEkleme.ekleButonunaTikla();
            adayIslemleriAdayEkleme.kullaniciEkle(email);
            activateUser.kullaniciAktiveEt(ad, "abc", email, "5394032872");
            i++;
        }

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.adHeaderTikla();
        onayBekleyenKullanici.adSiralamaKontrolu();
    }

    @Test
    public void soyadSorting(){
        int i = 0;
        while (i < 10) {
            menuNavigation.menuTikla("Kullanıcı İşlemleri");

            String email = emailAddressGenerator.validEmailGenerator();
            String soyad = nameGenerator.randomNameGenerator();

            adayIslemleriAdayEkleme.ekleButonunaTikla();
            adayIslemleriAdayEkleme.kullaniciEkle(email);
            activateUser.kullaniciAktiveEt(nameGenerator.randomNameGenerator(), soyad, email, "5394032872");
            i++;
        }

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.soyadHeaderTikla();
        onayBekleyenKullanici.soyadSiralamaKontrolu();
    }


    //DETAY
    @Test
    public void kullaniciDetayGoruntuleme(){
        String email = emailAddressGenerator.validEmailGenerator();
        String ad = nameGenerator.randomNameGenerator();
        String soyad = nameGenerator.randomNameGenerator();
        String telNo = "5394032872";

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email);
        activateUser.kullaniciAktiveEt(ad, soyad, email, telNo);

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.emailFiltrele(email);
        onayBekleyenKullanici.dataTableSatirdakiDetayButonuTikla(0);
        onayBekleyenKullanici.detayPopUpAcildiginiDogrula();

        Assert.assertTrue(onayBekleyenKullanici.popUpIcerigi().contains(ad));
        Assert.assertTrue(onayBekleyenKullanici.popUpIcerigi().contains(soyad));
        Assert.assertTrue(onayBekleyenKullanici.popUpIcerigi().contains(email));
        Assert.assertTrue(onayBekleyenKullanici.popUpIcerigi().contains(telNo));

        onayBekleyenKullanici.popUpKapatButonuTikla();
        onayBekleyenKullanici.detayPopUpKapandiginiDogrula();
    }


    //KULLANICIISLEMLERI
    @Test
    public void kullaniciOnaylama(){
        String email = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email);

        activateUser.kullaniciAktiveEtEmailIle(email);

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.kullaniciOnayla(email);
        Assert.assertTrue(onayBekleyenKullanici.mesajiKutusuIcerigiGetir().contains(onayBekleyenKullanici.kullaniciOnaylandiMesaji));
        // TODO: Onay ve Red caseleri icin Kullanici islemlerinde kullaniciyi aratarak da onayla? @Burak: ya da bu surec testlerinde mi olmali?
    }

    @Test
    public void kullaniciReddetme(){
        String email = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email);

        activateUser.kullaniciAktiveEtEmailIle(email);

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.kullaniciReddetRedNedeniSecerek(email, "Girilen bilgiler hatalıdır.");
        Assert.assertTrue(onayBekleyenKullanici.mesajiKutusuIcerigiGetir().contains(onayBekleyenKullanici.kullaniciReddedildiMesaji));
    }

    @Test
    public void kullaniciReddetmeRedNedeniDiger(){
        String email = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email);

        activateUser.kullaniciAktiveEtEmailIle(email);

        menuNavigation.menuTikla("Onay Bekleyen Kullanıcı İşlemleri");
        onayBekleyenKullanici.kullaniciReddetRedNedeniDiger(email, randomGenerator.randomAlphaNumericGeneratorSetLength(99));
        Assert.assertTrue(onayBekleyenKullanici.mesajiKutusuIcerigiGetir().contains(onayBekleyenKullanici.kullaniciReddedildiMesaji));
    }

    // Todo: @Burak SUREC Testleri??

//    @After
//    public void afterMethod(){
//        super.afterMethod();
//    }

}
