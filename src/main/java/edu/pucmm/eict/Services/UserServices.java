package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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
        Usuario yo = new Usuario("gab", encryptedPassword, 0);
        UserServices.getInstancia().insert(admin);
        UserServices.getInstancia().insert(yo);
    }

    public Usuario getUserByUsername(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u where u.username = :username", Usuario.class);
        query.setParameter("username", username);

        List<Usuario> results = query.getResultList();

        if(results.size() > 0)
        {
            return results.get(0);
        }else {
            return null;
        }

    }

    public Usuario login(String username, String password) {
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

    public boolean usernameUnico(String username)
    {
        Usuario user = getUserByUsername(username);
        if(user == null)
        {
            return true;
        }else{
            return false;
        }
    }

    public boolean registrarUser(String username, String password)
    {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        Usuario nuevo = new Usuario(username, encryptedPassword);

        UserServices.getInstancia().insert(nuevo);

        if(getUserByUsername(nuevo.getUsername()) != null) {
            return true;
        }else {
            return false;
        }
    }

    public List<Url> getUsersPaginated(int page){
        EntityManager em = getEntityManager();

        int rowsPerPage = 10;
        int selectedPage = page;

        Query selectQuery = em.createQuery("select p From Usuario p where p.borrado = 0 and p.username != :username");
        selectQuery.setParameter("username", "admin");
        selectQuery.setFirstResult((selectedPage - 1) * rowsPerPage);
        selectQuery.setMaxResults(rowsPerPage);

        selectQuery.getFirstResult();
        List<Url> lista = selectQuery.getResultList();

        return lista;
    }
}
