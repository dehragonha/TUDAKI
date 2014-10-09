package br.com.agilles.tudaki.models.beans;

import br.com.agilles.tudaki.models.business.Usuario;
import br.com.agilles.tudaki.utils.Mensagens;
import br.com.agilles.tudaki.utils.SessionUtils;
import br.com.agilles.tudaki.dao.UsuarioDao;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description UsuarioBean - Objeto que fará o controle das usuários no sistema.
 * 
 **/
@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();
    private Usuario usuarioSessao = new Usuario();
    private UsuarioDao dao = new UsuarioDao();
    private String outcome;
    private Mensagens mensagem = new Mensagens();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String login() throws InterruptedException {
        outcome = "LOGIN_NOK";
        if (dao.login(usuario)) {
            SessionUtils.getInstance().write(SessionUtils.USUARIO, usuario);

            outcome = "LOGIN_OK";
        } else {
            usuario = new Usuario();
            mensagem.criarMensagem(FacesMessage.SEVERITY_ERROR, "Opa! Detectamos um erro!", "Você deve ter digitado seu nome ou sua senha de maneira incorreta");
            
        }
        return outcome;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Mensagens getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagens mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuarioSessao() {
        return (Usuario)SessionUtils.getInstance().read(SessionUtils.USUARIO);
    }

    public UsuarioDao getDao() {
        return dao;
    }

}
