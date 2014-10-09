package br.com.agilles.tudaki.models.business;

import java.io.Serializable;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description Empresa - Objeto que define os atributos de uma Empresa.
 * 
 **/
public class Empresa implements Serializable{

    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private Ramo ramo;
    private Usuario usuario;
    private boolean status;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
