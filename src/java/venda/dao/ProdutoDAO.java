/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import venda.model.Produto;
import venda.util.Conexao;

/**
 *
 * @author informatica
 */
public class ProdutoDAO extends Conexao {

    public void guardar(Produto produto) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("INSERT INTO produto(nome_produto, preco_produto) values(?,?)");
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            this.fechar();
        }
    }
    
    
     public void editar(Produto produto) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("UPDATE produto SET nome_produto=?, preco_produto=? WHERE codigo_produto =?");
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getCodigo());
            System.out.println("venda.dao.ProdutoDAO.editar()+ Com sucesso");
            ps.executeUpdate();
        } catch (SQLException ex) {
             System.err.println("Erro ao Actualizar"+ex.getMessage());
        } finally {
            this.fechar();
        }
    }
     
     
      public void eliminar(Produto produto) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("DELETE FROM produto WHERE codigo_produto =?");
            
            ps.setInt(1, produto.getCodigo());
            System.out.println("venda.dao.ProdutoDAO.Eliminar()+ Com sucesso");
            ps.executeUpdate();
        } catch (SQLException ex) {
             System.err.println("Erro ao Actualizar"+ex.getMessage());
        } finally {
            this.fechar();
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("SELECT codigo_produto, nome_produto, preco_produto FROM produto");
            rs = ps.executeQuery();

            while (rs.next()) {

                Produto pes = new Produto();
                pes.setCodigo(rs.getInt("codigo_produto"));
                pes.setNome(rs.getString("nome_produto"));
                pes.setPreco(rs.getDouble("preco_produto"));

                produtos.add(pes);
            }

        } catch (SQLException ex) {

        } finally {
            this.fechar();
        }
        return produtos;
    }
    
    public Produto lerId(Produto pes){
        
        Produto produto = null;
        ResultSet rs;
    try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("SELECT codigo_produto, nome_produto, preco_produto FROM produto WHERE codigo_produto=?");
            ps.setInt(1, pes.getCodigo());
           
           rs= ps.executeQuery();
           
           while(rs.next()){
           produto = new Produto();
           produto.setCodigo(rs.getInt("codigo_produto"));
           produto.setNome(rs.getString("nome_produto"));
           produto.setPreco(rs.getDouble("preco_produto"));
           
           }
        } catch (SQLException ex) {
        } finally {
            this.fechar();
        }
        return produto;
    }
    
    
    
}
