/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agilles.tudaki.dao;

import br.com.agilles.tudaki.models.business.Cliente;
import br.com.agilles.tudaki.models.business.Endereco;
import br.com.agilles.tudaki.models.business.Pedido;
import br.com.agilles.tudaki.models.business.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inteligencia
 */
public class PedidoDao extends AbstractDao<Pedido> {

    @Override
    public boolean salvar(Pedido objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(Pedido objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Pedido objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Pedido objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> listarTodos() {
        Connection conexao = null;
        List<Pedido> listaPedidos = new ArrayList<>();
        Cliente cliente;
        Endereco endereco;
        List<Produto> itensPedido = new ArrayList<>();

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();

            String queryPedidos = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_PEDIDOS");
            Statement stm = conexao.createStatement();
            ResultSet resultadoGeral = stm.executeQuery(queryPedidos);

            Pedido pedidoAtual;

            while (resultadoGeral.next()) {
                pedidoAtual = new Pedido();
                cliente = new Cliente();
                endereco = new Endereco();

                pedidoAtual.setNumeroPedido(resultadoGeral.getLong("numeroPedido"));
                pedidoAtual.setValorFinal(resultadoGeral.getDouble("valor_total"));
                cliente.setNome(resultadoGeral.getString("nome"));
                endereco.setBairro("bairro");
                endereco.setCidade("cidade");
                endereco.setLogradouro("logradouro");
                pedidoAtual.setCliente(cliente);
                pedidoAtual.setEnderecoEntrega(endereco);
                listaPedidos.add(pedidoAtual);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);
        }
        return listaPedidos;
    }

   
}
