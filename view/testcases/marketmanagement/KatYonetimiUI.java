package tr.com.t2.bkmaaile.view.testcases.marketmanagement;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.marketmanagement.KatYonetimi;


/**
 * Created by burakdoma on 29/11/2016.
 */
@Ignore
public class KatYonetimiUI extends BaseUITestCase{

    KatYonetimi katYonetimi;
    Login login;

    @Before
    public void beforeMethod(){

        super.beforeMethod();
        driver.get(loginCoinGeneratorPage);
        login = PageFactory.initElements(driver, Login.class);
        login.girisYap(dogruEposta, dogruSifre);
        driver.get(marketManagement);
        katYonetimi = PageFactory.initElements(driver, KatYonetimi.class);
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
    public void yeniKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.bilgilendirmeMesajiGosterimKontrolu(katYonetimi.marketEklemeBasarili);
    }

    @Test
    public void yeniKatTanimlamaCoklu() {
        for(int i=0; i<10; i++)
            yeniKatTanimlama();
    }

    @Test
    public void isimsizKatTanimlama() {

        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketIsmiGereklidirHatasi);
    }

    @Test
    public void portBilgisizKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketPortDegeriHatasi);
    }

    @Test
    public void IP1alaniEksikKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir("", katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketIP1FormatHatasi);
   }

    @Test
    public void IP2alaniEksikKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), "", katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketIP2FormatHatasi);
    }

    @Test
    public void IP3alaniEksikKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), "", katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketIP3FormatHatasi);
    }

    @Test
    public void IP4alaniEksikKatTanimlama() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), "");
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.hataMesajiGosterimKontrolu(katYonetimi.marketIP4FormatHatasi);
    }

    @Test
    public void yeniKatTanimlamaVazgec() {

        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketVazgecButonunaBas();
        katYonetimi.alanlarBosmuKontrolEt();
    }

    @Test
    public void mevcutKatiGuncelleme() {

        yeniKatTanimlama();
        katYonetimi.dataTableSatirdakiGuncelleButonunaTikla(0);
        katYonetimi.marketIsmiSil();
        katYonetimi.marketIsmiGir(katYonetimi.marketIsmiUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.bilgilendirmeMesajiGosterimKontrolu(katYonetimi.marketGuncellemeBasarili);
    }

    @Test
    public void mevcutKatiSilme() {

        yeniKatTanimlama();
        katYonetimi.dataTableSatirdakiSilButonunaTikla(0);
        katYonetimi.bilgilendirmeMesajiGosterimKontrolu(katYonetimi.marketSilmeBasarili);
        katYonetimi.alanlarBosmuKontrolEt();
    }

    @Test
    public void katIsmiFiltreleme() {

        String marketIsmi = katYonetimi.marketIsmiUret();
        katYonetimi.marketIsmiGir(marketIsmi);
        katYonetimi.marketTanimiGir(katYonetimi.marketTanimiUret());
        katYonetimi.marketIPAdresiGir(katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret(), katYonetimi.ipDegeriUret());
        katYonetimi.marketPortBilgisiGir(katYonetimi.portDegeriUret());
        katYonetimi.marketKaydetButonunaBas();
        katYonetimi.bilgilendirmeMesajiGosterimKontrolu(katYonetimi.marketEklemeBasarili);

        katYonetimi.marketIsmiFiltreleme(marketIsmi);
        katYonetimi.filtreliAlanGirilenKriterIleMiBasliyor(katYonetimi.dataTableKayitSayisi(), marketIsmi);
    }




    @After
    public void afterMethod() {
        driver.quit();
    }

}