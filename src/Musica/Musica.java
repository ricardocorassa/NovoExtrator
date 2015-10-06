/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musica;

import Acorde.Acorde;
import ArquivoArff.ArquivoArff;
import Transicoes.Transicao;
import Transicoes.Transicoes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private Acorde Tom;
    private ArrayList<Acorde> acordes;

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

    public Musica(File arquivo) {
        /**
         * Inicializar todas as variaveis
         */
        this.arquivo = arquivo;

        this.classe = arquivo.getParent().replace("/home/ricardo/Área de Trabalho/Cifras/", "");

        this.nome = arquivo.getPath().replace(arquivo.getParent() + "/", "").replace(".txt", "");

        this.Tom = new Acorde("");

        this.acordes = new ArrayList<Acorde>();

        this.transicoes = new ArrayList<Transicao>();

        this.transicoes_musica = new ArrayList<Transicao>();

        /*Cria a base de acordes*/
        this.acordes_anlise_list = new ArrayList<String>();
        for (int i = 1; i < base_acordes.length - 1; i++) {
            acordes_anlise_list.add(base_acordes[i]);
        }

    }

    public void extraiCaracteristicas() throws FileNotFoundException, IOException {

        BufferedReader input = new BufferedReader(new FileReader(this.getArquivo()));
        String linha = input.readLine();

        while (linha != null) {
            if (eAcorde(linha)) {
                String linha_split[] = linha.split(" ");
                for (int i = 0; i < linha_split.length; i++) {
                    String pos = linha_split[i].replace(" ", "");
                    System.out.println(pos);
                    if (i < linha_split.length - 1) {
                        /*Adiciona as transiçoes da musica*/
                        transicoes_musica.add(new Transicao(pos, linha_split[i + 1].replace(" ", "")));
                    }
                    if (acordes_anlise_list.contains(pos)) {
                        if (!acordes.contains(pos)) {
                            Acorde acr = new Acorde(pos);
                            acr.setCont(1);
                            getAcordes().add(acr);
                        } else {
                            int cont = getAcordes().get(getAcordes().indexOf(pos)).getCont();
                            Acorde newAcorcde = new Acorde(getAcordes().get(getAcordes().indexOf(pos)).getAcorde());
                            newAcorcde.setCont(cont++);
                            getAcordes().set(getAcordes().indexOf(pos), newAcorcde);
                        }
                    }
                }
            }
            linha = input.readLine();
        }
        /**
         * Determina o tom como acorde que aparece o numero maior de vezes
         */
        int cont_total_acordes = 0;

        for (int i = 0; i < acordes.size(); i++) {
            cont_total_acordes += acordes.get(i).getCont();/*conta o total de acordes*/

            if (i == 0) {
                this.Tom = acordes.get(i);
            } else {
                if (acordes.get(i).getCont() > this.Tom.getCont()) {
                    this.Tom = acordes.get(i);//armazena o valor maio do tom;
                }
            }
        }
        if (this.Tom.getAcorde() == "") {
            System.out.println("Tom vazio");
        }
        
        /**
         * Adionas as frequencia as musicas
         */
        for (int i = 0; i < acordes.size(); i++) {
            System.out.println(nome + " " +acordes.get(i).getAcorde()+" "+acordes.get(i).getCont());
            if (cont_total_acordes != 0) {
                acordes.get(i).setFreq(acordes.get(i).getCont() / cont_total_acordes);
            }
        }

        /**
         * Cria as transiçoes de base
         */
        for (int i = 0; i < base_acordes.length - 1; i++) {
            for (int j = i; j < base_acordes.length; j++) {
                Transicoes transicoes = new Transicoes();
                this.transicoes = transicoes.getTransicoes(arquivo);
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
        return this.Tom.getAcorde();
    }

    /**
     * @param Tom the Tom to set
     */
    public void setTom(Acorde Tom) {
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
    public ArrayList<Acorde> getAcordes() {
        return acordes;
    }

    /**
     * @param acordes the acordes to set
     */
    public void setAcordes(ArrayList<Acorde> acordes) {
        this.acordes = acordes;
    }

}
