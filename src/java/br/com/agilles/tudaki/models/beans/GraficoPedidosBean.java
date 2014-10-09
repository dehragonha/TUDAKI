package br.com.agilles.tudaki.models.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Agilles
 */
@ManagedBean
@RequestScoped
public class GraficoPedidosBean {

    private CartesianChartModel model;
    
    @PostConstruct
    public void preRender(){
        this.model = new CartesianChartModel();
        
        adicionarSerie("Todos os pedidos");
    }
    
    private void adicionarSerie(String rotulo){
        ChartSeries series = new ChartSeries(rotulo);
        
        series.set("1", Math.random()*1000);
        series.set("2", Math.random()*1000);
        series.set("3", Math.random()*1000);
        series.set("4", Math.random()*1000);
        series.set("5", Math.random()*1000);
        
        
        
        this.model.addSeries(series);
        
    }

    public CartesianChartModel getModel() {
        return model;
    }

    
}
