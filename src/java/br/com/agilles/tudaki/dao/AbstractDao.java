package br.com.agilles.tudaki.dao;

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
 * @description AbstractDao - Objeto Abstrato, que deverá ser implementado nas próximas classes.
 * 
 **/
public abstract class AbstractDao<T> {

    public abstract boolean salvar(T objeto);

    public abstract boolean remover(T objeto);

    public abstract boolean desativar(T objeto);

    public abstract boolean atualizar(T objeto);
    
    public abstract List<T> listarTodos();

}
