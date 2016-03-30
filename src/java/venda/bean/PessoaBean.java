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
import venda.dao.PessoaDAO;
import venda.model.Pessoa;

/**
 *
 * @author informatica
 */
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa = new Pessoa();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private String accao;

    private List<Pessoa> pessoas;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.limpar();
        this.pessoa = pessoa;
    }

    public String getAccao() {
        return accao;
    }

    public void setAccao(String accao) {
        this.accao = accao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
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

    public void limpar() {
        this.pessoa.setCodigo(0);
        this.pessoa.setNome("");
        this.pessoa.setSexo("");

    }

    private void guardar() {
        try {

            pessoaDAO.guardar(pessoa);
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

            pessoaDAO.editar(pessoa);
            this.listar("V");
        } catch (Exception e) {

        }

    }

    public void eliminar(Pessoa pes) {
        try {

            pessoaDAO.eliminar(pes);
            this.listar("V");
        } catch (Exception e) {

        }

    }

    public void listar(String valor) {

        try {
            if (valor.equals("F")) {
                if (isPostBack() == false) {
                    pessoas = pessoaDAO.listar();
                }
            } else {
                pessoas = pessoaDAO.listar();
            }

        } catch (Exception e) {

        }

    }

    public void lerId(Pessoa pes) {
        Pessoa temp;
        try {

            temp = pessoaDAO.lerId(pes);
            if (temp != null) {
                this.pessoa = temp;
                this.accao = "Editar";
            }
        } catch (Exception e) {

        }

    }
}
