package tr.com.t2.bkmaaile.view.componentobjects.moneytransfer;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tr.com.t2.bkmaaile.view.common.DataTable;
import tr.com.t2.bkmaaile.view.common.Pager;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 22/11/16.
 */
public class ParaAktarimi {
    final WebDriver driver;

    DataTable dataTable = new DataTable();
    Pager pager = new Pager();

    String mesajBasariliAktarimPart1 = " tutarındaki miktar ayrı ayrı ";
    String mesajBasariliAktarimPart2 = " kullanıcıya aktarıldı";

    public ParaAktarimi(WebDriver driver) { this.driver = driver; }



    //PARA AKTARIMI
    @FindBy(id = "mainForm:inputcoinAssignAmount_input")
    private WebElement paraMiktarInput;

    @FindBy(id = "mainForm:btnAssignCoins")
    private WebElement paraAktarmaBtn;


    //GRUP
    @FindBy(id = "mainForm:userGroupTable_data")
    private WebElement grupDataTable;

    @FindBy(id = "mainForm:userGroupTable:colUserGroupName")
    private WebElement kullaniciGrupIsmiHeader;

    @FindBy(id = "mainForm:userGroupTable:colUserGroupName:filter")
    private WebElement kullaniciGrupIsmiInput;

    @FindBy(id = "mainForm:userGroupTable:colGroupSelection")
    private WebElement grupSelectAll;


    //KULLANICI
    @FindBy(id = "mainForm:usersTable_data")
    private WebElement kullaniciDataTable;

    @FindBy(id = "mainForm:usersTable:colEmail")
    private WebElement epostaHeader;

    @FindBy(id = "mainForm:usersTable:colEmail:filter")
    private WebElement epostaInput;

    @FindBy(id = "mainForm:usersTable:colName")
    private WebElement adHeader;

    @FindBy(id = "mainForm:usersTable:colName:filter")
    private WebElement adInput;

    @FindBy(id = "mainForm:usersTable:colSurname")
    private WebElement soyadHeader;

    @FindBy(id = "mainForm:usersTable:colSurname:filter")
    private WebElement soyadInput;


    //PAGER
    @FindBy(id = "mainForm:usersTable_paginator_bottom")
    private WebElement kullaniciTablosuPaginatorID;

    @FindBy(id = "mainForm:userGroupTable_paginator_top")
    private WebElement kullaniciGrubuTablosuPaginatorID;


    //MESAJ
    @FindBy(id = "mainForm:messages")
    private WebElement mesajKutusu;



    //INPUT
    public void grupIsmiGir(String grupIsmi) { kullaniciGrupIsmiInput.sendKeys(grupIsmi); }
    public void epostaGir(String eposta){
        epostaInput.sendKeys(eposta);
    }
    public void adGir(String ad) { adInput.sendKeys(ad); }
    public void soyadGir(String soyad) { soyadInput.sendKeys(soyad); }

    //FILTRELEME
    public void grupIsmiFiltrele(String filtreDatasi){
        kullaniciGrupIsmiInput.sendKeys(filtreDatasi);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }
    public void emailFiltrele(String filtreDatasi){
        epostaGir(filtreDatasi);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }
    public void adFiltrele(String filtreDatasi){
        adGir(filtreDatasi);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }
    public void soyadFiltrele(String filtreDatasi){
        soyadGir(filtreDatasi);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }

    //MESAJ
    public void mesajGoruntuleme(String mesaj){
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        Assert.assertTrue(mesajKutusu.getText().equals(mesaj));
    }
    public void paraAktarmaBasariliMesajiGoruntuleme(String aktarilacakMiktar, String aktarilacakKullaniciSayisi){
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
        System.out.println(aktarilacakMiktar + mesajBasariliAktarimPart1 + aktarilacakKullaniciSayisi + mesajBasariliAktarimPart2);
        Assert.assertTrue(mesajKutusu.getText().equals(aktarilacakMiktar + mesajBasariliAktarimPart1 + aktarilacakKullaniciSayisi + mesajBasariliAktarimPart2));
    }

    //HEADER TIKLA
    public void emailHeaderTikla(){
        WebElement emailHeaderText = epostaHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        emailHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }
    public void adHeaderTikla(){
        WebElement adHeaderText = adHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        adHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }
    public void soyadHeaderTikla(){
        WebElement soyadHeaderText = soyadHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        soyadHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }
    public void kullaniciGrupHeaderTikla(){
        WebElement grupHeaderText = kullaniciGrupIsmiHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        grupHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }

    //SORTING
    public void kullaniciGrupIsmiSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(grupDataTable, 1);
    }
    public void emailSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(kullaniciDataTable, 1);
    }
    public void adSiralamaKontrolu() {
        dataTable.dataTableKolonOrderKontrolu(kullaniciDataTable, 2);
    }
    public void soyadSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(kullaniciDataTable, 3);
    }

    //GRUP
    public void tabloGrupIsmininBulunduguCheckboxTikla(String aranacakData){
        int satirID = dataTable.dataTableIceriginBulunduguSatirGetir(grupDataTable, aranacakData);
        dataTable.dataTableSatirindakiCheckBoxSec(grupDataTable, satirID, "ui-chkbox-icon ui-icon ui-icon-");
    }
    public void grupIsmiSec(String grupIsmi){
        //email filtreleme alanini temizle
        //email filtrele
        tabloGrupIsmininBulunduguCheckboxTikla(grupIsmi);
    }
    public void rastgeleGrupIsmiSec(){
        dataTable.dataTableRastgeleCheckBoxSec(grupDataTable, "ui-chkbox-icon ui-icon ui-icon-blank ui-c");
    }
    public List<String> seciliGrupListesiGetir(){
        return dataTable.dataTableSeciliItemListesiGetir(grupDataTable, kullaniciGrubuTablosuPaginatorID, "ui-chkbox-icon ui-icon ui-c ui-icon-check" );
    }
    public int seciliGrupSayisiGetir(){
        return dataTable.dataTableSeciliItemSayisiGetir(grupDataTable, kullaniciGrubuTablosuPaginatorID ,"ui-chkbox-icon ui-icon ui-c ui-icon-check");
    }
    public void grupTumSecimleriKaldir(){
        grupSelectAll.click();
        grupSelectAll.click();
    }

    //KULLANICI
    public void tabloKullaniciEmailBulunduguCheckboxTikla(String aranacakData){
        int satirID = dataTable.dataTableIceriginBulunduguSatirGetir(kullaniciDataTable, aranacakData);
        dataTable.dataTableSatirindakiCheckBoxSec(kullaniciDataTable, satirID, "ui-chkbox-icon ui-icon ui-icon-");
    }
    public void kullaniciSec(String kullaniciEmail){
        //email filtreleme alanini temizle
        //email filtrele
        tabloKullaniciEmailBulunduguCheckboxTikla(kullaniciEmail);
    }
    public void rastgeleKullaniciSec(){
        dataTable.dataTableRastgeleCheckBoxSec(kullaniciDataTable, "ui-chkbox-icon ui-icon ui-icon-blank ui-c");
    }
    public void rastgeleCokluKullaniciSec(int secilecekKullaniciSayisi){
        // TODO: mevcut durumda hepsi ilk sayfadan seciliyor, expand
        int i =0;
        while(i<secilecekKullaniciSayisi-1){
            dataTable.dataTableRastgeleCheckBoxSec(kullaniciDataTable, "ui-chkbox-icon ui-icon ui-icon-blank ui-c");
            Uninterruptibles.sleepUninterruptibly(50, TimeUnit.MILLISECONDS);
            i++;
        }
    }
    public List<String> seciliKullaniciListesiGetir(){
        return dataTable.dataTableSeciliItemListesiGetir(kullaniciDataTable, kullaniciTablosuPaginatorID, "ui-chkbox-icon ui-icon ui-c ui-icon-check" );
    }
    public int seciliKullaniciSayisiGetir(){
        return dataTable.dataTableSeciliItemSayisiGetir(kullaniciDataTable, kullaniciTablosuPaginatorID, "ui-chkbox-icon ui-icon ui-icon-check ui-c");
    }
    public String seciliKullaniciSayisiTextIcerikGetir(){
        WebElement seciciKullaniciSayisi = kullaniciDataTable.findElement(By.cssSelector("div[class='ui-datatable-footer ui-widget-header ui-corner-bottom'"));
        return seciciKullaniciSayisi.getText();
    }

    //PARA AKTARMA
    public void paraAktarma(String aktarilacakMiktar, String aktarilacakKullaniciSayisi){
        paraMiktarInput.sendKeys(aktarilacakMiktar);
        paraAktarmaBtn.click();
        paraAktarmaBasariliMesajiGoruntuleme(aktarilacakMiktar, aktarilacakKullaniciSayisi);
    }



}
