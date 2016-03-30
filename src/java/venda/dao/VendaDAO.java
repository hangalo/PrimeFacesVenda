/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import venda.model.DetalheVenda;
import venda.model.Venda;
import venda.util.Conexao;

/**
 *
 * @author informatica
 */
public class VendaDAO extends Conexao {

    public void guardar(Venda venda, List<DetalheVenda> lista) {
        try {
            this.Conectar();
            this.getCn().setAutoCommit(false);
            PreparedStatement ps = this.getCn().prepareStatement("INSERT INTO venda(data, codigo_pessoa, valor) values(?,?,?)");
            ps.setDate(1, venda.getData());
            ps.setInt(2, venda.getPessoa().getCodigo());
            ps.setDouble(3, venda.getValor());
             System.out.println("Passou=======>====>====> Passou => 010");
            ps.executeUpdate();
             System.out.println("Passou=======>====>====> Passou => 011");
            ps.close();
            System.out.println("Passou=======>====>====> Passou => 012");

            PreparedStatement psRecCodigo = this.getCn().prepareStatement("SELECT LAST_INSERT_ID() FROM venda limit 1");
            ResultSet rs;
            int codigoVenda = 0;
            rs = psRecCodigo.executeQuery();
            while (rs.next()) {
                 System.out.println("Passou=======>====>====> Passou 0> Loop");
                codigoVenda = rs.getInt(1);
            }
            rs.close();

            System.out.println("Passou=======>====>====> Passou 0> 02");
            for (DetalheVenda dv : lista) {
                PreparedStatement psDetalhe = this.getCn().prepareStatement("INSERT INTO detalhe_venda(codigo_venda,codigo_produto, quantidade) values(?,?,?)");
                psDetalhe.setInt(1, codigoVenda);
                psDetalhe.setInt(2, dv.getProduto().getCodigo());
                System.out.println("Passou=======>====>====> Passou 0> 03");
                psDetalhe.setInt(3, dv.getQuantidade());
                psDetalhe.executeUpdate();
                psDetalhe.close();

            }
            this.getCn().commit();

        } catch (SQLException ex) {
            try {
                this.getCn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            this.fechar();
        }
    }

}
