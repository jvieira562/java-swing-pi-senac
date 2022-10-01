/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.sp.senac.exemplojdbc.data;

import static br.sp.senac.exemplojdbc.dao.NotaFiscalDAO.login;
import static br.sp.senac.exemplojdbc.dao.NotaFiscalDAO.senha;
import static br.sp.senac.exemplojdbc.dao.NotaFiscalDAO.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose.vsvieira
 */
public class DbConnection {

    private Connection _connection;
    public String url = "jdbc:mysql://127.0.0.1:3306/aula_1_jdbc";
    public String login = "jose.vsveira";
    public String senha = "P@$$w0rd";

    public boolean abrirConexao(){
                        
        try 
        {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            _connection= DriverManager.getConnection(url, login, senha);
            return true;
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
