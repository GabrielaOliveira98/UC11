/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco de dados: " + e.getMessage());
            
            JOptionPane.showMessageDialog(null, "Erro,não foi possivel cadastrar o produto!");
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
       ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        conn = new conectaDAO().connectDB();
       
        String sql = "SELECT * FROM produtos";

        try (PreparedStatement stt = conn.prepareStatement(sql)) {
            try (ResultSet rss = stt.executeQuery()) {
                while (rss.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(rss.getInt("id"));
                    produto.setNome(rss.getString("nome"));
                    produto.setValor(rss.getInt("valor"));
                    produto.setStatus(rss.getString("status"));
                    listagem.add(produto);
                }
            }
            
                 } catch (SQLException ex) {
                System.out.println("Erro ao consultar produto: " + ex.getMessage());
                } 
        
        return listagem;
    }
    
    
    public void venderProduto (Integer idProduto){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, idProduto);
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro,não foi possivel vender produto!");
        }
        
    }
    
        
}

