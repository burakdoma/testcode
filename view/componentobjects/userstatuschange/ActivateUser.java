package tr.com.t2.bkmaaile.view.componentobjects.userstatuschange;

import tr.com.t2.bkmaaile.view.common.NameGenerator;
import tr.com.t2.bkmaaile.view.common.Servis;

/**
 * Created by burakdoma on 03/11/16.
 */
public class ActivateUser {

    Servis servis = new Servis();
    NameGenerator nameGenerator = new NameGenerator();

    public void kullaniciAktiveEt(String firstName, String lastName, String emailAddress, String phoneNumber){
        servis.activateUser(firstName, lastName, emailAddress, phoneNumber);
    }

    public void kullaniciAktiveEtEmailIle(String emailAddress){
        //TODO: phone number generator ekle
        servis.activateUser(nameGenerator.randomNameGenerator(), nameGenerator.randomNameGenerator(), emailAddress, "5394032872");
    }
}
