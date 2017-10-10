package tr.com.t2.bkmaaile.view.componentobjects.usergroup;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tr.com.t2.bkmaaile.view.common.DataTable;
import tr.com.t2.bkmaaile.view.common.NameGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 18.10.2016.
 */
public class GrupIslemleriAnasayfa {

    final WebDriver driver;
    public GrupIslemleriAnasayfa(WebDriver driver) { this.driver = driver; }

    NameGenerator nameGenerator = new NameGenerator();
    DataTable dataTable = new DataTable();

    public String grupEklemeBasarili = "Kullanıcı Grubu Eklendi";
    public String grupGuncellemeBasarili = "Kullanıcı Grubu Güncellendi";
    public String grupSilmeBasarili = "Kullanıcı Grubu Silindi";
    public String grupAktifUyeVar = "Kullanıcı Grubu en az 1 kullanıcıya atanmış";
    public String grupOncedenTanımlı = "Kullanıcı Grubu önceden kaydedilmiş";
    public String grupAdıBosOlamaz = "Kullanıcı Grubu boş olamaz";

    //public List<String> tanimliGrupIsimleriListesi;
    public String[] tanimliGrupIsimleriListesi = {"Grup5", "Grup6", "Grup7", "Muhasebe1", "Satın Alma1"};

    public static int rastgeleGrupSayisi = 25;

    @FindBy (id = "mainForm:btnSave")
    private WebElement kaydetButonu;

    @FindBy (id = "mainForm:btnCancel")
    private WebElement vazgecButonu;

    @FindBy (id = "mainForm:inputGroupName")
    private WebElement kullanıcıGrupIsmi;

    @FindBy (id = "mainForm:userGroupList")
    private WebElement kullanicilariGoruntule;

    //@FindBy (className = "ui-messages-info-summary")
    @FindBy (id = "mainForm:messages")
    private WebElement bilgilendirmeMesajAlani;

    @FindBy (className = "ui-messages-error-summary")
    private WebElement hataMesajAlani;

    @FindBy (id = "mainForm:userGroupList:inputGroupNameFilter")
    private WebElement grupIsmiFiltre;

    @FindBy (id="mainForm:userGroupList_data")
    private WebElement tabloTbodyId;

    @FindBy (id="mainForm:userGroupList_paginator_bottom")
    private WebElement tabloPaginator;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-n'")
    private WebElement yukariOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s'")
    private WebElement asagiOk;

    @FindBy (css="span[class='ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s'")
    private WebElement asagiYukariOk;

//    güncelle butonu   = btnEditUserGroup
//    sil butonu        = btnDeleteUserGroup;
//    görüntüle butonu  = btnDisplayUsers

    public void grupIsmiGir (String grupIsmi) {

        kullanıcıGrupIsmi.click();
        kullanıcıGrupIsmi.sendKeys(grupIsmi);
    }

    public String grupIsmiDegeriniOku () {
        String text = kullanıcıGrupIsmi.getAttribute("value");
        return kullanıcıGrupIsmi.getAttribute("value");
    }

    public void grupIsmiBosMu (String grupIsmi) throws InterruptedException {
        milisaniyeBekle(500);
        Assert.assertFalse(grupIsmi.isEmpty());
    }

    public boolean grupIsmiAyniMi (String grupIsmi1, String grupIsmi2) {
        return grupIsmi1.equalsIgnoreCase(grupIsmi2);
    }

    public void grupEkle (String grupIsmi) {

        grupIsmiGir(grupIsmi);
        kaydetButonu.click();
    }

    public void cokluGrupEkle (int eklenecekGrupSayisi) {
        for (int i=0; i<eklenecekGrupSayisi; i++)
        {
            String grupIsmi = grupIsmiUret();
            grupEkle(grupIsmi);
            bilgilendirmeMesajiGosterimKontrolu(grupEklemeBasarili);
        }
    }

    public void tanimliGrupListesiniEkle () throws InterruptedException {

        for( int i=0; i<tanimliGrupIsimleriListesi.length; i++)
        {
            milisaniyeBekle(500);
            grupIsmiGir(tanimliGrupIsimleriListesi[i].toString());
            kaydetButonu.click();
        }
    }

//    public void listeyiSirala () { siralamaOku.click(); }

    public void grupEklemeVazgec () {
        vazgecButonu.click();
    }

    public void bilgilendirmeMesajiGosterimKontrolu (String bilgilendirmeMesaji) {
        Uninterruptibles.sleepUninterruptibly(250, TimeUnit.MILLISECONDS);
        System.out.println(bilgilendirmeMesajAlani.getText());
        bilgilendirmeMesajAlani.getText().contains(bilgilendirmeMesaji);
    }

    public void hataMesajiGosterimKontrolu (String hataMesaji) throws IOException, InterruptedException {

        milisaniyeBekle(500);
        System.out.println(hataMesajAlani.getText());
        Assert.assertTrue(hataMesajAlani.getText().contains(hataMesaji));

    }

    public String dataTableSatirIcerik (int ilgiliSatir) {
        return dataTable.dataTableSatirIcerikGetir(tabloTbodyId, ilgiliSatir);
    }

    public int dataTableDataninBulunduguSatiriGetir (String ilgiliIcerik) {
        return dataTable.dataTableIceriginBulunduguSatirGetir(tabloTbodyId, ilgiliIcerik);
    }

    public void dataTableSatirdakiGuncelleButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnEditUserGroup");
    }

    public void dataTableSatirdakiSilButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnDeleteUserGroup");
    }

    public void dataTableSatirdakiGoruntuleButonunaTikla (int ilgiliSatir) {
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnDisplayUsers");
    }

    public int dataTableKayitSayisi () {
        return dataTable.dataTableToplamSatirSayisiGetir(tabloTbodyId);
    }

    public String dataTableSatirdakiKolonIceriginiGetir (int ilgiliSatir) {
        return dataTable.dataTableSatirdakiIlkKolonunIceriginiGetir(tabloTbodyId, ilgiliSatir);
    }

    public String grupIsmiUret () {

        String uretilenIsim = nameGenerator.randomNameGenerator();
        return uretilenIsim;
    }

    public void milisaniyeBekle(long millisecond) throws InterruptedException {
        Thread thread = new Thread();
        thread.sleep(millisecond);
    }

    public void grupIsmiFiltreleme(String kriter) throws InterruptedException {
        grupIsmiFiltre.click();
        grupIsmiFiltre.sendKeys(kriter);
        milisaniyeBekle(500);
    }

    // TODO : @Seyda buraya AssertTrue benzeri bir şey yapabilir miyiz?
    public void filtreliGrupIsmiGirilenKriterIleMiBasliyor(int kayitSayisi, String kriter) {

        int toplam = 0;

        if(kayitSayisi > 0) {
            for (int i = 0; i < kayitSayisi; i++) {
                if(dataTableSatirdakiKolonIceriginiGetir(i).startsWith(kriter))
                    toplam++;
            }

            System.out.println("Toplam listelenen kayit sayisi : "+kayitSayisi);
            System.out.println("Eşleşen kayit sayisi : "+toplam);

            if(toplam==kayitSayisi)
                System.out.println("Filtrelenen kayit sayisi dogrudur");
            else
                System.out.println("Filtrelenen kayit sayisi hatalidir");
        }
        else
            System.out.println("Aranan kriterlerde kayit bulunamamistir");

    }

    public void siralamaButonunaTikla() throws InterruptedException {
        asagiYukariOk.click();
        milisaniyeBekle(500);
        yukariOk.click();
        milisaniyeBekle(500);
        asagiOk.click();
    }

    public String kullanicisiOlanGrupGetir(){
        int i = 0;
        while(dataTable.dataTableHucreIcerikGetir(tabloTbodyId, i, 1).equals("0")){
            i++;
        }
        return dataTable.dataTableHucreIcerikGetir(tabloTbodyId, i , 0);
    }

    public String grupKullaniciSayisiGetir(String grupIsmi) throws InterruptedException {
        grupIsmiFiltreleme(grupIsmi);
        return dataTable.dataTableHucreIcerikGetir(tabloTbodyId, 0, 1);
    }



    // TODO: Toplam kullanici sayisi caseleri tumuyle covered degil
}