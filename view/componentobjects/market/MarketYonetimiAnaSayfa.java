package tr.com.t2.bkmaaile.view.componentobjects.market;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tr.com.t2.bkmaaile.view.common.DataTable;
import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;

import java.io.IOException;

/**
 * Created by burakdoma on 28/10/2016.
 */
public class MarketYonetimiAnaSayfa {

    final WebDriver driver;
    public MarketYonetimiAnaSayfa(WebDriver driver) { this.driver = driver; }

    NameGenerator nameGenerator = new NameGenerator();
    RandomGenerator randomGenerator = new RandomGenerator();
    DataTable dataTable = new DataTable();

    @FindBy (id="mainForm:inputItemCode")
    private WebElement kodText;

    @FindBy (id="mainForm:inputItemTitle")
    private WebElement baslikText;

    //name=mainForm:inputItemTitle[
    @FindBy (css="input[class='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-error'")
    private WebElement zorunluAlan;

    @FindBy (id="mainForm:inputItemDescription")
    private WebElement aciklamaText;

    @FindBy (id="mainForm:inputItemPrice_input")
    private WebElement fiyatText;

    @FindBy (css="span[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left ui-fileupload-choose'")
    private WebElement fotografYuklemeButonu;

    @FindBy (id="mainForm:btnSave")
    private WebElement kaydetButonu;

    @FindBy (id="mainForm:btnCancel")
    private WebElement vazgecButonu;

    @FindBy (id="mainForm:marketItemList_data")
    private WebElement tabloTbodyId;

    @FindBy (id="mainForm:marketItemList")
    private WebElement dataTableId;

    @FindBy (id="mainForm:messages")
    private WebElement bilgilendirmeMesajAlani;

    @FindBy (className="ui-messages-error-summary")
    private WebElement hataMesajAlani;

    @FindBy (id="mainForm:marketItemList:colCode:filter")
    private WebElement kodFiltresi;

    @FindBy (id="mainForm:marketItemList:colTitle:filter")
    private WebElement baslikFiltresi;

    @FindBy (id="mainForm:marketItemList:colQuantity")
    private WebElement miktarFiltresi;

    @FindBy (id="mainForm:marketItemList:colPrice:filter")
    private WebElement fiyatFiltresi;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-n'")
    private WebElement yukariOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s'")
    private WebElement asagiOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s'")
    private WebElement asagiYukariOk;

    // güncelle butonu = btnEditMarketItem
    // sil butonu = btnDeleteMarketItem

    public String urunEklemeBasarili = "Hediye eklendi";
    public String urunEklemeBasarisiz = "kodlu hediye önceden kaydedilmiş";
    public String urunSilmeBasarili = "Hediye silindi";
    public String urunGuncellemeBasarili = "Hediye güncellendi";
    public String kodKarakterSiniri = "Hediye kodu en az 3 karakter olmalı";
    public String kodAlanZorunlulugu = "Kod alanı gereklidir";
    public String baslikAlanZorunlulugu = "Başlık alanı gereklidir";
    public String fiyatDegerZorunlulugu = "Fiyat alanı gereklidir";

    public void kodBilgisiGir(String kodBilgisi) {

        kodText.click();
        kodText.sendKeys(kodBilgisi);
    }

    public void baslikBilgisiGir(String baslikBilgisi) {

        baslikText.click();
        baslikText.sendKeys(baslikBilgisi);
    }

    public void aciklamaBilgisiGir(String aciklamaBilgisi) {

        aciklamaText.click();
        aciklamaText.sendKeys(aciklamaBilgisi);
    }

    public void fiyatBilgisiGir(String fiyatBilgisi) {

        fiyatText.click();
        fiyatText.sendKeys(fiyatBilgisi);
    }

    public void fotografYukle() {}

    public void yeniUrunEkle(String kodBilgisi, String baslikBilgisi, String aciklamaBilgisi, String fiyatBilgisi) throws InterruptedException {

        kodBilgisiGir(kodBilgisi);
        baslikBilgisiGir(baslikBilgisi);
        aciklamaBilgisiGir(aciklamaBilgisi);
        fiyatBilgisiGir(fiyatBilgisi);
        //fotografYukle();
        milisaniyeBekle(500);
    }

    public void yeniCokluUrunEkle(int rastgeleUrunSayisi) throws InterruptedException {

        for (int i=0; i<rastgeleUrunSayisi; i++) {

            String kodBilgisi = kodBilgisiUret();
            String baslikBilgisi = baslikBilgisiUret();
            String aciklamaBilgisi = aciklamaBilgisiUret();
            String fiyatBilgisi = fiyatBilgisiUret();

            yeniUrunEkle(kodBilgisi, baslikBilgisi, aciklamaBilgisi, fiyatBilgisi);
            kaydetButonunaBas();
            bilgilendirmeMesajiGosterimKontrolu(urunEklemeBasarili);
        }
    }

    public String kodBilgisiUret() {

        String uretilenKodBilgisi = nameGenerator.randomNameGenerator();
        return uretilenKodBilgisi;
    }

    public String baslikBilgisiUret() {

        String uretilenBaslikBilgisi = nameGenerator.randomNameGenerator();
        return uretilenBaslikBilgisi;
    }

    public String aciklamaBilgisiUret() {

        String uretilenAciklamaBilgisi = nameGenerator.randomNameGenerator();
        return uretilenAciklamaBilgisi;
    }

    public String fiyatBilgisiUret() {

        float uretilenFiyatBilgisi = (float) randomGenerator.randomNumberGeneratorBetween(1,100);
        return String.format("%.2f",uretilenFiyatBilgisi);
    }

    public void cokluUrunEkle(String kodBilgisi, String baslikBilgisi, String aciklamaBilgisi, float fiyatBilgisi) {

    }

    public void kaydetButonunaBas() {
        kaydetButonu.click();
    }

    public void vazgecButonunaBas() {
        vazgecButonu.click();
    }

    public void alanlarSifirlandiKontrolEt() throws InterruptedException {
        milisaniyeBekle(500);
        Assert.assertTrue(kodText.getText().isEmpty());
        Assert.assertTrue(baslikText.getText().isEmpty());
        Assert.assertTrue(aciklamaText.getText().isEmpty());
        Assert.assertTrue(fiyatText.getText().isEmpty());
    }

    public void alanlarinIceriginiKontrolEt(String kodBilgisi, String baslikBilgisi, String aciklamaBilgisi, String miktarBilgisi, String fiyatBilgisi ) throws InterruptedException {
        milisaniyeBekle(5000);
        //String okunan = "Burak";
        //okunan = kodText.getCssValue("type");
        //Assert.assertTrue(kodText.getText().contains(kodBilgisi));
        //Assert.assertTrue(baslikText.getText().contains(baslikBilgisi));
        //Assert.assertTrue(aciklamaText.getText().contains(aciklamaBilgisi));
        //Assert.assertTrue(miktarText.getText().contains(miktarBilgisi));
        //Assert.assertTrue(fiyatText.getText().contains(fiyatBilgisi));
    }






    public String dataTableSatirIcerik (int ilgiliSatir) {
        return dataTable.dataTableSatirIcerikGetir(tabloTbodyId, ilgiliSatir);
    }

    public int dataTableDataninBulunduguSatiriGetir (String ilgiliIcerik) {
        return dataTable.dataTableIceriginBulunduguSatirGetir(tabloTbodyId, ilgiliIcerik);
    }

    public void dataTableSatirdakiGuncelleButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnEditMarketItem");
    }

    public void dataTableSatirdakiSilButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnDeleteMarketItem");
    }

    public int dataTableKayitSayisi () {
        return dataTable.dataTableToplamSatirSayisiGetir(tabloTbodyId);
    }

    public String dataTableSatirdakiKolonIceriginiGetir (int ilgiliSatir) {
        return dataTable.dataTableSatirdakiIlkKolonunIceriginiGetir(tabloTbodyId, ilgiliSatir);
    }

    public void dataTableFiyatSutunundakiAsagiYukariOkaBas() throws InterruptedException {
        dataTable.dataTableSutundakiIkonaTikla(dataTableId, "mainForm:marketItemList:colPrice", "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s");
        milisaniyeBekle(1000);
    }

    public void dataTableBaslikSutunundakiAsagiYukariOkaBas() throws InterruptedException {
        dataTable.dataTableSutundakiIkonaTikla(dataTableId, "mainForm:marketItemList:colTitle", "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s");
        milisaniyeBekle(1000);
    }

    public void dataTableFiyatSutunundakiAsagiOkaBas() {
        System.out.println(asagiOk.getText());
        dataTable.dataTableSutundakiIkonaTikla(dataTableId, "mainForm:marketItemList:colCode", "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s");
    }

    public void dataTableFiyatSutunundakiYukariOkaBas() {
        dataTable.dataTableSutundakiIkonaTikla(dataTableId, "mainForm:marketItemList:colCode", "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-n");
    }

    public void dataTableKolonSiralamaKontrol (int ilgiliSutun){
        dataTable.dataTableKolonOrderKontrolu(tabloTbodyId, ilgiliSutun);
    }

    public void kodKolonuFiltreKontrol(String kriter) throws InterruptedException {
        dataTableHucreIcerikDogrulama(1, 1, kriter);
    }

    public void baslikKolonuFiltreKontrol(String kriter) throws InterruptedException {
        for(int i=0; i<dataTableKayitSayisi()-1; i++) dataTableHucreIcerikDogrulama(i + 1, 2, kriter);
    }

    public void miktarKolonuFiltreKontrol(String kriter) throws InterruptedException {
        for(int i=0; i<dataTableKayitSayisi(); i++) dataTableHucreIcerikDogrulama(i + 1, 4, kriter);
    }

    public void fiyatKolonuFiltreKontrol(String kriter) throws InterruptedException {
        for(int i=0; i<dataTableKayitSayisi()-1; i++) dataTableHucreIcerikDogrulama(i + 1, 5, kriter);
    }

    public void dataTableHucreIcerikDogrulama(int ilgiliSatir, int ilgiliSutun, String kriter) throws InterruptedException {
        dataTable.dataTableHucreIcerikDogrulama(tabloTbodyId, ilgiliSatir, ilgiliSutun, kriter);
    }

    public void urunBaslikFiltreleme(String baslikKriteri) throws InterruptedException {
        baslikFiltresi.click();
        baslikFiltresi.sendKeys(baslikKriteri);
        milisaniyeBekle(500);
    }

    public void urunMiktarFiltreleme(String miktarKriteri) throws InterruptedException {
        miktarFiltresi.click();
        miktarFiltresi.sendKeys(miktarKriteri);
        milisaniyeBekle(500);
    }

    public void urunFiyatFiltreleme(String fiyatKriteri) throws InterruptedException {
        fiyatFiltresi.click();
        fiyatFiltresi.sendKeys(fiyatKriteri);
        milisaniyeBekle(500);
    }

    public void bilgilendirmeMesajiGosterimKontrolu (String bilgilendirmeMesaji) throws InterruptedException {

        milisaniyeBekle(500);
        System.out.println(bilgilendirmeMesajAlani.getText());
        bilgilendirmeMesajAlani.getText().contains(bilgilendirmeMesaji);
    }

    public void hataMesajiGosterimKontrolu (String hataMesaji) throws IOException, InterruptedException {

        milisaniyeBekle(500);
        System.out.println(hataMesajAlani.getText());
        Assert.assertTrue(hataMesajAlani.getText().contains(hataMesaji));

    }

    public void zorunluAlanKontrolu () throws InterruptedException {

        milisaniyeBekle(500);
        Assert.assertTrue(zorunluAlan.isDisplayed());

    }

    public void milisaniyeBekle(long millisecond) throws InterruptedException {
        Thread thread = new Thread();
        thread.sleep(millisecond);
    }
}
