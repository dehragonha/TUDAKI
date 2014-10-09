package br.com.agilles.tudaki.models.business;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description Cliente - Objeto que define os atributos do cliente(usuário mobile).
 * 
 **/
public class Cliente {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
