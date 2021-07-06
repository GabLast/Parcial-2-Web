package edu.pucmm.eict.Database;

import org.h2.tools.Server;

import java.sql.SQLException;

public class DBConfig {
    private static Server server;

    public static void startDb()  {
        try {
            server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon", "-ifNotExists").start();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void stopDb() throws SQLException {
        if(server!=null) {
            server.stop();
        }
    }
}
