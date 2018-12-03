/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hmreb
 */
public class UsuarioDAO {
    
    public void Salvar(pulsevida.Usuario novoUsuario) throws ClassNotFoundException, SQLException{
        Connection c = null;
        Statement stmt = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:pulseVidaDB.db");        
            System.out.println("Base conectada.");  
            stmt = c.createStatement();
            
            String query = "INSERT INTO tbl_usuario (ID,NOME,CELULAR,EMAIL,LOGIN,SENHA) "
                    + "VALUES (" + novoUsuario.getId() + ",'" + 
                    novoUsuario.getNome()+ "','" +
                    novoUsuario.getCelular()+ "','" +
                    novoUsuario.getEmail()+ "','" +
                    novoUsuario.getLogin()+ "','" +
                    novoUsuario.getSenha()+ "');";
            
            stmt.executeUpdate(query);
            stmt.close();
            //c.commit();
            c.close();
        }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());            
        }
        System.out.println("Salvo com sucesso.");
    }
    
    public synchronized ArrayList selectTable() {
        ArrayList listUsuarios = new ArrayList();        
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:pulseVidaDB.db");            
            System.out.println("Base conectada.");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_usuario;");
            while (rs.next()) {
                pulsevida.Usuario usuario = new pulsevida.Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setCelular(rs.getString("CELULAR"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setSenha(rs.getString("SENHA"));
                listUsuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());            
        }
        System.out.println("Operation done successfully");
        
        return listUsuarios;
    }
}