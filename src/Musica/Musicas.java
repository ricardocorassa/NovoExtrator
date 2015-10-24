/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musica;

import Acorde.Acorde;
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

    public static String[] acordes = {"C", "D", "E", "F", "G", "A", "B"};
    public static String[] subAcordes = {"", "m", "#", "b", "4", "5", "7", "9", "º"};
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

        }

        arff.writeArff("/home/ricardo/Área de Trabalho/New/Tom.arff");
    }

    public void toArffFrequencia() throws IOException {

        System.out.println("gerando arff de freq ...");

        ArquivoArff arff = new ArquivoArff("Frequencia");

        /*Cria os atributos do arquivo arff*/
        for (int l = 0; l < acordes.length; l++) {
            for (int k = 0; k < subAcordes.length; k++) {
                arff.addAtribute((acordes[l] + subAcordes[k]));
            }
        }
        System.out.println("atributos submetidos");

        /*Adiciona as classes ao arquivo*/
        for (String classe : classes) {
            arff.addClasse(classe);
        }
        System.out.println("classes submetidas");
        for (int i = 0; i < musicas.size(); i++) {
            Musica msc = musicas.get(i);
            String classe = msc.getClasse();
            
            ArrayList<Acorde> acordesFreq = msc.getAcordesFreq();
            ArrayList<String> dados = new ArrayList<>();
            String strAux = "";
            ArrayList<String> atr = arff.getAtributes();
            if (!acordesFreq.isEmpty()) {
                for (int j = 0; j < atr.size(); j++) {
                    for (int k = 0; k < acordesFreq.size(); k++) {
                        if (atr.get(j).equals(acordesFreq.get(k).getAcorde()) && (!atr.get(j).equals(""))) {
                            strAux = "" + acordesFreq.get(k).getCont();
                        } else {
                            strAux = "0";
                        }
                    }
                    dados.add(strAux);
                    strAux = "0";
                }
                dados.add("alegria");
            }else{
                for (int j = 0; j < atr.size(); j++) {
                    dados.add("0");
                }
                dados.add(classe);
            }
            arff.addDados(dados);
        }

        arff.writeArff("/home/ricardo/Área de Trabalho/New/frequencia.arff");
    }
    
    public void toArffPresenca() throws IOException {

        System.out.println("gerando arff de presença ...");

        ArquivoArff arff = new ArquivoArff("Frequencia");

        /*Cria os atributos do arquivo arff*/
        for (int l = 0; l < acordes.length; l++) {
            for (int k = 0; k < subAcordes.length; k++) {
                arff.addAtribute((acordes[l] + subAcordes[k]));
            }
        }
        System.out.println("atributos submetidos");

        /*Adiciona as classes ao arquivo*/
        for (String classe : classes) {
            arff.addClasse(classe);
        }
        System.out.println("classes submetidas");
        for (int i = 0; i < musicas.size(); i++) {
            Musica msc = musicas.get(i);
            String classe = msc.getClasse();
            
            ArrayList<Acorde> acordesFreq = msc.getAcordesFreq();
            ArrayList<String> dados = new ArrayList<>();
            String strAux = "";
            ArrayList<String> atr = arff.getAtributes();
            if (!acordesFreq.isEmpty()) {
                for (int j = 0; j < atr.size(); j++) {
                    for (int k = 0; k < acordesFreq.size(); k++) {
                        if (atr.get(j).equals(acordesFreq.get(k).getAcorde()) && (!atr.get(j).equals(""))) {
                            strAux = "1";
                        } else {
                            strAux = "0";
                        }
                    }
                    dados.add(strAux);
                    strAux = "0";
                }
                dados.add("alegria");
            }else{
                for (int j = 0; j < atr.size(); j++) {
                    dados.add("0");
                }
                dados.add(classe);
            }
            arff.addDados(dados);
        }

        arff.writeArff("/home/ricardo/Área de Trabalho/New/presenca.arff");
    }
}
