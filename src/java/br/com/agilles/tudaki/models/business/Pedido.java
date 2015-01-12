package br.com.agilles.tudaki.models.business;

import java.util.List;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos
 * reservados
 *
 *
 * @author Jilles Ragonha
 * @category Web
 * @copyright Agilles
 * @updated 06/06/2014
 *
 * @description Pedido - Objeto que define os atributos de um Pedido.
 *
 *
 */
public class Pedido {

    private double numeroPedido;
    private List<Produto> produtosPedido;
    private FormaPagto pagamento;
    private Endereco enderecoEntrega;
    private Cliente cliente;
    private double valorFinal;

    public double getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(double numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public List<Produto> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<Produto> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    public FormaPagto getPagamento() {
        return pagamento;
    }

    public void setPagamento(FormaPagto pagamento) {
        this.pagamento = pagamento;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

}
