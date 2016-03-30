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
import venda.model.Pessoa;
import venda.util.Conexao;

/**
 *
 * @author informatica
 */
public class PessoaDAO extends Conexao {

    public void guardar(Pessoa pessoa) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("INSERT INTO pessoa(nome_pessoa, sexo) values(?,?)");
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getSexo());
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            this.fechar();
        }
    }
    
    
     public void editar(Pessoa pessoa) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("UPDATE pessoa SET nome_pessoa=?, sexo=? WHERE codigo_pessoa =?");
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getSexo());
            ps.setInt(3, pessoa.getCodigo());
            System.out.println("venda.dao.PessoaDAO.editar()+ Com sucesso");
            ps.executeUpdate();
        } catch (SQLException ex) {
             System.err.println("Erro ao Actualizar"+ex.getMessage());
        } finally {
            this.fechar();
        }
    }
     
     
      public void eliminar(Pessoa pessoa) {
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("DELETE FROM pessoa WHERE codigo_pessoa =?");
            
            ps.setInt(1, pessoa.getCodigo());
            System.out.println("venda.dao.PessoaDAO.Eliminar()+ Com sucesso");
            ps.executeUpdate();
        } catch (SQLException ex) {
             System.err.println("Erro ao Actualizar"+ex.getMessage());
        } finally {
            this.fechar();
        }
    }

    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("SELECT codigo_pessoa, nome_pessoa, sexo FROM pessoa");
            rs = ps.executeQuery();

            while (rs.next()) {

                Pessoa pes = new Pessoa();
                pes.setCodigo(rs.getInt("codigo_pessoa"));
                pes.setNome(rs.getString("nome_pessoa"));
                pes.setSexo(rs.getString("sexo"));

                pessoas.add(pes);
            }

        } catch (SQLException ex) {

        } finally {
            this.fechar();
        }
        return pessoas;
    }
    
    public Pessoa lerId(Pessoa pes){
        
        Pessoa pessoa = null;
        ResultSet rs;
    try {
            this.Conectar();
            PreparedStatement ps = this.getCn().prepareStatement("SELECT codigo_pessoa, nome_pessoa, sexo FROM pessoa WHERE codigo_pessoa=?");
            ps.setInt(1, pes.getCodigo());
           
           rs= ps.executeQuery();
           
           while(rs.next()){
           pessoa = new Pessoa();
           pessoa.setCodigo(rs.getInt("codigo_pessoa"));
           pessoa.setNome(rs.getString("nome_pessoa"));
           pessoa.setSexo(rs.getString("sexo"));
           
           }
        } catch (SQLException ex) {
        } finally {
            this.fechar();
        }
        return pessoa;
    }
    
    
    
}
