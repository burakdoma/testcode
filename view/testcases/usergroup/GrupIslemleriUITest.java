package tr.com.t2.bkmaaile.view.testcases.usergroup;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.PageFactory;
import tr.com.t2.bkmaaile.view.base.BaseUITestCase;
import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.RandomGenerator;
import tr.com.t2.bkmaaile.view.componentobjects.usergroup.GrupIslemleriAnasayfa;
import tr.com.t2.bkmaaile.view.componentobjects.main.Login;
import java.io.IOException;

/**
 * Created by burakdoma on 18.10.2016.
 */
@Ignore
public class GrupIslemleriUITest extends BaseUITestCase {

    GrupIslemleriAnasayfa groupPage;
    Login login;
    RandomGenerator randomGenerator = new RandomGenerator();

    @Before
    public void beforeMethod(){

        super.beforeMethod();

        driver.get(loginPage);
        login = PageFactory.initElements(driver, Login.class);
        login.girisYap(dogruEposta, dogruSifre);

        driver.get(userGroupPage);
        groupPage = PageFactory.initElements(driver, GrupIslemleriAnasayfa.class);
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

    @Test // Tanımlı grup listesini ekleme
    public void tanimliGrupListesiniEkle() throws InterruptedException {
        groupPage.tanimliGrupListesiniEkle();
    }

    @Test // Grup ekleme başarılı
    public void yeniGrupEkleBasarili() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);
    }

    @Test // Grup ekleme başarısız - aynı isim
    public void yeniGrupEklemeVarolan() throws IOException, InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);

        groupPage.grupEkle(grupIsmi);
        groupPage.hataMesajiGosterimKontrolu(groupPage.grupOncedenTanımlı);
    }

    @Test // Grup ekleme başarısız - boş isim
    public void yeniGrupEklemeBosIsim() throws IOException, InterruptedException {

        groupPage.grupEkle("");
        groupPage.hataMesajiGosterimKontrolu(groupPage.grupAdıBosOlamaz);
    }

    @Ignore
//    @Test // Grup ekleme - vazgeçme
    public void yeniGrupEklemeVazgec() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupIsmiGir(grupIsmi);
        groupPage.milisaniyeBekle(1000);
        groupPage.grupEklemeVazgec();
        groupPage.grupIsmiBosMu(groupPage.grupIsmiDegeriniOku());
    }

    @Test // Çoklu grup ekleme
    public void yeniCokluGrupEkleme() throws InterruptedException {

        groupPage.cokluGrupEkle(groupPage.rastgeleGrupSayisi);
    }

    @Test // Grup adı güncelle - kaydet
    public void varolanGrubuGuncelleme() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();
        String yeniGrupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);

        groupPage.grupIsmiFiltreleme(grupIsmi);

        groupPage.dataTableSatirdakiGuncelleButonunaTikla(groupPage.dataTableDataninBulunduguSatiriGetir(grupIsmi));
        groupPage.milisaniyeBekle(1000);

        if (!groupPage.grupIsmiAyniMi(grupIsmi, groupPage.grupIsmiDegeriniOku())) {
            return;
        }

        groupPage.grupEkle(yeniGrupIsmi);
        groupPage.milisaniyeBekle(1000);

        if(groupPage.dataTableDataninBulunduguSatiriGetir(grupIsmi) < 0)
            groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupGuncellemeBasarili);
    }

    @Test // Grup adı güncelle - vazgeç
    public void varolanGrubuGuncellemeVazgec() {

    }

    @Test // Grup sil - başarılı
    public void varolanGrubuSilme() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);

        groupPage.grupIsmiFiltreleme(grupIsmi);

        groupPage.dataTableSatirdakiSilButonunaTikla(groupPage.dataTableDataninBulunduguSatiriGetir(grupIsmi));
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupSilmeBasarili);
    }

    // TODO : Grup sil - başarısız - kullanıcısı olan
    @Test
    public void varolanGrubuSilmeBasarisiz() {}

    @Test // Gruptaki kullanıcıları görüntüle - boş
    public void gruptakiKullanicilariGoruntuleBos() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);

        groupPage.grupIsmiFiltreleme(grupIsmi);
        groupPage.dataTableSatirdakiGoruntuleButonunaTikla(0);

        groupPage.milisaniyeBekle(1000);
        Assert.assertTrue(driver.getPageSource().contains("Kayıt bulunamadı"));
    }

    // TODO : Gruptaki kullanıcıları görüntüle - dolu
    @Test
    public void grupTakiKullaniclariGoruntuleDolu() throws InterruptedException {

    }

    @Test // Rastgele kullanıcı grup ismi filtreleme
    public void grupIsmiFiltrelemeRastgele() throws InterruptedException {

        String grupIsmi = groupPage.grupIsmiUret();

        groupPage.grupEkle(grupIsmi);
        groupPage.bilgilendirmeMesajiGosterimKontrolu(groupPage.grupEklemeBasarili);

        // TODO : burada delaylık bir durum var. Kontrol et !!

        groupPage.grupIsmiFiltreleme(grupIsmi);
        groupPage.filtreliGrupIsmiGirilenKriterIleMiBasliyor(groupPage.dataTableKayitSayisi(), grupIsmi);
    }

    @Test // Tanımlı değerler ile kullanıcı grup ismi filtreleme
    // Pre-condition : tanimliGrupListesiniEkle
    public void grupIsmiFiltrelemeTanimli() throws InterruptedException {

        groupPage.grupIsmiFiltreleme("Grup");
        groupPage.filtreliGrupIsmiGirilenKriterIleMiBasliyor(groupPage.dataTableKayitSayisi(), "Grup");
    }

    @Test // Kullanıcı grup ismi sorting
    public void grupListesiniIsmeGoreSirala() throws InterruptedException {

        groupPage.siralamaButonunaTikla();
    }

    @Test // Sayfa paging
    public void sayfaPagingKontrol() {}

    @After
    public void afterMethod() {
        driver.quit();
    }
    
}