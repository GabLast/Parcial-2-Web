package edu.pucmm.eict.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instancia;
    private String URL = "jdbc:h2:tcp://localhost/~/urlshortener"; //Modo Server


    private DBConnection() {
        registrarDriver();
    }

    public static DBConnection getInstancia() {
        if (instancia == null) {
            instancia = new DBConnection();
        }
        return instancia;
    }

    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
            //Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void testConexion() {
        try {
            getConexion().close();
            System.out.println("CONNECTION SUCCEDED");
        } catch (SQLException ex) {
            //Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
