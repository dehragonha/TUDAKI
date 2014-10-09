package br.com.agilles.tudaki.dao;

import br.com.agilles.tudaki.models.business.Empresa;
import br.com.agilles.tudaki.models.business.Usuario;
import br.com.agilles.tudaki.utils.Mensagens;
import br.com.agilles.tudaki.utils.SessionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;

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
 * @description UsuarioDao - CRUD relacionado ao Usuario
 *
 *
 */
public class UsuarioDao extends AbstractDao<Usuario> {

    public boolean login(Usuario usuario) {
        Connection conexao = null;
        boolean resultadoLogin = false;
        Empresa empresa;

        try {
            conexao = GerenciadorConexoes.pegarInstancia().abrirConexao();
            String querySql = GerenciadorConexoes.pegarInstancia().pegarPropriedade("LOGIN_USUARIO");
            String querySqlEmpresa = GerenciadorConexoes.pegarInstancia().pegarPropriedade("GRAVAR_EMPRESA_LOGIN");

            PreparedStatement psUsuario = conexao.prepareStatement(querySql);
            PreparedStatement psEmpresa = conexao.prepareStatement(querySqlEmpresa);

            psUsuario.setString(1, usuario.getNome());
            psUsuario.setString(2, usuario.getSenha());

            ResultSet rs = psUsuario.executeQuery();

            if (rs.next()) {
                usuario.setIdEmpresa(rs.getInt("id_empresa"));
                psEmpresa.setLong(1, usuario.getIdEmpresa());
                ResultSet rsEmpresa = psEmpresa.executeQuery();

                try {
                    if (rsEmpresa.next()) {
                        empresa = new Empresa();
                        empresa.setCnpj(rsEmpresa.getString("cnpj"));
                        empresa.setRazaoSocial(rsEmpresa.getString("razao_social"));

                        SessionUtils.getInstance().write(SessionUtils.EMPRESA, empresa);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                resultadoLogin = true;
            }

        } catch (SQLException e) {
            Mensagens msg = new Mensagens();
            msg.criarMensagem(null, "Problema na Conexão", "Não foi possível conectar com o banco de dados");
            e.printStackTrace();
        }

        GerenciadorConexoes.pegarInstancia()
                .fecharConexao(conexao);

        return resultadoLogin;
    }

    @Override
    public boolean salvar(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
