package br.com.agilles.tudaki.dao;

import br.com.agilles.tudaki.models.beans.relatorios.RelatorioAdminProdutos;
import br.com.agilles.tudaki.models.business.Categoria;
import br.com.agilles.tudaki.models.business.Produto;
import br.com.agilles.tudaki.models.beans.relatorios.RelatorioProdutos;
import br.com.agilles.tudaki.models.business.Empresa;
import br.com.agilles.tudaki.models.business.Pedido;
import br.com.agilles.tudaki.models.business.Usuario;
import br.com.agilles.tudaki.utils.SessionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * @description ProdutoDao - CRUD relacionado ao Produto
 *
 *
 */
public class ProdutoDao extends AbstractDao<Produto> {

    @Override
    public boolean salvar(Produto produto) {

        Connection conexao = null;
        boolean produtoSalvo = false;
        Usuario usuario = (Usuario) SessionUtils.getInstance().read(SessionUtils.USUARIO);

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String sql = GerenciadorConexoes.pegarInstancia().pegarPropriedade("GRAVAR_PRODUTO");
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValorUnitario());
            ps.setInt(3, produto.getQtdeEstoque());
            ps.setInt(4, produto.getCategoria().getId());
            ps.setLong(5, usuario.getIdEmpresa());

            if (ps.executeUpdate() > 0) {
                produtoSalvo = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);

        }
        return produtoSalvo;
    }

    @Override
    public boolean remover(Produto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Produto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Produto produto) {
        Connection conexao = null;
        boolean atualizado = false;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("ATUALIZAR_PRODUTO");
            PreparedStatement ps = conexao.prepareStatement(query);

            //descricao = ?, qtde_estoque = ?, valor_unit = ? WHERE id = ?; 
            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQtdeEstoque());
            ps.setDouble(3, produto.getValorUnitario());
            ps.setLong(4, produto.getId());

            if (ps.executeUpdate() > 0) {
                atualizado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);

        }
        return atualizado;
    }

    @Override
    public List<Produto> listarTodos() {
        Usuario usuario = (Usuario) SessionUtils.getInstance().read(SessionUtils.USUARIO);
        Connection conexao = null;
        List<Produto> produtos = new ArrayList<>();
        Produto produtoAtual;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_PRODUTOS");

            PreparedStatement ps = conexao.prepareStatement(query);

            ps.setInt(1, usuario.getIdEmpresa());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                produtoAtual = new Produto();
                produtoAtual.setId(rs.getLong("id"));
                produtoAtual.setDescricao(rs.getString("descricao"));
                produtoAtual.setQtdeEstoque(rs.getInt("qtde_estoque"));
                produtoAtual.setValorUnitario(rs.getDouble("valor_unit"));
                produtos.add(produtoAtual);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);

        }
        return produtos;

    }

    public Long pegarId() {
        Connection conexao = null;
        Produto produtoAtual;
        List<Produto> listaProdutos;
        Long ultimoId = null;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("PEGAR_ID_PRODUTO_PARA_EXIBICAO");
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery(query);

            listaProdutos = new ArrayList<>();
            while (rs.next()) {
                produtoAtual = new Produto();
                produtoAtual.setId(rs.getLong("id"));
                listaProdutos.add(produtoAtual);

                if (rs.last()) {
                    ultimoId = rs.getLong("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);
        }
        return ultimoId + 1;

    }

    public List<RelatorioProdutos> listarProdutosParaRelatorio() {

        Usuario usuario = (Usuario) SessionUtils.getInstance().read(SessionUtils.USUARIO);
        Connection conexao = null;
        List<RelatorioProdutos> produtos = new ArrayList<>();
        RelatorioProdutos produtoAtual;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_PRODUTOS");

            PreparedStatement ps = conexao.prepareStatement(query);

            ps.setInt(1, usuario.getIdEmpresa());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                produtoAtual = new RelatorioProdutos();
                produtoAtual.setEstoque(rs.getInt("qtde_estoque"));
                produtoAtual.setProduto(rs.getString("descricao"));
                produtoAtual.setValorUnitario(rs.getDouble("valor_unit"));
                produtos.add(produtoAtual);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);

        }
        return produtos;

    }

    public List<RelatorioAdminProdutos> listarProdutosAdministradorParaRelatorio() {

        Usuario usuario = (Usuario) SessionUtils.getInstance().read(SessionUtils.USUARIO);
        Connection conexao = null;
        List<RelatorioAdminProdutos> produtos = new ArrayList<>();
        RelatorioAdminProdutos produtoAtual;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("RELATORIO_ADMIN_PRODUTOS");
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery(query);
            Categoria categoriaAtual;
            Empresa empresaAtual;

            while (rs.next()) {

                produtoAtual = new RelatorioAdminProdutos();

                produtoAtual.setCategoria(rs.getString("categoria"));
                produtoAtual.setEmpresa(rs.getString("empresa"));

                produtoAtual.setEstoque(rs.getInt("estoque"));
                produtoAtual.setProduto(rs.getString("produto"));
                produtoAtual.setValorUnit(rs.getDouble("valorUnit"));

                produtos.add(produtoAtual);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);
        }
        return produtos;

    }
    
     public List<Produto> itensPedido(Pedido pedido) {
        Connection conexao = null;
        List<Produto> listaProdutos = new ArrayList<Produto>();

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String sql = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_ITENS_PEDIDO");

            // atribui valor para a query
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setLong(1, pedido.getNumeroPedido());

            ResultSet resultado = null;
            if (ps.executeUpdate() > 0) {
                resultado = ps.executeQuery();
            }
            Produto produtoAtual;

            while (resultado.next()) {
                produtoAtual = new Produto();
                produtoAtual.setDescricao(resultado.getString("descricao"));
                produtoAtual.setValorUnitario(resultado.getDouble("valor_unit"));
                
                listaProdutos.add(produtoAtual);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

}
