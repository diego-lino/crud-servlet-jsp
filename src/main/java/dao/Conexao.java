package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String driver = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://iecdiego.southcentralus.cloudapp.azure.com/iec_crud?useTimezone=true&serverTimexone=UTC";
    private String USER = "root";
    private String SENHA = "root";
    private Connection conn;

    public Conexao(){
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        return conn;
    }

    public void fecharConexao(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
