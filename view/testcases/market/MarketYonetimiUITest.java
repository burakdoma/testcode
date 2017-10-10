package tr.com.t2.bkmaaile.view.testcases.market;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import tr.com.t2.bkmaaile.view.componentobjects.market.MarketYonetimiAnaSayfa;
import tr.com.t2.bkmaaile.view.componentobjects.usergroup.GrupIslemleriAnasayfa;

import java.io.IOException;

/**
 * Created by burakdoma on 28/10/2016.
 */

@Ignore
public class MarketYonetimiUITest extends BaseUITestCase{


    Login login;
    MarketYonetimiAnaSayfa marketYonetim;

    @Before
    public void beforeMethod(){

        super.beforeMethod();

        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        login.girisYap(dogruEposta, dogruSifre);

        driver.get(marketPage);
        marketYonetim = PageFactory.initElements(driver, MarketYonetimiAnaSayfa.class);

    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: "+description.getMethodName());
        }

        protected void finished(Description description) {
            System.out.println("Finished test: "+description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
//            watchedLog+= description + "\n";
            System.out.println(description + " " + "fail!\n");
        }

        @Override
        protected void succeeded(Description description) {
//            watchedLog+= description + " " + "success!\n";
            System.out.println(description + " " + "success!\n");
        }
    };

    @Test // başarılı ürün ekle
    public void yeniUrunEkleBasarili() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);
    }

    @Test // çoklu ürün ekle
    public void yeniCokluUrunEkleBasarili() throws InterruptedException {

        marketYonetim.yeniCokluUrunEkle(25);
    }

//    @Test // kod alanı eksik ürün ekle
    @Ignore // TODO : Mert burayı düzeltecek
    public void yeniUrunEkleKodAlaniBos() throws IOException, InterruptedException {

        String kodBilgisi = "";
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.hataMesajiGosterimKontrolu(marketYonetim.kodAlanZorunlulugu);
        marketYonetim.zorunluAlanKontrolu();
    }

//    @Test // kod alanı karakter sınırı
    @Ignore // TODO : Mert burayı düzeltecek
    public void yeniUrunEkleKodAlaniEksik() throws IOException, InterruptedException {

        String kodBilgisi = "U1";
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.hataMesajiGosterimKontrolu(marketYonetim.kodKarakterSiniri);
        marketYonetim.zorunluAlanKontrolu();
    }

    @Test // başlık alanı eksik ürün ekle
    public void yeniUrunEkleBaslikAlaniBos() throws IOException, InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = "";
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.hataMesajiGosterimKontrolu(marketYonetim.baslikAlanZorunlulugu);
        marketYonetim.zorunluAlanKontrolu();
    }

    @Test // açıklama alanı eksik ürün ekle - hata alma
    public void yeniUrunEkleAciklamaAlaniBos() {}

    @Test // fiyat alanı eksik ürün ekle
    public void yeniUrunEkleFiyatAlaniBos() throws IOException, InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, "");
        marketYonetim.kaydetButonunaBas();
        marketYonetim.hataMesajiGosterimKontrolu(marketYonetim.fiyatDegerZorunlulugu);
        marketYonetim.zorunluAlanKontrolu();
    }

    @Test // fiyat alanına text girişi yap - hata al
    public void yeniUrunEkleFiyatAlaniHatali() throws InterruptedException, IOException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, "text");
        marketYonetim.kaydetButonunaBas();
        marketYonetim.hataMesajiGosterimKontrolu(marketYonetim.fiyatDegerZorunlulugu);
        marketYonetim.zorunluAlanKontrolu();
    }

    // TODO :
    @Test // fotoğraf alanı eksik ürün ekle - hata alma
    public void yeniUrunEkleFotografYuklenmemis() {}

    @Test // ürün eklemekten vazgeç
    public void urunEklemektenVazgec() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.vazgecButonunaBas();
        marketYonetim.alanlarSifirlandiKontrolEt();
    }

    // TODO: fotograf pop-up ac

    @Test // Hediye başlık filtreleme
    public void urunBaslikFiltrelemeRastgele() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);

        marketYonetim.urunBaslikFiltreleme(baslikBilgisi);
        marketYonetim.baslikKolonuFiltreKontrol(baslikBilgisi);
    }

    @Ignore
//    @Test // Hediye miktar filtreleme
    public void urunMiktarFiltrelemeRastgele() throws InterruptedException {

//        String kodBilgisi = marketYonetim.kodBilgisiUret();
//        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
//        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
//        float fiyatBilgisi = marketYonetim.fiyatBilgisiUret();
//
//        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, Float.toString(fiyatBilgisi));
//        marketYonetim.kaydetButonunaBas();
//        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);
//
////        marketYonetim.urunMiktarFiltreleme(Integer.toString(miktarBilgisi));
////        marketYonetim.miktarKolonuFiltreKontrol(Integer.toString(miktarBilgisi));
    }

    @Test // Hediye fiyat filtreleme
    public void urunFiyatFiltrelemeRastgele() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);

        marketYonetim.urunFiyatFiltreleme(fiyatBilgisi);
        marketYonetim.fiyatKolonuFiltreKontrol(fiyatBilgisi);
    }

    @Test // ürün sil
    public void yeniEklenenUrunuSil() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);

        marketYonetim.urunBaslikFiltreleme(baslikBilgisi);
        marketYonetim.baslikKolonuFiltreKontrol(baslikBilgisi);

        marketYonetim.dataTableSatirdakiSilButonunaTikla(1);
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunSilmeBasarili);
    }

    @Test // ürün güncelle
    public void yeniEklenenUrunuGuncelle() throws InterruptedException {

        String kodBilgisi = marketYonetim.kodBilgisiUret();
        String baslikBilgisi = marketYonetim.baslikBilgisiUret();
        String aciklamaBilgisi = marketYonetim.aciklamaBilgisiUret();
        String fiyatBilgisi = marketYonetim.fiyatBilgisiUret();

        marketYonetim.yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
        marketYonetim.kaydetButonunaBas();
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunEklemeBasarili);

        marketYonetim.urunBaslikFiltreleme(baslikBilgisi);
        marketYonetim.baslikKolonuFiltreKontrol(baslikBilgisi);

        marketYonetim.dataTableSatirdakiGuncelleButonunaTikla(1);
//        marketYonetim.alanlarinIceriginiKontrolEt(kodBilgisi, baslikBilgisi, aciklamaBilgisi, Integer.toString(miktarBilgisi), Float.toString(fiyatBilgisi));
        // TODO : Devamı yok !!
        marketYonetim.bilgilendirmeMesajiGosterimKontrolu(marketYonetim.urunGuncellemeBasarili);
    }

    @Test // başlık kolonuna göre sıralama
    public void urunBaslikSiralama() throws InterruptedException {

        marketYonetim.dataTableBaslikSutunundakiAsagiYukariOkaBas();
        marketYonetim.dataTableKolonSiralamaKontrol(2);
    }

//    @Test // fiyat kolonuna göre sıralama
    @Ignore // TODO : common da bulunan sorting de sıkıntı var
    public void urunFiyatSiralama() throws InterruptedException {

        marketYonetim.dataTableFiyatSutunundakiAsagiYukariOkaBas();
        marketYonetim.dataTableKolonSiralamaKontrol(5);
    }

    @After
    public void afterMethod() {
        driver.quit();
    }

}
