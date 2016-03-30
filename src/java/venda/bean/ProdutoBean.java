/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import venda.dao.ProdutoDAO;
import venda.model.Produto;

/**
 *
 * @author informatica
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{

    private Produto produto = new Produto();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private String accao;

    private List<Produto> produtos;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.limpar();
        this.produto = produto;
    }

    public String getAccao() {
        return accao;
    }

    public void setAccao(String accao) {
        this.accao = accao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void operar() {
        switch (accao) {
            case "Guardar":
                this.guardar();
                this.limpar();
                break;
            case "Editar":
                this.editar();
                this.limpar();
                break;

        }
    }

    public void limpar(){
    this.produto.setCodigo(0);
    this.produto.setNome("");
    this.produto.setPreco(0);
    
    }
    /*
    private void guardar() {
        try {

            produtoDAO.guardar(produto);
            this.listar();
        } catch (Exception e) {

        }

    }

   private void editar() {
        try {

            produtoDAO.editar(produto);
            this.listar();
        } catch (Exception e) {

        }

    }

    public void eliminar(Produto pes) {
        try {

            produtoDAO.eliminar(pes);
            this.listar();
        } catch (Exception e) {

        }

    }

    public void listar() {

        try {
            produtos = produtoDAO.listar();

        } catch (Exception e) {

        }

    }*/
    private void guardar() {
        try {

            produtoDAO.guardar(produto);
            this.listar("V");
        } catch (Exception e) {

        }

    }

    private boolean isPostBack() {
        boolean resposta;
        resposta = FacesContext.getCurrentInstance().isPostback();
        return resposta;

    }

    private void editar() {
        try {

            produtoDAO.editar(produto);
            this.listar("V");
        } catch (Exception e) {

        }

    }

    public void eliminar(Produto pes) {
        try {

            produtoDAO.eliminar(pes);
            this.listar("V");
        } catch (Exception e) {

        }

    }

    public void listar(String valor) {

        try {
            if (valor.equals("F")) {
                if (isPostBack() == false) {
                    produtos = produtoDAO.listar();
                }
            } else {
                produtos = produtoDAO.listar();
            }

        } catch (Exception e) {

        }

    }


    public void lerId(Produto pes) {
        Produto temp;
        try {

            temp = produtoDAO.lerId(pes);
            if (temp != null) {
                this.produto = temp;
                this.accao="Editar";
            }
        } catch (Exception e) {

        }

    }
}
