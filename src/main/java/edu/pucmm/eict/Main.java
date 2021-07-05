package edu.pucmm.eict;

import edu.pucmm.eict.Database.DBConfig;
import edu.pucmm.eict.Database.DBConnection;
import edu.pucmm.eict.Services.UserServices;

public class Main {

    public static void main(String[] args) {
        System.out.println("si");
        //******************************************************//
        //Base de datos
        DBConfig.startDb();
        //Prueba de Conexión.
        DBConnection.getInstancia().testConexion();
        //******************************************************//

        if (UserServices.getInstancia().findAll().isEmpty()) {
            UserServices.init();
        }

    }

}
