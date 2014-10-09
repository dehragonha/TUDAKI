package br.com.agilles.tudaki.models.beans;

import br.com.agilles.tudaki.models.business.Categoria;
import br.com.agilles.tudaki.models.business.Empresa;
import br.com.agilles.tudaki.models.business.Produto;
import br.com.agilles.tudaki.models.beans.relatorios.RelatorioProdutos;
import br.com.agilles.tudaki.utils.Mensagens;
import br.com.agilles.tudaki.utils.RelatorioUtils;
import br.com.agilles.tudaki.utils.SessionUtils;
import br.com.agilles.tudaki.dao.CategoriaDao;
import br.com.agilles.tudaki.dao.ProdutoDao;
import br.com.agilles.tudaki.models.beans.relatorios.RelatorioAdminProdutos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * *
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos
 * reservados
 *
 *
 * @author Jilles Ragonha
 * @category Web
 * @copyright Agilles
 * @updated 06/06/2014
 *
 * @description ProdutoBean - Objeto que fará o controle dos produtos no
 * sistema.
 *
 *
 *
 */
@ManagedBean
@RequestScoped
public class ProdutoBean implements Serializable {

    private Produto produto = new Produto();
    private Produto produtoSelecionado = new Produto();
    private RelatorioProdutos relProdutos = new RelatorioProdutos();
    private List<RelatorioProdutos> listaRelatorioProdutos = new ArrayList<RelatorioProdutos>();
    private List<Produto> produtos = new ArrayList<Produto>();
    private ProdutoDao dao = new ProdutoDao();
    private Categoria categoriaSelecionada = new Categoria();
    private Mensagens mensagem = new Mensagens();
    private List<Categoria> categorias;
    private CategoriaDao catDao = new CategoriaDao();
    private Long novaId = null;
    private Empresa empresa;

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();

        }
        return (Empresa) SessionUtils.getInstance().read(SessionUtils.EMPRESA);
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Produto> getProdutos() {
        return dao.listarTodos();
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Long getNovaId() {
        novaId = dao.pegarId();
        return novaId;
    }

    public void setNovaId(Long novaId) {
        this.novaId = novaId;
    }

    public CategoriaDao getCatDao() {
        return catDao;
    }

    public void setCatDao(CategoriaDao catDao) {
        this.catDao = catDao;
    }

    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<Categoria> getCategorias() {
        return catDao.listarTodos();
    }

    public ProdutoDao getDao() {
        return dao;
    }

    public void setDao(ProdutoDao dao) {
        this.dao = dao;
    }

    public Mensagens getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagens mensagem) {
        this.mensagem = mensagem;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void gravarProduto() {
        if (dao.salvar(produto)) {
            mensagem.criarMensagem(null, "Funcionou", "Você conseguiu cadastrar com sucesso um produto no banco");
            produto = new Produto();

        } else {
            mensagem.criarMensagem(FacesMessage.SEVERITY_ERROR, "iiihhh cara", "nao funcionou nao");
            produto = null;
        }

    }

    public boolean alterarProduto() {
        return dao.atualizar(produtoSelecionado);

    }

    public void cancelarEdicao() {

    }

    public void limpar() {
        produto = null;
    }

    public void executarRelatorio() throws IOException {
        listaRelatorioProdutos = dao.listarProdutosParaRelatorio();
        JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(listaRelatorioProdutos);
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sContext = (ServletContext) context.getExternalContext().getContext();
        String pasta = sContext.getRealPath("/resources/imagens");
        String imagem = pasta + "/cherry.jpg";
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("logo", imagem);

        RelatorioUtils.executarRelatorio("/resources/relatorios/Produtos.jasper", parametros, "Lista de Produtos", fonteDados);

    }

    public void executarRelatorioAdministrador() throws IOException {
        List<RelatorioAdminProdutos> produtos = dao.listarProdutosAdministradorParaRelatorio();
        JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(produtos);
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sContext = (ServletContext) context.getExternalContext().getContext();
        String pasta = sContext.getRealPath("/resources/imagens");
        String imagem = pasta + "/cherry.jpg";
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("logo", imagem);

        RelatorioUtils.executarRelatorio("/resources/relatorios/RelatorioAdministradorProdutos.jasper", parametros, "Administrador - Produtos", fonteDados);

    }

}
