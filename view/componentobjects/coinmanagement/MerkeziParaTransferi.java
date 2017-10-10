package tr.com.t2.bkmaaile.view.componentobjects.coinmanagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by burakdoma on 25/11/2016.
 */
public class MerkeziParaTransferi {

    final WebDriver driver;

    public MerkeziParaTransferi (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (id = "mainForm:inputcoinAssignAmount")
    private WebElement aktarilacakParaMiktari;

    @FindBy (id = "mainForm:btnAssignCoins")
    private WebElement paraAktarButonu;

    @FindBy (id = "mainForm:marketsTable:j_idt27:filter")
    private WebElement isimFiltresi;

    @FindBy (id = "mainForm:marketsTable:j_idt29:filter")
    private WebElement tanımFiltresi;

    //@FindBy (className = "ui-messages-info-summary")
    @FindBy (id = "mainForm:messages")
    private WebElement bilgilendirmeMesajAlani;

    @FindBy (className = "ui-messages-error-summary")
    private WebElement hataMesajAlani;

    public String miktarDegeriHatasi = "Aktarılacak miktar değeri gereklidir";




}
