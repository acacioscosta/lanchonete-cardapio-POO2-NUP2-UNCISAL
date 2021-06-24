/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author acacio
 */
public class ProdutoDAO {
    
    public void create(Produto p) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO produto (type, description, price) VALUES (?, ?, ?)");
            stmt.setString(1, p.getType());
            stmt.setString(2, p.getDescription());
            stmt.setString(3, p.getPrice());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public List<Produto> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> products = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getString("price"));
                
                products.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os produtos");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return products;
    }
    
    public void update(Produto p) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE produto SET type = ?, description = ?, price = ? WHERE ID = ?");
            stmt.setString(1, p.getType());
            stmt.setString(2, p.getDescription());
            stmt.setString(3, p.getPrice());
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void delete(Produto p) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Removido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public List<Produto> readByDescOrType(String text) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> products = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE description LIKE ? OR type LIKE ?");
            stmt.setString(1, "%"+text+"%");
            stmt.setString(2, "%"+text+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getString("price"));
                
                products.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os produtos");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return products;
    }
}