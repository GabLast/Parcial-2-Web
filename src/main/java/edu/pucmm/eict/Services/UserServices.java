package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public Usuario getUserByUsername(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u where u.username = :username", Usuario.class);
        query.setParameter("username", username);
        return (Usuario) query.getResultList().get(0);
    }

    public static Usuario login(String username, String password) {
        Usuario usuario;
        usuario = UserServices.getInstancia().getUserByUsername(username);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

        if (usuario != null) {
            if (passwordEncryptor.checkPassword(password, usuario.getPassword())) {
                return usuario;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
