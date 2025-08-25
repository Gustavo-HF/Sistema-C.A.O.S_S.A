package DAO;


import java.sql.Connection;
import java.sql.DriverManager;

    public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/db_caos";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



