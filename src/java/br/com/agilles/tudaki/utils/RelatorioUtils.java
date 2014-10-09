package br.com.agilles.tudaki.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Agilles
 */
public class RelatorioUtils {

    //executar relatório através de um jrds
    public static void executarRelatorio(String caminhoRelatorio, Map<String, Object> parametros, String nomeRelatorio, JRBeanCollectionDataSource fonteDados) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        //Pegar caminho do arquivo .jasper
        InputStream reportStream = context.getExternalContext().getResourceAsStream(caminhoRelatorio);
        response.setContentType("application/pdf");

        response.setHeader("Pragma", "no-cache");

        try {
            ServletOutputStream servletOutputStream = null;
            try {
                servletOutputStream = response.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (parametros == null) {
                parametros = new HashMap<String, Object>();
            }
            JasperRunManager.runReportToPdfStream(reportStream, response.getOutputStream(), parametros, fonteDados);
            
            try {
                servletOutputStream.flush();
                servletOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }

    }

}
