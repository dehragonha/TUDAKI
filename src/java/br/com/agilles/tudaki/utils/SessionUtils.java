package br.com.agilles.tudaki.utils;

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
 * @description SessionUtils - Classe responsável por realizar tarefas relacionados a sessão
 * 
 **/
public class SessionUtils {
    private static SessionUtils instance;
    public static String USUARIO    = "usuarioSession";
    public static String EMPRESA = "empresaSession";
    
    private SessionUtils(){}
    
    public static SessionUtils getInstance(){
        if(instance == null){
            instance = new SessionUtils();
        }
        return instance;
    }
    
    public void write(String chave, Object objeto){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(chave, objeto);
        
    }
    
    public Object read(String chave){
       return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
        
    }
    

}
