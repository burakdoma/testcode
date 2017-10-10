package tr.com.t2.bkmaaile.view.componentobjects.candidateuser;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import tr.com.t2.bkmaaile.view.common.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 18/10/16.
 */

public class AdayIslemleriAnasayfa {

    final WebDriver driver;
    public AdayIslemleriAnasayfa(WebDriver driver) { this.driver = driver; }

    ComboBox comboBox = new ComboBox();
    Pager pager = new Pager();
    DataTable dataTable = new DataTable();
    EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();
    Servis servis = new Servis();

    public String kullaniciEklemeBasariliMesaji = "Kullanıcı kayıt edildi";
    public String aktifKullaniciSilmeOnay = "Kullanıcı aktifdir. Silmeniz durumunda kullanıcı pasif olacaktır. Devam etmek istiyor musunuz?";
    public String genelKullaniciSilmeOnay = "Kullanıcıyı silmek istediğinizden emin misiniz?";

    //**FILTER**

    @FindBy(id = "mainForm:userDataTable:inputEmailFilter")
    private WebElement epostaInput;

    @FindBy(id = "mainForm:userDataTable:cmbUserUserStatusFilter_label")
    private WebElement durumBox;

    @FindBy(id = "mainForm:userDataTable:cmbUserUserStatusFilter_panel")
    private WebElement durumOptionBox;

    @FindBy(id = "mainForm:userDataTable:cmbUserUserGroupFilter_label")
    private WebElement grupBox;

    @FindBy(id = "mainForm:userDataTable:cmbUserUserGroupFilter_panel")
    private WebElement grupBoxPanel;


    //**TABLO**

    @FindBy(id = "mainForm:userDataTable_data")
    private WebElement tabloTbodyId;

    @FindBy(id = "mainForm:userDataTable")
    private WebElement dataTableId;


    //*HEADER**

    @FindBy(id = "mainForm:userDataTable:columnEmail")
    private WebElement emailHeader;

    @FindBy(id = "mainForm:userDataTable:columnStatus")
    private WebElement durumHeader;

    @FindBy(id = "mainForm:userDataTable:columnUserGroup")
    private WebElement kullaniciGrupHeader;


    //**BUTTONS**

    @FindBy(id = "mainForm:uploadLink")
    private WebElement yukleButonu;

    @FindBy(id = "mainForm:btnDialogConfirmYes")
    private WebElement silmeOnayKabulButonu;

    @FindBy(id = "mainForm:btnDialogConfirmYes")
    private WebElement silmeOnayRedButonu;


    //**DUZENLE**

    @FindBy(id = "mainForm:btnSave")
    private WebElement duzenleKaydetButonu;

    @FindBy(id = "mainForm:btnClose")
    private WebElement duzenleKapatButonu;

    @FindBy(id = "mainForm:cmbUserUserGroup_label")
    private WebElement duzenleGrupBox;

    @FindBy(id = "mainForm:cmbUserUserGroup_panel")
    private WebElement duzenleGrupBoxPanel;

    @FindBy(id = "mainForm:inputEmail")
    private WebElement duzenleEpostaInput;


    //**SIL**

    @FindBy(id = "mainForm:dialogConfirm")
    private WebElement silPopUp;

    @FindBy(id = "mainForm:btnDialogConfirmNo")
    private WebElement silHayirBtn;

    @FindBy(id = "mainForm:btnDialogConfirmYes")
    private WebElement silEvetBtn;


    //**MESAJBOX**

    @FindBy(id = "mainForm:messages")
    private WebElement mesajKutusu;



    //**INPUT/SECIM**
    public void emailAddressGir(String epostaAdresi){
        epostaInput.sendKeys(epostaAdresi);
    }
    public void durumSec(String secilecekDurum){
        comboBox.comboboxSecimIsimIle(durumBox, durumOptionBox, secilecekDurum);
    }
    public void grupSecIsimIle(String secilecekGrupIsmi){
        comboBox.comboboxSecimIsimIle(grupBox, grupBoxPanel, secilecekGrupIsmi);
    }
    public void grupSecSiralamaIle(int secilecekGrupSiralama){
        comboBox.comboboxSecimSiralamaIle(grupBox, grupBoxPanel, secilecekGrupSiralama);
    }
    public void grupSecRandom(){
        comboBox.comboboxSecimRandom(grupBox, grupBoxPanel);
    }


    //**FILTRELEME**

    public void emailFiltrele(String filtreDatasi){
        emailAddressGir(filtreDatasi);
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
    }


    //**DATA*TABLE*ISLEMLERI**
    //**Data Table Buttons
    public void dataTableSatirdakiSilButonuTikla(int ilgiliSatir){
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnDelete");
    }
    public void dataTableSatirdakiDuzenluButonunaTikla(int ilgiliSatir){
        dataTable.dataTableSatirdakiButonunaTikla(tabloTbodyId, ilgiliSatir, "btnEdit");
    }

    //**Data Table Icerik
    public int dataTableDataninBulunduguSatiriGetir(String ilgiliIcerik){
        return dataTable.dataTableIceriginBulunduguSatirGetir(tabloTbodyId, ilgiliIcerik);
    }
    public String dataTableSatirIcerikGetir(int ilgiliSatir){
        return dataTable.dataTableSatirIcerikGetir(tabloTbodyId, ilgiliSatir);
    }
    public String dataTableIceriginBulunduguSatirBilgisiGetir(String icerik){
        return dataTable.dataTableIceriginBulunduguSatirIcerikGetir(tabloTbodyId, icerik);
    }
    public void dataTableHucreIcerigiDogrulama(int dataSatir, int dataKolon, String icerik){
        WebElement tablo = driver.findElement(By.id("mainForm")).findElement(By.id("mainForm:userDataTable")).findElement(By.id("mainForm:userDataTable_data"));
        List<WebElement>  satir = tablo.findElements(By.cssSelector("tr[role='row']"));
        List<WebElement> kolonlar = satir.get(dataSatir).findElements(By.cssSelector("td[role='gridcell']"));
        Assert.assertEquals(icerik, kolonlar.get(dataKolon).getText());
    }
    public int dataTableSatirSayisiGetir(){
        return dataTable.dataTableToplamSatirSayisiGetir(tabloTbodyId);
    }
    public void dataTableKolonIcerikKontrolu(String hucreIcerik, int kolonId){
        int satirSayisi = dataTableSatirSayisiGetir();
        int k=0;
        while(k<satirSayisi){
            dataTableHucreIcerigiDogrulama(k, kolonId, hucreIcerik);
            k++;
        }
    }


    //**SILME*ISLEMI**
    public void silmeIsleminiOnayla(){
        silmeOnayKabulButonu.click();
    }
    public void silmeIsleminiIptalEt(){
        silmeOnayRedButonu.click();
    }


    //**MESAJ**
    public void mesajGoruntuleme(String mesaj){
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        Assert.assertTrue(mesajKutusu.getText().equals(mesaj));
    }
    public void kullaniciEklemeBasariliNotificationGoruntuleme(){
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
        Assert.assertTrue(mesajKutusu.getText().equals(kullaniciEklemeBasariliMesaji));
    }


    //**HEADER*TIKLA**
    public void emailHeaderTikla(){
        WebElement emailHeaderText = emailHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        emailHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }
    public void durumHeaderTikla(){
        WebElement durumHeaderText = durumHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        durumHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }
    public void kullaniciGrupHeaderTikla(){
        WebElement grupHeaderText = kullaniciGrupHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
        grupHeaderText.click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }


    //**SORTING**
    public void emailSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(tabloTbodyId, 0);
    }
    public void durumSiralamaKontrolu() {
        dataTable.dataTableKolonOrderKontrolu(tabloTbodyId, 1);
    }
    public void grupSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(tabloTbodyId, 3);
    }


    //**DUZENLE**
    public void duzenleGrupSecIsimIle(String secilecekGrupIsmi){
        comboBox.comboboxSecimIsimIle(duzenleGrupBox, duzenleGrupBoxPanel, secilecekGrupIsmi);
    }
    public void duzenleKaydetButonuTikla(){
        duzenleKaydetButonu.click();
    }
    public void duzenleKapatButonuTikla(){
        duzenleKapatButonu.click();
    }
    public void duzenlePopUpAcildiginiDogrula(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(duzenleKaydetButonu));
    }
    public void duzenlePopUpKapandiginiDogrula(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(epostaInput));
    }
    public void kullaniciEmailDuzenle(String kullaniciEposta, String kullaniciGuncelEposta){
        emailFiltrele(kullaniciEposta);
        dataTableSatirdakiDuzenluButonunaTikla(0);
        duzenlePopUpAcildiginiDogrula();
        duzenleEpostaInput.click();
        duzenleEpostaInput.clear();
        duzenleEpostaInput.sendKeys(kullaniciGuncelEposta);
        duzenleKaydetButonuTikla();
        duzenlePopUpKapandiginiDogrula();
    }
    public void aktifKullaniciEmailDuzenleme(String kullaniciEposta){
        emailFiltrele(kullaniciEposta);
        dataTableSatirdakiDuzenluButonunaTikla(0);
        duzenlePopUpAcildiginiDogrula();
        Assert.assertEquals(duzenleEpostaInput.getAttribute("aria-disabled"), "true");
        duzenleKapatButonuTikla();
        duzenlePopUpKapandiginiDogrula();
    }


    //**SIL**
    public void silmeIptalButonuTikla(){
        silHayirBtn.click();
    }
    public void silmeOnayButonuTikla(){
        silEvetBtn.click();
    }
    public void kullaniciSilmeIslemiOnaylamadanCik(String kullaniciEposta){
        emailFiltrele(kullaniciEposta);
        dataTableSatirdakiSilButonuTikla(0);
        Assert.assertTrue(silPopUp.isDisplayed());
        silmeIptalButonuTikla();
    }
    public void kullaniciSil(String kullaniciEposta, String onayMesaji){
        emailFiltrele(kullaniciEposta);
        dataTableSatirdakiSilButonuTikla(0);
        Assert.assertTrue(silPopUp.isDisplayed());
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        Assert.assertTrue(silPopUp.getText().contains(onayMesaji));
        silmeOnayButonuTikla();
    }
    public void kullaniciSilAktifOlmayan(String kullaniciEposta){
        kullaniciSil(kullaniciEposta, genelKullaniciSilmeOnay);
    }
    public void aktifKullaniciSil(String kullaniciEposta){
        kullaniciSil(kullaniciEposta, aktifKullaniciSilmeOnay);
    }
    public void kullanicininSilindiginiDogrula(String kullaniciEposta){
        emailFiltrele(kullaniciEposta);
        Assert.assertEquals(dataTableSatirSayisiGetir(), 0);
    }
    public void aktifKullanicininPasifeGectiginiDogrula(String kullaniciEposta){
        emailFiltrele(kullaniciEposta);
        dataTable.dataTableHucreIcerikDogrulama(tabloTbodyId, 0, 1, "Pasif");
    }


    //**GRUP*ATAMA**
    public void kullaniciGrupAta(String kullaniciEpostaAdresi, String grupIsmi){
        emailFiltrele(kullaniciEpostaAdresi);
        dataTableSatirdakiDuzenluButonunaTikla(0);
        duzenlePopUpAcildiginiDogrula();
        duzenleGrupSecIsimIle(grupIsmi);
        duzenleKaydetButonuTikla();
        duzenlePopUpKapandiginiDogrula();
    }

}
