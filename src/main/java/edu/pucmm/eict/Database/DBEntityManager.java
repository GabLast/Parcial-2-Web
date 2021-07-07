package edu.pucmm.eict.Database;

import edu.pucmm.eict.Main;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class DBEntityManager<T> {
    private static EntityManagerFactory emf;
    private Class<T> claseEntidad;


    public DBEntityManager(Class<T> claseEntidad) {

        if(emf == null) {
            if(Main.getModoConexion().equalsIgnoreCase("Heroku")){
                emf = getConfiguracionBaseDatosHeroku();
            } else {
                emf = Persistence.createEntityManagerFactory("urlshortener");
            }
        }
        this.claseEntidad = claseEntidad;

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public T insert(T entidad) throws IllegalArgumentException, EntityExistsException, PersistenceException {
        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return entidad;
    }

    public T update(T entidad) throws PersistenceException {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entidad);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entidad;
    }

    public boolean delete(Object entidadId) throws PersistenceException {
        boolean ok = false;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entidad = em.find(claseEntidad, entidadId);
            em.remove(entidad);
            em.getTransaction().commit();
            ok = true;
        } finally {
            em.close();
        }
        return ok;
    }


    public T find(Object id) throws PersistenceException {
        EntityManager em = getEntityManager();
        try {
            return em.find(claseEntidad, id);
        } finally {
            em.close();
        }
    }


    public List<T> findAll() throws PersistenceException {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(claseEntidad);
            criteriaQuery.select(criteriaQuery.from(claseEntidad));
            return em.createQuery(criteriaQuery).getResultList();
        } finally {
            em.close();
        }
    }

    private EntityManagerFactory getConfiguracionBaseDatosHeroku(){
        //Leyendo la información de la variable de ambiente de Heroku
        String databaseUrl = System.getenv("DATABASE_URL");
        StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
        //Separando las información del conexión.
        String dbVendor = st.nextToken();
        String userName = st.nextToken();
        String password = st.nextToken();
        String host = st.nextToken();
        String port = st.nextToken();
        String databaseName = st.nextToken();
        //creando la jbdc String
        String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?sslmode=require", host, port, databaseName);
        //pasando las propiedades.
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url", jdbcUrl );
        properties.put("javax.persistence.jdbc.user", userName );
        properties.put("javax.persistence.jdbc.password", password );
        //
        return Persistence.createEntityManagerFactory("Heroku", properties);
    }
}
