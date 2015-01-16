/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agilles.tudaki.models.beans;

import br.com.agilles.tudaki.dao.PedidoDao;
import br.com.agilles.tudaki.dao.ProdutoDao;
import br.com.agilles.tudaki.models.business.Pedido;
import br.com.agilles.tudaki.models.business.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * *
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos
 * reservados
 *
 *
 * @author Jilles Ragonha
 * @category Web
 * @copyright Agilles
 * @updated 06/01/2015
 *
 * @description Pedido Bean - Objeto que fará o controle dos pedidos no sistema.
 *
 *
 *
 */
@ManagedBean
@RequestScoped
public class PedidoBean implements Serializable {

    private Pedido pedido = new Pedido();
    private Pedido pedidoSelecionado;
    private List<Pedido> listaPedidos;
    
    private PedidoDao dao = new PedidoDao();
    private ProdutoDao daoProduto = new ProdutoDao();

    public PedidoDao getDao() {
        return dao;
    }

    public void setDao(PedidoDao dao) {
        this.dao = dao;
    }

    public ProdutoDao getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDao daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public List<Pedido> getListaPedidos() {
        return dao.listarTodos();
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Produto> detalharPedido() {
        return daoProduto.itensPedido(pedidoSelecionado);
    }

    

}
