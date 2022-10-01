package br.sp.senac.exemplojdbc.dao;

import br.sp.senac.exemplojdbc.model.NotaFiscal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.fernandes
 */
public class NotaFiscalDAO {

    public static String url = "jdbc:mysql://127.0.0.1:3306/aula_1_jdbc";
    public static String login = "jose.vsvieira";
    public static String senha = "@Teste123";

    public static boolean salvar(NotaFiscal obj) {

        Connection conexao = null;
        boolean retorno = false;

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Abrir conexão
            conexao = DriverManager.getConnection(url, login, senha);
            //Criar conexão
            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO notafiscal (numeroNota, valorNota) VALUES (?,?)");
            //Atribuino parametros
            comandoSQL.setInt(1, obj.getNumeroNota());
            comandoSQL.setDouble(2, obj.getValorNota());
            int linhasAfetadas = comandoSQL.executeUpdate();
            if (linhasAfetadas > 0) retorno = true;
            
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());            
        }
        return retorno;
    }

    public static ArrayList<NotaFiscal> listar() {
        ArrayList<NotaFiscal> lista = new ArrayList<NotaFiscal>();
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM notafiscal;");
            
            ResultSet resultado = comandoSQL.executeQuery();
            while(resultado.next()){
                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.setIdNota(resultado.getInt("idNota"));
                notaFiscal.setNumeroNota(resultado.getInt("numeroNota"));
                notaFiscal.setValorNota(resultado.getDouble("valorNota"));
                lista.add(notaFiscal);
            }
        } catch (Exception ex) {
            Logger.getLogger(NotaFiscalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static NotaFiscal consultarPorID(int idNota) {
        NotaFiscal nf = new NotaFiscal();
        Connection conexao = null;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Abrir conexão
            conexao = DriverManager.getConnection(url, login, senha);
            //Criar conexão
            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM notafiscal WHERE idNota = (?)");
            //Atribuino parametros
            
            comandoSQL.setInt(1, idNota);
            
            ResultSet resultado = comandoSQL.executeQuery();
            
            nf.setIdNota(resultado.getInt("idNota"));
            nf.setNumeroNota(resultado.getInt("numeroNota"));
            nf.setValorNota(resultado.getDouble("valorNota"));
            return nf;
            
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());            
        }
        
        return nf;
    }

    public static boolean atualizar(NotaFiscal obj) {
        boolean retorno = false;

       Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            PreparedStatement comandoSQL = conexao.prepareStatement("UPDATE notafiscal SET numeroNota = (?), valorNota = (?)  WHERE idNota = (?);");
            comandoSQL.setInt(1, obj.getIdNota());
            comandoSQL.setInt(2, obj.getNumeroNota());
            comandoSQL.setDouble(3, obj.getValorNota());
            int linhasAlteradas = comandoSQL.executeUpdate();
            if(linhasAlteradas > 0) retorno = true;
        } catch (Exception ex) {
            Logger.getLogger(NotaFiscalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
