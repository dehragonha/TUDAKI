
package br.com.agilles.tudaki.models.beans;

import br.com.agilles.tudaki.models.business.Categoria;
import br.com.agilles.tudaki.dao.CategoriaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * @description CategoriaBean - Objeto que fará o controle das categorias no sistema.
 * 
 **/
@ManagedBean
@RequestScoped
public class CategoriaBean implements Serializable {

    private Categoria categoria;
    private List<Categoria> listaCategorias = new ArrayList<>();
     private CategoriaDao dao = new CategoriaDao();

    public List<Categoria> carregarCategorias() {
        listaCategorias = dao.listarTodos();
        return listaCategorias;

    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public CategoriaDao getDao() {
        return dao;
    }

    public void setDao(CategoriaDao dao) {
        this.dao = dao;
    }

}
