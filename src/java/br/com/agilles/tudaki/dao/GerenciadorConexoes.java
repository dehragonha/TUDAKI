package br.com.agilles.tudaki.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TUDAKI - SISTEMA DE GERENCIAMENTO PEDIDOS MOBILE (C) - Todos os direitos reservados 
 * 
 *  
 * @author      Jilles Ragonha
 * @category    Web
 * @copyright   Agilles
 * @updated     06/06/2014
 * 
 * @description GerenciadorConexoes - Objeto que deverá carregar o driver sql no sistema
 * preparado para abrir conexoes e encerrar as conexoes
 * 
 **/
public class GerenciadorConexoes {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(GerenciadorConexoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GerenciadorConexoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GerenciadorConexoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static GerenciadorConexoes singleton;
    private boolean propriedadesCarregadas;
    private Properties dbProp;

    /**
     * Construtor da classe
     */
    public GerenciadorConexoes() {
        carregarPropriedades();
    }

    /**
     * Método para carregar o arquivo de propriedades
     */
    private void carregarPropriedades() {
        if (dbProp == null) {
            dbProp = new Properties();
            try {
                dbProp.load(GerenciadorConexoes.class.getResourceAsStream("db.properties"));
                propriedadesCarregadas = true;

            } catch (IOException e) {
                Logger.getLogger("Gerenciador Conexoes").log(Level.SEVERE, "Problemas ao carregar as propriedades do banco de dados");
                Logger.getLogger("Gerenciador Conexoes").log(Level.SEVERE, "Mensagem de exceção: ", e.getMessage());
            }
        }
    }

    static GerenciadorConexoes pegarInstancia() {
        if (singleton == null) {
            singleton = new GerenciadorConexoes();
        }
        return singleton;
    }

    /**
     * Método para abrir uma conexão com o banco de dados
     */
    Connection abrirConexao() throws SQLException {

   
        return DriverManager.getConnection(dbProp.getProperty("URL"),
                dbProp.getProperty("USER"), dbProp.getProperty("PASS"));
        

    }

    /**
     * Método que encerra uma conexão com o banco
     *
     * @param conexao que deverá ser encerrada
     */
    void fecharConexao(Connection conexao) {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger("GerenciadorConexoes").log(Level.SEVERE, "Problema ao encerrar a conexao");
            Logger.getLogger("GerenciadorConexoes").log(Level.SEVERE, "Mensagem da exceção:", ex.getMessage());
        } finally {
            conexao = null;
        }
    }

    /**
     * Método que pega valores do arquivo de Propriedades
     * @param chave identificador a ser usado
     */
    String pegarPropriedade(String chave) {
        if (!propriedadesCarregadas) {
            carregarPropriedades();
        }
        return dbProp.getProperty(chave);
    }

}
