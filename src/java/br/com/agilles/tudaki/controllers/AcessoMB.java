package br.com.agilles.tudaki.controllers;

import br.com.agilles.tudaki.models.business.Usuario;
import br.com.agilles.tudaki.utils.SessionUtils;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Agilles
 */
@ManagedBean
@RequestScoped
public class AcessoMB implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = pe.getFacesContext();
        String paginaAtual = context.getViewRoot().getViewId();
        // Se a página for 'index.xhtml' seta a variável como true
        boolean isLoginPage = paginaAtual.lastIndexOf("index.xhtml") > -1;
        // Retorna o usuário gravado na sessão
        Usuario usuario = (Usuario) SessionUtils.getInstance().read(SessionUtils.USUARIO);
        // Verifica se a página em que ele está é diferente da index e se existe algum guarda na sessão.
        if (!isLoginPage && usuario == null) {
            //redireciona o fluxo para a pagina de login
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, "null", "LOGIN_NOK");

        } else if (isLoginPage && usuario == null) {
            // Se o usuário logado tentar acessar a página de Login, ele é redirecionado para Home
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "LOGIN_OK");

        }

    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
