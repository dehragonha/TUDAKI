package br.com.agilles.tudaki.dao;

import br.com.agilles.tudaki.models.business.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * @description CategoriaDao - Objeto responsável pelas transações com banco de dados
 * 
 **/
public class CategoriaDao extends AbstractDao<Categoria> {

    @Override
    public boolean salvar(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> listarTodos() {
        Connection conexao = null;
        List<Categoria> arrayCategorias = new ArrayList<Categoria>();

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LISTAR_CATEGORIAS");
            Statement stm = conexao.createStatement();
            ResultSet resultado = stm.executeQuery(query);

            Categoria categoriaAtual = null;
            while (resultado.next()) {
                categoriaAtual = new Categoria();
                categoriaAtual.setId(resultado.getInt("id"));
                categoriaAtual.setDescricao(resultado.getString("descricao"));
                arrayCategorias.add(categoriaAtual);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            GerenciadorConexoes.pegarInstancia().fecharConexao(conexao);
        }

        return arrayCategorias;
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        Connection conexao = null;
        Categoria categoria = null;
        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String query = GerenciadorConexoes.pegarInstancia().pegarPropriedade("PEGAR_CATEGORIA_POR_ID");
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, idCategoria);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setDescricao(rs.getString("descricao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categoria;

    }
}
