/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navaclientesplaca;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */



public class clsConexion {
    
    static String servidor="192.168.1.9";
    static String puerto="1433";
    static String user="sa";
    static String password="nava2008";
    static String base="bdnava01";
    static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public static Connection Cadena() throws ClassNotFoundException, SQLException {
                    /*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");*/
            Class.forName(driver);
            String url="jdbc:sqlserver://"+servidor+":"+puerto+";"+"databasename="+base+";user="+user+";password="+password+";";
            Connection cnn=DriverManager.getConnection(url);
            return cnn;                           
      
    }
    
}




