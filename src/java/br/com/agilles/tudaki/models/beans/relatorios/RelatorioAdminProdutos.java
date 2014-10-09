package br.com.agilles.tudaki.models.beans.relatorios;

/**
 *
 * @author Agilles
 */
public class RelatorioAdminProdutos {

    private String produto;
    private double valorUnit;
    private int estoque;
    private String empresa;
    private String categoria;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnitario) {
        this.valorUnit = valorUnitario;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
