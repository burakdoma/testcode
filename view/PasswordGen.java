package tr.com.t2.bkmaaile.view;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by omermetehandanaci
 */
public class PasswordGen {

    @Test
    public void generatePassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
        System.out.println(bCryptPasswordEncoder.encode("bkm"));
    }
}
