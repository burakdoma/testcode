package tr.com.t2.bkmaaile.view.testcases.moneytransfer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.common.EmailAddressGenerator;
import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAdayEkleme;
import tr.com.t2.bkmaaile.view.componentobjects.candidateuser.AdayIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.main.MenuNavigation;
import tr.com.t2.bkmaaile.view.componentobjects.moneytransfer.ParaAktarimi;
import tr.com.t2.bkmaaile.view.componentobjects.pendinguser.OnayBekleyenKullanici;
import tr.com.t2.bkmaaile.view.componentobjects.usergroup.GrupIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.userstatuschange.ActivateUser;

import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 29/11/16.
 */
@Ignore
public class ParaAktarimiTest extends BaseUITestCase {

    Login login;
    ParaAktarimi paraAktarimi;
    MenuNavigation menuNavigation;
    GrupIslemleriAnasayfa groupPage;
    RandomGenerator randomGenerator = new RandomGenerator();

    @Before
    public void beforeMethod(){
        super.beforeMethod();
        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        menuNavigation = PageFactory.initElements(driver, MenuNavigation.class);
        groupPage = PageFactory.initElements(driver, GrupIslemleriAnasayfa.class);
        paraAktarimi = PageFactory.initElements(driver, ParaAktarimi.class);

        //Set up
        login.girisYap(dogruEposta, dogruSifre);
        menuNavigation.menuTikla(menuNavigation.paraAktarimi);
    }

    @Test // Case always fails, limitleri guncelle
    public void grupIsmiIleParaAktarma(){
        paraAktarimi.rastgeleGrupIsmiSec();
        String aktarilacakMiktar = Integer.toString(randomGenerator.randomNumberGeneratorBetween(1, 10000000));
        paraAktarimi.paraAktarma(aktarilacakMiktar, "1");
        //TODO mesajin icerigini de dogrula - su kadar kullanici
    }

    @Test // Case always fails, limitleri guncelle
    public void kullaniciSecimiIleParaAktarma(){
        //TODO: tablo row sayisi kadar rnd generation
        int rnd = randomGenerator.randomNumberGeneratorBetween(1,10);
        paraAktarimi.rastgeleCokluKullaniciSec(rnd);
        String aktarimYapilacakKullaniciSayisi = Integer.toString(paraAktarimi.seciliKullaniciSayisiGetir());
        String aktarilacakMiktar = Integer.toString(randomGenerator.randomNumberGeneratorBetween(1, 10000000));
        paraAktarimi.paraAktarma(aktarilacakMiktar, aktarimYapilacakKullaniciSayisi );
    }

//    @Test
//    public void cokluGrubaParaAktarma(){
//    }

    @Test
    public void grupKullaniciSayisiKontrolu() throws InterruptedException {
        menuNavigation.menuTikla(menuNavigation.kullaniciGrupIslemleri);
        String kullanicisiOlanGrup = groupPage.kullanicisiOlanGrupGetir();
        String grupKullaniciSayisi = groupPage.grupKullaniciSayisiGetir(kullanicisiOlanGrup);

        menuNavigation.menuTikla(menuNavigation.paraAktarimi);
        paraAktarimi.grupIsmiSec(kullanicisiOlanGrup);
        int seciliKullaniciSayisi = paraAktarimi.seciliKullaniciSayisiGetir();
        Assert.assertTrue(Integer.toString(seciliKullaniciSayisi).equals(grupKullaniciSayisi));
    }

    @Test
    public void gruptaBulunmayanKullaniciSecimi(){
        //TODO: tablo row sayisi kadar rnd generation
        Assert.assertTrue(paraAktarimi.seciliGrupSayisiGetir()==0);
        paraAktarimi.rastgeleGrupIsmiSec();
        Assert.assertTrue(paraAktarimi.seciliGrupSayisiGetir()==1);
        paraAktarimi.rastgeleKullaniciSec();
        Assert.assertTrue(paraAktarimi.seciliGrupSayisiGetir()==0);
    }

    @Test
    public void seciliKullancilarinAitOlmadigiGrupSecimi(){
        int rnd = randomGenerator.randomNumberGeneratorBetween(1,10);
        paraAktarimi.rastgeleCokluKullaniciSec(rnd);
        Assert.assertTrue(paraAktarimi.seciliKullaniciSayisiGetir()!=0);
        paraAktarimi.rastgeleGrupIsmiSec();
        Assert.assertTrue(paraAktarimi.seciliGrupSayisiGetir()==1);
        paraAktarimi.grupTumSecimleriKaldir();
        Assert.assertTrue(paraAktarimi.seciliKullaniciSayisiGetir()==0);
    }
}
