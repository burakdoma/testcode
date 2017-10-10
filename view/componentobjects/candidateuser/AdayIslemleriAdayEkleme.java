package tr.com.t2.bkmaaile.view.componentobjects.candidateuser;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.t2.bkmaaile.view.common.ComboBox;
import tr.com.t2.bkmaaile.view.common.EmailAddressGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.main.MenuNavigation;

import java.util.concurrent.TimeUnit;

/**
 * Created by seday on 19/10/16.
 */
public class AdayIslemleriAdayEkleme {

    final WebDriver driver;
    public AdayIslemleriAdayEkleme(WebDriver driver) { this.driver = driver; }

    ComboBox comboBox = new ComboBox();
    EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();


    //**MESAJLAR*TEXT**
    public String emailGecersizHataMesaji = "Girilen e-posta adresi hatalıdır";


    //**BUTTONS**

    @FindBy(id = "mainForm:btnAdd")
    private WebElement ekleButonu;

    @FindBy(id = "mainForm:btnSave")
    private WebElement kaydetButon;

    @FindBy(id = "mainForm:btnClose")
    private WebElement kapatButon;


    //**INPUT/SELECT**

    @FindBy(id = "mainForm:inputEmail")
    private WebElement epostaInput;

    @FindBy(id = "mainForm:cmb_candidateUserUserGroup_label")
    private WebElement grupBox;

    @FindBy(id = "mainForm:cmb_candidateUserUserGroup_panel")
    private WebElement grupBoxPanel;


    //**MESAJ*BOX**

    @FindBy(id = "mainForm:addEditDialogMessage")
    private WebElement hataMesajiKutusu;


    //**BUTTON*CLICK**
    public void ekleButonunaTikla(){
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        ekleButonu.click();
    }
    public void kaydetButonunaTikla(){
        kaydetButon.click();
    }
    public void kapatButonunaTikla(){
        kapatButon.click();
    }


    //**POP_UP**
    public void popUpAcildiginiDogrula(){
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.elementToBeClickable(epostaInput));
    }
    public void popUpKapandiginiDogrula(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(ekleButonu));
    }


    //**INPUT/SELECT**
    public void epostaGir(String eposta){
        epostaInput.sendKeys(eposta);
    }
    public void grupSec(String secilecekGrupIsmi){
        comboBox.comboboxSecimIsimIle(grupBox, grupBoxPanel, secilecekGrupIsmi);
    }


    //**MESAJ**
    public void hataMesajiGoruntuleme(String hataMesaji){
        System.out.println(hataMesajiKutusu.getText());
        Assert.assertTrue(hataMesajiKutusu.getText().equals(hataMesaji));
    }


    //**MAIN*ACTIONS**
    public void kullaniciEkle(String epostaAdresi){
        popUpAcildiginiDogrula();
        epostaGir(epostaAdresi);
        kaydetButonunaTikla();
        popUpKapandiginiDogrula();
    }
    public void rastgeleKullaniciEkle(){
        kullaniciEkle(emailAddressGenerator.validEmailGenerator());
    }
    public void cokluRandomKullaniciEkle(int kullaniciSayisi){
        int i=0;
        while (i<kullaniciSayisi){
            ekleButonunaTikla();
            rastgeleKullaniciEkle();
            i++;
        }
    }

}
