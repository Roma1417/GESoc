package utn.dds.tpAnual.db.service.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityData {
    private String key = Base64.getEncoder().encodeToString(KeyGenerator.getInstance("AES").generateKey().getEncoded());
    private static SecurityData instance;
    private SecurityData() throws NoSuchAlgorithmException {

    }
    public static SecurityData getInstance(){
        if (instance == null) {
            try {
                instance = new SecurityData();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getKey() {
        return key;
    }
}
