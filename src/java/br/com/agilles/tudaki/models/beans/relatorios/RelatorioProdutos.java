package br.com.agilles.tudaki.models.beans.relatorios;

import br.com.agilles.tudaki.models.business.Empresa;
import br.com.agilles.tudaki.utils.SessionUtils;

/**
 *
 * @author Agilles
 */
public class RelatorioProdutos {

    private String produto;
    private double valorUnitario;
    private int estoque;
    private String empresa;

    public String getEmpresa() {
        Empresa empresa = (Empresa) SessionUtils.getInstance().read(SessionUtils.EMPRESA);
        return this.empresa= empresa.getRazaoSocial();
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
