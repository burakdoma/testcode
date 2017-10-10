package tr.com.t2.bkmaaile.view.componentobjects.pendinguser;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.t2.bkmaaile.view.common.ComboBox;
import tr.com.t2.bkmaaile.view.common.DataTable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by burakdoma on 03/11/16.
 */
public class OnayBekleyenKullanici {

    final WebDriver driver;
    public OnayBekleyenKullanici(WebDriver driver) { this.driver = driver; }

    DataTable dataTable = new DataTable();
    ComboBox comboBox = new ComboBox();

    public String kullaniciOnaylandiMesaji = "Kullanıcı Onaylandı";
    public String kullaniciReddedildiMesaji = "Kullanıcı Reddedildi";


    //**FILTER**

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:inputEmailFilter")
    private WebElement epostaInput;

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:inputNameFilter")
    private WebElement adInput;

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:inputSurnameFilter")
    private WebElement soyadInput;


    //*HEADER**

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:columnEmail")
    private WebElement emailHeader;

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:inputNameFilter")
    private WebElement adHeader;

    @FindBy(id = "mainForm:pendingApprovalUserDataTable:inputSurnameFilter")
    private WebElement soyadHeader;


    //**TABLO**

    @FindBy(id = "mainForm:pendingApprovalUserDataTable")
    private WebElement dataTableId;

    @FindBy(id = "mainForm:pendingApprovalUserDataTable_data")
    private WebElement dataTableTbodyId;


    //**BUTTONS**

    @FindBy(id = "mainForm:btnDialogApprove")
    private WebElement onayButonu;

    @FindBy(id = "mainForm:btnDialogReject")
    private WebElement redButonu;

    @FindBy(id = "mainForm:btnDialogClose")
    private WebElement popUpKapatBtn;


    //**POP-UP**
    @FindBy(id = "mainForm:userDetailDialog")
    private WebElement popUp;

    @FindBy(id = "mainForm:btnDialogOverlayReject")
    private WebElement popUpRedNedeniSeciliReddetBtn;

    @FindBy(id = "mainForm:cmbRejectReason_label")
    private WebElement redNedeniCombo;

    @FindBy(id = "mainForm:cmbRejectReason_items")
    private WebElement redNedeniComboList;

    @FindBy(id = "rejectReasonOtherDetails")
    private WebElement redNedeniDigerDetay;


    //**MESSAGE*BOX**
    @FindBy(id = "mainForm:messages")
    private WebElement messageBox;


    //**INPUT/SECIM**
    public void emailAddressGir(String epostaAdresi){
        epostaInput.sendKeys(epostaAdresi);
    }
    public void emailAddressAlaniniTemizle() {
        epostaInput.clear();
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
    }
    public void adGir(String ad) { adInput.sendKeys(ad); }
    public void adAlaniniTemizle(){
        adInput.clear();
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
    }
    public void soyadGir(String ad) { soyadInput.sendKeys(ad); }
    public void soyadAlaniniTemizle(){
        soyadInput.clear();
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
    }

    //**FILTRELEME**
    public void emailFiltrele(String filtreDatasi){
        emailAddressGir(filtreDatasi);
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


    //**DATA*TABLE*ISLEMLERI**
    //**Data Table Buttons
    public void dataTableSatirdakiDetayButonuTikla(int ilgiliSatir){
        dataTable.dataTableSatirdakiButonunaTikla(dataTableTbodyId, ilgiliSatir, "btnDetail");
    }

    //**Data Table Icerik
    public int dataTableDataninBulunduguSatiriGetir(String ilgiliIcerik){
        return dataTable.dataTableIceriginBulunduguSatirGetir(dataTableTbodyId, ilgiliIcerik);
    }
    public String dataTableSatirIcerikGetir(int ilgiliSatir){
        return dataTable.dataTableSatirIcerikGetir(dataTableTbodyId, ilgiliSatir);
    }
    public String dataTableIceriginBulunduguSatirBilgisiGetir(String icerik){
        return dataTable.dataTableIceriginBulunduguSatirIcerikGetir(dataTableTbodyId, icerik);
    }
    public void dataTableHucreIcerigiDogrulama(int dataSatir, int dataKolon, String icerik){
        List<WebElement> satir = dataTableId.findElements(By.cssSelector("tr[role='row']"));
        List<WebElement> kolonlar = satir.get(dataSatir).findElements(By.cssSelector("td[role='gridcell']"));
        Assert.assertEquals(icerik, kolonlar.get(dataKolon).getText());
    }
    public int dataTableSatirSayisiGetir(){
        return dataTable.dataTableToplamSatirSayisiGetir(dataTableTbodyId);
    }


    //**HEADER*TIKLA**
    public void emailHeaderTikla(){
        WebElement emailHeaderText = emailHeader.findElement(By.cssSelector("span[class='ui-column-title']"));
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


    //**SORTING**
    public void emailSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(dataTableTbodyId, 0);
    }
    public void adSiralamaKontrolu() {
        dataTable.dataTableKolonOrderKontrolu(dataTableTbodyId, 1);
    }
    public void soyadSiralamaKontrolu(){
        dataTable.dataTableKolonOrderKontrolu(dataTableTbodyId, 2);
    }


    //**POP-UP DOGRULAMA**
    public void detayPopUpAcildiginiDogrula(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(onayButonu));
    }
    public void detayPopUpKapandiginiDogrula(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(epostaInput));
    }
    public String popUpIcerigi(){
        return popUp.getText();
    }
    public void popUpKapatButonuTikla(){
        popUpKapatBtn.click();
    }


    //**KULLANICI*ISLEMLERI**
    public void kullaniciOnayla(String emailAddress){
        emailFiltrele(emailAddress);
        dataTableSatirdakiDetayButonuTikla(0);
        detayPopUpAcildiginiDogrula();
        onayButonu.click();
        detayPopUpKapandiginiDogrula();
    }
    public void kullaniciReddetRedNedeniSecerek(String emailAddress, String redNedeni){
        emailFiltrele(emailAddress);
        dataTableSatirdakiDetayButonuTikla(0);
        detayPopUpAcildiginiDogrula();
        redButonu.click();
        kullaniciRedNedeniSec(redNedeni);
        popUpRedNedeniSeciliReddetBtn.click();
        detayPopUpKapandiginiDogrula();
    }
    public void kullaniciReddetRedNedeniDiger(String emailAddress, String redNedeniDetay){
        emailFiltrele(emailAddress);
        dataTableSatirdakiDetayButonuTikla(0);
        detayPopUpAcildiginiDogrula();
        redButonu.click();
        kullaniciRedNedeniSec("Diğer");
        redNedeniDigerDetay.sendKeys(redNedeniDetay);
        popUpRedNedeniSeciliReddetBtn.click();
        detayPopUpKapandiginiDogrula();
    }
    public void kullaniciRedNedeniSec(String redNedeni){
        comboBox.comboboxSecimIsimIle(redNedeniCombo, redNedeniComboList, redNedeni);
    }


    //**MESSAGE*BOX**
    public String mesajiKutusuIcerigiGetir(){
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        return messageBox.getText();
    }
}



//@Burak: Reddedilmis kullanicinin red nedenini degistirmek?
//BUG: Red nedeni olarak "Seciniz" secili iken , Reddet butonu tiklaninca pop up direk kapaniyor - uyari mesaji?
//Red Nedeni diger - id ekle