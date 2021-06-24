/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author acacio
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cardapio";
    private static final String USER = "root";
    private static final String PASS = "root1234";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com banco");
            throw new RuntimeException("Erro na conexão com banco: ", ex);
        }
    }
    
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar conexão com banco: ", ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        
        closeConnection(conn);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar PreparedStatement: ", ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        
        closeConnection(conn, stmt);
        
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar ResultSet: ", ex);
        }
    }
    
}
