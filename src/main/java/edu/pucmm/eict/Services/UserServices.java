package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class UserServices extends DBEntityManager<Usuario> {

    private static UserServices instancia;


    private UserServices() {
        super(Usuario.class);
    }

    public static UserServices getInstancia() {
        if (instancia == null) {
            instancia = new UserServices();
        }
        return instancia;
    }

    public static void init() {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword("admin");
        Usuario admin = new Usuario("admin", encryptedPassword, 1);
        encryptedPassword = passwordEncryptor.encryptPassword("123");
        Usuario yo = new Usuario("gabriel", encryptedPassword, 0);
        UserServices.getInstancia().insert(admin);
        UserServices.getInstancia().insert(yo);
    }

}
