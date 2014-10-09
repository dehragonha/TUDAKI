package br.com.agilles.tudaki.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description Mensagens - Objeto para criar mensagens no sistema.
 * 
 **/
public class Mensagens {

    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage mensagem;

    public void criarMensagem(FacesMessage.Severity severity,String titulo, String texto) {
        mensagem = new FacesMessage(titulo, texto);
        context.addMessage(null, mensagem);
    }

}
