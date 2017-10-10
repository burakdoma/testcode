package tr.com.t2.bkmaaile.view.componentobjects.marketmanagement;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tr.com.t2.bkmaaile.view.common.DataTable;
import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;

import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 25/11/2016.
 */
public class KatYonetimi {

    final WebDriver driver;

    public KatYonetimi(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "mainForm:inputMarketName")
    private WebElement marketIsmiInput;

    @FindBy (id = "mainForm:inputMarketDescription")
    private WebElement marketTanimiInput;

    @FindBy (id = "mainForm:inputMarketIP1")
    private WebElement marketIPAdresiInput1;

    @FindBy (id = "mainForm:inputMarketIP2")
    private WebElement marketIPAdresiInput2;

    @FindBy (id = "mainForm:inputMarketIP3")
    private WebElement marketIPAdresiInput3;

    @FindBy (id = "mainForm:inputMarketIP4")
    private WebElement marketIPAdresiInput4;

    @FindBy (id = "mainForm:inputMarketPort")
    private WebElement marketPortBilgisiInput;

    @FindBy (id = "mainForm:btnSave")
    private WebElement marketKaydetButonu;

    @FindBy (id = "mainForm:btnCancel")
    private WebElement marketVazgecButonu;

//    @FindBy (id="mainForm:marketList_data")
    @FindBy (id = "mainForm:marketList")
    private WebElement tabloTbodyId;

    @FindBy (id="mainForm:marketList_paginator_bottom")
    private WebElement tabloPaginator;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s'")
    private WebElement asagiYukariOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-n'")
    private WebElement yukariOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s'")
    private WebElement asagiOk;

    @FindBy (id = "mainForm:marketList:inputMarketNameFilter")
    private WebElement marketIsmiFiltre;

    @FindBy (id = "mainForm:marketList:inputMarketIPFilter")
    private WebElement ipAdresiFiltre;

    @FindBy (id = "mainForm:marketList:inputMarketPortFilter")
    private WebElement portBilgisiFiltre;

    //@FindBy (className = "ui-messages-info-summary")
    @FindBy (id = "mainForm:messages")
    private WebElement bilgilendirmeMesajAlani;

    @FindBy (className = "ui-messages-error-summary")
    private WebElement hataMesajAlani;

    public String marketIP1FormatHatasi = "Market IP 1. bölüm değeri gereklidir";
    public String marketIP2FormatHatasi = "Market IP 2. bölüm değeri gereklidir";
    public String marketIP3FormatHatasi = "Market IP 3. bölüm değeri gereklidir";
    public String marketIP4FormatHatasi = "Market IP 4. bölüm değeri gereklidir";
    public String marketPortDegeriHatasi = "Market port değeri gereklidir";
    public String marketIsmiGereklidirHatasi = "Market ismi gereklidir";
    public String marketEklemeBasarili = "Market eklendi";
    public String marketSilmeBasarili = "Market silindi";
    public String marketGuncellemeBasarili = "Market güncellendi";

//    güncelle butonu   = btnEditMarket
//    sil butonu        = btnDeleteMarket

    RandomGenerator randomGenerator = new RandomGenerator();
    NameGenerator nameGenerator = new NameGenerator();
    DataTable dataTable = new DataTable();

    /* FUNCTIONAL METHODS */

    public void marketIsmiGir (String marketIsmi) {
        marketIsmiInput.click();
        marketIsmiInput.sendKeys(marketIsmi);
    }

    public void marketIsmiSil () {
        marketIsmiInput.click();
        marketIsmiInput.clear();
    }

    public void marketTanimiGir (String marketTanimi) {
        marketTanimiInput.click();
        marketTanimiInput.sendKeys(marketTanimi);
    }

    public void marketIPAdresiGir (String marketIPAdresi1, String marketIPAdresi2, String marketIPAdresi3, String marketIPAdresi4) {

        marketIPAdresiInput1.click();
        marketIPAdresiInput1.sendKeys(marketIPAdresi1);
        marketIPAdresiInput2.click();
        marketIPAdresiInput2.sendKeys(marketIPAdresi2);
        marketIPAdresiInput3.click();
        marketIPAdresiInput3.sendKeys(marketIPAdresi3);
        marketIPAdresiInput4.click();
        marketIPAdresiInput4.sendKeys(marketIPAdresi4);
    }

    public void marketPortBilgisiGir (String marketPortBilgisi) {

        marketPortBilgisiInput.click();
        marketPortBilgisiInput.sendKeys(marketPortBilgisi);
    }

    public void marketKaydetButonunaBas () {

        marketKaydetButonu.click();
    }

    public void marketVazgecButonunaBas () {

        marketVazgecButonu.click();
    }

    public void bilgilendirmeMesajiGosterimKontrolu (String bilgilendirmeMesaji) {

        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        Assert.assertTrue(bilgilendirmeMesajAlani.getText().contains(bilgilendirmeMesaji));
    }

    public void hataMesajiGosterimKontrolu (String hataMesaji) {

        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        Assert.assertTrue(hataMesajAlani.getText().contains(hataMesaji));
    }

    public void alanlarBosmuKontrolEt() {

        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        Assert.assertTrue(marketIsmiInput.getAttribute("value").isEmpty());
        Assert.assertTrue(marketTanimiInput.getAttribute("value").isEmpty());
        Assert.assertTrue(marketIPAdresiInput1.getAttribute("value").isEmpty());
        Assert.assertTrue(marketIPAdresiInput2.getAttribute("value").isEmpty());
        Assert.assertTrue(marketIPAdresiInput3.getAttribute("value").isEmpty());
        Assert.assertTrue(marketIPAdresiInput4.getAttribute("value").isEmpty());
        Assert.assertTrue(marketPortBilgisiInput.getAttribute("value").isEmpty());
    }

    public void marketIsmiFiltreleme(String kriter) {

        marketIsmiFiltre.click();
        ipAdresiFiltre.click();
        portBilgisiFiltre.click();


        marketIsmiFiltre.click();
        marketIsmiFiltre.sendKeys("Burak");
        marketIsmiFiltre.sendKeys(kriter);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    public void marketIPFiltreleme(String kriter) {

        ipAdresiFiltre.click();
        ipAdresiFiltre.sendKeys(kriter);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    public void marketPortFiltreleme(String kriter) {

        portBilgisiFiltre.click();
        portBilgisiFiltre.sendKeys(kriter);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    /* GENERATOR */

    public String marketIsmiUret() {
        return nameGenerator.randomNameGenerator();
    }

    public String marketTanimiUret() {
        return nameGenerator.randomNameGenerator();
    }

    public String ipDegeriUret() {
        return String.valueOf(randomGenerator.randomNumberGeneratorBetween(0,255));
    }

    public String portDegeriUret() {
        return String.valueOf(randomGenerator.randomNumberGeneratorBetween(0,65535));
    }

    /* DATA TABLE */

    public int dataTableDataninBulunduguSatiriGetir (String ilgiliIcerik) {
        return dataTable.dataTableIceriginBulunduguSatirGetir(tabloTbodyId, ilgiliIcerik);
    }

    public int dataTableKayitSayisi () {
        return dataTable.dataTableToplamSatirSayisiGetir(tabloTbodyId);
    }

    public String dataTableSatirdakiKolonIceriginiGetir (int ilgiliSatir) {
        return dataTable.dataTableSatirdakiIlkKolonunIceriginiGetir(tabloTbodyId, ilgiliSatir);
    }

    public void dataTableSatirdakiGuncelleButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnEditMarket");
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    public void dataTableSatirdakiSilButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnDeleteMarket");
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    /* FILTER OPERATIONS */

    public void filtreliAlanGirilenKriterIleMiBasliyor(int kayitSayisi, String kriter) {

        int toplam = 0;

        if(kayitSayisi > 0) {
            for (int i = 0; i < kayitSayisi; i++) {
                if(dataTableSatirdakiKolonIceriginiGetir(i).startsWith(kriter))
                    toplam++;
            }

//            System.out.println("Toplam listelenen kayit sayisi : "+kayitSayisi);
//            System.out.println("Eşleşen kayit sayisi : "+toplam);

            if(toplam==kayitSayisi)
                System.out.println("Filtrelenen kayit sayisi dogrudur");
            else
                System.out.println("Filtrelenen kayit sayisi hatalidir");
        }
        else
            System.out.println("Aranan kriterlerde kayit bulunamamistir");

    }

}
