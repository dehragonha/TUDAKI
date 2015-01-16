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
            Statement stmPedido = conexao.createStatement();
            ResultSet resultadoGeral = stmPedido.executeQuery(queryPedidos);

            String queryItensPedido = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_ITENS_PEDIDO");
            PreparedStatement psItensPedido = conexao.prepareStatement(queryItensPedido);
            ResultSet resultadoItensPedido;

            Pedido pedidoAtual;
            Produto produtoAtual;

            while (resultadoGeral.next()) {
                pedidoAtual = new Pedido();
                cliente = new Cliente();
                endereco = new Endereco();

                pedidoAtual.setNumeroPedido(resultadoGeral.getLong("numeroPedido"));
                psItensPedido.setLong(1, pedidoAtual.getNumeroPedido());
                resultadoItensPedido = psItensPedido.executeQuery();

                while (resultadoItensPedido.next()) {
                    produtoAtual = new Produto();
                    produtoAtual.setDescricao(resultadoItensPedido.getString("descricao"));
                    produtoAtual.setValorUnitario(resultadoItensPedido.getDouble("valor_unit"));
                    itensPedido.add(produtoAtual);

                }

                pedidoAtual.setValorFinal(resultadoGeral.getDouble("valor_total"));
                cliente.setNome(resultadoGeral.getString("nome"));
                endereco.setBairro(resultadoGeral.getString("bairro"));
                endereco.setCidade(resultadoGeral.getString("cidade"));
                endereco.setLogradouro(resultadoGeral.getString("logradouro"));
                pedidoAtual.setCliente(cliente);
                pedidoAtual.setEnderecoEntrega(endereco);
                pedidoAtual.setProdutosPedido(itensPedido);

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
