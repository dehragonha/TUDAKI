package br.com.agilles.tudaki.models.business;

import java.util.List;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description Pedido - Objeto que define os atributos de um Pedido.
 * 
 **/
public class Pedido {
    private double numeroPedido;
    private List<Produto> produtosPedido;
    private FormaPagto pagamento;
    private Endereco enderecoEntrega;
    private Cliente cliente;
    private double valorFinal;
    
}
