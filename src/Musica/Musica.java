/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musica;

import Acorde.Acorde;
import ArquivoArff.ArquivoArff;
import static Musica.Musicas.acordes;
import static Musica.Musicas.subAcordes;
import Transicoes.Transicao;
import Transicoes.Transicoes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ricardo
 *
 * Cria uma musica com as suas caracteristicas
 *
 */
public class Musica {

    private File arquivo;

    private String nome;

    private String classe;

    private String Tom;

    /**
     * Acordes da musica
     */
    private ArrayList<String> acordesDaMusica;

    /**
     * Vetor de transiçoes possiveis/
     */
    private ArrayList<Transicao> transicoes;

    /**
     * Lista de transiçoes possiveis na musica
     */
    private ArrayList<Transicao> transicoes_musica;

    /**
     * Acordes analisados na cifra
     */
    private ArrayList<String> acordes_anlise_list;

    /**
     * Acordes base para a analise
     */
    private static String[] base_acordes = {"DO", "RE", "MI", "FA", "SOL", "LA", "SI", "C", "D", "E", "F", "G", "A", "B", "m", "b", "#", "4", "5", "7", "9", "º", "6", "/",
        "(", ")", "13", "11", "sus", "maj", "dim", "+", "x |", "X2"};

    public static String basacordes[] = {"C", "D", "E", "F", "G", "A", "B"};
    public static String subAcordes[] = {"", " ", "m", "#", "b", "4", "5", "7", "9", "°"};

    private ArrayList<Acorde> acordesFreq;

    public Musica(File arquivo) {
        /**
         * Inicializar todas as variaveis
         */
        this.arquivo = arquivo;

        this.classe = arquivo.getParent().replace("/home/ricardo/Área de Trabalho/Cifras/", "");

        this.nome = arquivo.getPath().replace(arquivo.getParent() + "/", "").replace(".txt", "");

        this.Tom = "";

        this.acordesDaMusica = new ArrayList<String>();

        this.transicoes = new ArrayList<Transicao>();

        this.transicoes_musica = new ArrayList<Transicao>();

        this.acordesFreq = new ArrayList<Acorde>();

        /*Cria a base de acordes*/
        this.acordes_anlise_list = new ArrayList<String>();
        for (int l = 0; l < basacordes.length; l++) {
            for (int k = 0; k < subAcordes.length; k++) {
                acordes_anlise_list.add(basacordes[l] + subAcordes[k]);
            }
        }

    }

    public void extraiAcordes() throws FileNotFoundException, IOException {

        BufferedReader input = new BufferedReader(new FileReader(this.getArquivo()));
        String linha = input.readLine();
        //System.out.println(this.nome);
        String linas_acordes = "";
        while (linha != null) {
            if (eAcorde(linha)) {
                linas_acordes += linha;
            }
            linha = input.readLine();
        }

        String linha_split[] = linas_acordes.split(" ");
        for (int i = 0; i < linha_split.length; i++) {
            String pos = linha_split[i].replaceAll(" ", "");
            //System.out.println("["+pos+"]");
            if (acordes_anlise_list.contains(pos)) {
                acordesDaMusica.add(pos);
            }
        }
    }

    public void geraFreq() {

        int cont = 1;

        for (int i = 0; i < acordesDaMusica.size(); i++) {
            for (int k = 0; k < acordesDaMusica.size(); k++) {
                if (acordesDaMusica.get(k).equals(acordesDaMusica.get(i))) {
                    cont++;
                    //acordesDaMusica.remove(k);
                }
            }
            Acorde acorde = new Acorde(acordesDaMusica.get(i), cont);
            getAcordesFreq().add(acorde);
            cont = 1;
        }

//        for (int i = 0; i < getAcordesFreq().size(); i++) {
//            System.out.println("\t" + getAcordesFreq().get(i).getAcorde() + " " + getAcordesFreq().get(i).getCont());
//        }
    }
    
    public void geraTom(){
        int cont = 0;
        for (int i = 0; i < acordesFreq.size(); i++) {
            if(acordesFreq.get(i).getCont() > cont){
                this.Tom = acordesFreq.get(i).getAcorde();
            }            
        }
    }

    public static boolean eAcorde(String linha) {
        linha = linha.replace("\n", "");
        linha = linha.replace("\t", "");
        linha = linha.replace("\r", "");
        linha = linha.replace(" ", "");
        if (linha.equals("")) {
            return false;
        }
        for (int i = 0; i < base_acordes.length; i++) {
            linha = linha.replace(base_acordes[i], "");
        }
        if (linha.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the classe
     */
    public String getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the arquivo
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * @return the Tom
     */
    public String getTom() {
        return this.Tom;
    }

    /**
     * @param Tom the Tom to set
     */
    public void setTom(String Tom) {
        this.Tom = Tom;
    }

    /**
     * @return the transicoes_musica
     */
    public ArrayList<Transicao> getTransicoes_musica() {
        return transicoes_musica;
    }

    /**
     * @param transicoes_musica the transicoes_musica to set
     */
    public void setTransicoes_musica(ArrayList<Transicao> transicoes_musica) {
        this.transicoes_musica = transicoes_musica;
    }

    /**
     * @return the acordes
     */
    public ArrayList<String> getAcordes() {
        return acordesDaMusica;
    }

    /**
     * @param acordes the acordes to set
     */
    public void setAcordes(ArrayList<String> acordes) {
        this.acordesDaMusica = acordes;
    }

    /**
     * @return the acordesFreq
     */
    public ArrayList<Acorde> getAcordesFreq() {
        return acordesFreq;
    }

    /**
     * @param acordesFreq the acordesFreq to set
     */
    public void setAcordesFreq(ArrayList<Acorde> acordesFreq) {
        this.acordesFreq = acordesFreq;
    }

}
