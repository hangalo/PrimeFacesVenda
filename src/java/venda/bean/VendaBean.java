/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import venda.dao.VendaDAO;
import venda.model.DetalheVenda;
import venda.model.Produto;
import venda.model.Venda;

/**
 *
 * @author informatica
 */
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{

    private Venda venda = new Venda();

    private Produto produto = new Produto();
    private int quantidade;
    private List<DetalheVenda> detalhesVenda = new ArrayList<>();

    public VendaBean() {
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<DetalheVenda> getDetalhesVenda() {
        return detalhesVenda;
    }

    public void setDetalhesVenda(List<DetalheVenda> detalhesVenda) {
        this.detalhesVenda = detalhesVenda;
    }

    public void adicionar() {
        DetalheVenda dadosVenda = new DetalheVenda();
        dadosVenda.setQuantidade(quantidade);
        dadosVenda.setProduto(produto);
        this.detalhesVenda.add(dadosVenda);
    }

    public void registar() {
        VendaDAO dao;
        double valorTotal=0;
        try {
            for(DetalheVenda dv:detalhesVenda){
                valorTotal +=dv.getProduto().getPreco()*dv.getQuantidade();
            }
            
            dao = new VendaDAO();
            
            venda.setValor(valorTotal);
            dao.guardar(venda, detalhesVenda);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Venda registada com sucesso"));
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Erro ao registar venda"));
            throw e;
            
        }finally{
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }

    }

}
