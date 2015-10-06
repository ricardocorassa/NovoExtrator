/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musica;

import ArquivoArff.ArquivoArff;
import ArquivoArff.Data;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class Musicas {
    
    private ArrayList<Musica> musicas;
    private ArrayList<String> atributes;
    
    public static char acordes[] = {'C', 'D', 'E', 'F', 'G', 'A', 'B'};
    public static char subAcordes[] = {' ', 'm', '#', 'b', '4', '5', '7', '9', '°'};
    public static String classes[] = {"alegria", "amor", "decepcao", "entusiasmo", "paixao", "tristeza"};
    
    public Musicas() {
        musicas = new ArrayList<Musica>();
    }

    /**
     * @return the musicas
     */
    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    /**
     * @param musicas the musicas to set
     */
    public void add(Musica musica) {
        this.musicas.add(musica);
    }
    
    public void geraArffTom() throws IOException {
        System.out.println("gerando arff de tom ...");
        
        ArquivoArff arff = new ArquivoArff("Tom - 2");

        /*Cria os atributos do arquivo arff*/
        for (int l = 0; l < acordes.length; l++) {
            for (int k = 0; k < subAcordes.length; k++) {
                arff.addAtribute("" + acordes[l] + subAcordes[k]);
            }
        }
        System.out.println("atributos submetidos");

        /*Adiciona as classes ao arquivo*/
        for (int i = 0; i < classes.length; i++) {
            arff.addClasse(classes[i]);
        }
        System.out.println("classes submetidas");
        
        for (int i = 1; i < musicas.size(); i++) {
            
            Musica msc = musicas.get(i);
            System.out.println(msc.getNome());
            
            Data dados = new Data();
            
            ArrayList<String> tom = new ArrayList<String>();
            
            tom.add(msc.getTom());
            
            dados.setClasse(msc.getClasse());
            
            //dados.setDados(tom);
            
            arff.addDados(dados);
        }
        
        arff.writeArff("/home/ricardo/Área de Trabalho/New/Tom.arff");
    }
    
    public void toArffFrequencia() throws IOException {
        
        System.out.println("gerando arff de tom ...");
        
        ArquivoArff arff = new ArquivoArff("Frequencia");

        /*Cria os atributos do arquivo arff*/
        for (int l = 0; l < acordes.length; l++) {
            for (int k = 0; k < subAcordes.length; k++) {
                arff.addAtribute("" + acordes[l] + subAcordes[k]);
            }
        }
        System.out.println("atributos submetidos");

        /*Adiciona as classes ao arquivo*/
        for (int i = 0; i < classes.length; i++) {
            arff.addClasse(classes[i]);
        }
        System.out.println("classes submetidas");
        
        for (int i = 1; i < musicas.size(); i++) {
            
            Musica msc = musicas.get(i);
            
            Data dados = new Data();
            dados.setDados(msc.getAcordes());
            dados.setClasse(msc.getClasse());
            
            arff.addDados(dados);
        }
        
        arff.writeArff("/home/ricardo/Área de Trabalho/New/Frequencia.arff");
    }
}
