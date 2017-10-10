package tr.com.t2.bkmaaile.view.testcases.candidateuser;

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
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
 * Created by burakdoma on 18/10/16.
 */
@Ignore
public class AdayKullaniciIslemleriTest extends BaseUITestCase {

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
        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
    }

    // Pager
        // (-)

    // TODO: Dogrulama kodunu gonder @Burak ?


    //FILTERING
    @Test
    public void emailFiltreleme(){
        String email1 = emailAddressGenerator.validEmailGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();

        adayIslemleri.emailFiltrele(email1);
        Assert.assertTrue(adayIslemleri.dataTableSatirSayisiGetir() == 1);
        adayIslemleri.dataTableHucreIcerigiDogrulama(0, 0, email1);
    }

    @Test
    public void emailParcaliFiltreleme(){
        String email1 = emailAddressGenerator.validEmailGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();

        adayIslemleri.emailFiltrele(StringUtils.left(email1,(email1.length())-2));
        Assert.assertTrue(adayIslemleri.dataTableSatirSayisiGetir() == 1);
        adayIslemleri.dataTableHucreIcerigiDogrulama(0, 0, email1);
    }

    @Test
    public void emailFiltrelemeNegatif(){
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(emailAddressGenerator.validEmailGenerator());
        adayIslemleri.emailFiltrele(randomGenerator.randomAlphaNumericGeneratorSetLength(30));
        Assert.assertTrue(adayIslemleri.dataTableSatirSayisiGetir() == 0);
    }

    @Test // @Burak - sadece iki tur durum ile filtrelendi - tum caselere bakilmali mi?
    public void durumFiltreleme(){
        String ad = nameGenerator.randomNameGenerator();
        String soyad = nameGenerator.randomNameGenerator();
        String email1 = emailAddressGenerator.validEmailGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad, soyad, email1, "5394032872");

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();

        adayIslemleri.durumSec(menuNavigation.onayBekleyenKullaniciIslemleri);
        adayIslemleri.emailFiltrele(email1);
        Assert.assertTrue(adayIslemleri.dataTableSatirSayisiGetir() == 1);
        adayIslemleri.dataTableHucreIcerigiDogrulama(0, 0, email1);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.durumSec("Üye olabilir");
        adayIslemleri.emailFiltrele(email2);
        System.out.println(email2);
        Assert.assertTrue(adayIslemleri.dataTableSatirSayisiGetir() == 1);
        adayIslemleri.dataTableHucreIcerigiDogrulama(0, 0, email2);
    }

    @Test
    public void groupFiltreleme(){
        String ad = nameGenerator.randomNameGenerator();
        String soyad = nameGenerator.randomNameGenerator();
        String email1 = emailAddressGenerator.validEmailGenerator();
        String grupIsmi = nameGenerator.randomNameGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad, soyad, email1, "5394032872");

        menuNavigation.menuTikla(menuNavigation.onayBekleyenKullaniciIslemleri);
        onayBekleyenKullanici.kullaniciOnayla(email1);

        menuNavigation.menuTikla(menuNavigation.kullaniciGrupIslemleri);
        groupPage.grupEkle(grupIsmi);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.kullaniciGrupAta(email1, grupIsmi);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.grupSecIsimIle(grupIsmi);
        adayIslemleri.dataTableKolonIcerikKontrolu(grupIsmi, 2);

// TODO: Farkli bir gruba ait ikinci kullanici olustur, kullanicinin grup filtrede gelmedigini gor - filtrenin duzgun calistigini dogrula

    }


    //SORTING
    @Test
    public void epostaSorting(){
        adayIslemleriAdayEkleme.cokluRandomKullaniciEkle(10);
        adayIslemleri.emailHeaderTikla();
        adayIslemleri.emailSiralamaKontrolu();
    }

    @Test
    public void durumSorting(){
        String ad = nameGenerator.randomNameGenerator();
        String soyad = nameGenerator.randomNameGenerator();
        String email1 = emailAddressGenerator.validEmailGenerator();
        String email2 = emailAddressGenerator.validEmailGenerator();

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email1);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();
        activateUser.kullaniciAktiveEt(ad, soyad, email1, "5394032872");

        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(email2);
        adayIslemleri.kullaniciEklemeBasariliNotificationGoruntuleme();

        adayIslemleri.durumHeaderTikla();
        adayIslemleri.durumSiralamaKontrolu();
        // TODO: Pager la devam et, diger sayfalari da dolas
    }

    @Test
    public void kullaniciGrubuSorting(){
        groupPage.cokluGrupEkle(10);
        adayIslemleri.kullaniciGrupHeaderTikla();
        adayIslemleri.grupSiralamaKontrolu();
    }


    //SIL
    @Test // Aktif olmayan kullanici
    public void kullaniciSil(){
        String eposta = emailAddressGenerator.validEmailGenerator();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(eposta);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        adayIslemleri.kullaniciSilAktifOlmayan(eposta);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
        adayIslemleri.kullanicininSilindiginiDogrula(eposta);
    }

    @Test
    public void aktifKullaniciSil(){
        String eposta = emailAddressGenerator.validEmailGenerator();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(eposta);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        activateUser.kullaniciAktiveEtEmailIle(eposta);
        menuNavigation.menuTikla(menuNavigation.onayBekleyenKullaniciIslemleri);
        onayBekleyenKullanici.kullaniciOnayla(eposta);

        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.aktifKullaniciSil(eposta);
        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.aktifKullanicininPasifeGectiginiDogrula(eposta);
    }


    //DUZENLE // Duzenleme - email validation? @Burak, @Mete
    @Test // Aktif olmayan kullanici
    public void kullaniciEmailDuzenleme(){
        String eposta = emailAddressGenerator.validEmailGenerator();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(eposta);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        String epostaGuncel = emailAddressGenerator.validEmailGenerator();

        adayIslemleri.emailFiltrele(eposta);
        adayIslemleri.dataTableSatirdakiDuzenluButonunaTikla(0);
        adayIslemleri.kullaniciEmailDuzenle(eposta, epostaGuncel);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.emailFiltrele(eposta);
        Assert.assertEquals(adayIslemleri.dataTableSatirSayisiGetir(), 0);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.emailFiltrele(epostaGuncel);
        Assert.assertEquals(adayIslemleri.dataTableSatirSayisiGetir(), 1);
    }

    @Test // Aktif Kullanici Grup Atama - Grup Atama altinda cover edilmistir. (Grup guncelleme cover edilmemistir)
    public void aktifKullaniciEmailDuzenleme(){
        String eposta = emailAddressGenerator.validEmailGenerator();
        adayIslemleriAdayEkleme.ekleButonunaTikla();
        adayIslemleriAdayEkleme.kullaniciEkle(eposta);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        activateUser.kullaniciAktiveEtEmailIle(eposta);
        menuNavigation.menuTikla(menuNavigation.onayBekleyenKullaniciIslemleri);
        onayBekleyenKullanici.kullaniciOnayla(eposta);

        menuNavigation.menuTikla(menuNavigation.kullaniciIslemleri);
        adayIslemleri.aktifKullaniciEmailDuzenleme(eposta);
    }

    // TODO: Reddedilen kullanici icin de yaz // Kullanici reddet fonksiyonundan sonra

    // Todo: @Burak SUREC Testleri??


    @After
    public void afterMethod(){
        super.afterMethod();
    }
}



// **IMPROVEMENT LIST**
// Kullanici duzenleme isleminde "Kullanici kayit edildi mesaji veriyor
// Ileriki sayfalardan herhangi bir item i silince ilk sayfaya atiyor