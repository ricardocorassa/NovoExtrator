/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transicoes;

import Acorde.Acorde;

/**
 *
 * @author ricardo
 */
public class Transicao {

    private Acorde acorde;
    private Acorde prox;
    private int existe;
    private int cont;
    private int freq;

    public Transicao(String acorde, String prox) {
        //System.out.println(acorde + " - " + prox);
        this.acorde = new Acorde("");
        this.acorde.setAcorde(acorde);
        this.acorde.setCont(0);
        
        this.prox = new Acorde("");
        this.prox.setAcorde(prox);
        this.prox.setCont(0);
    }

    Transicao(String linha_split) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the acorde
     */
    public Acorde getAcorde() {
        return acorde;
    }

    /**
     * @param acorde the acorde to set
     */
    public void setAcorde(Acorde acorde) {
        this.acorde = acorde;
    }

    /**
     * @return the prox
     */
    public Acorde getProx() {
        return prox;
    }

    /**
     * @param prox the prox to set
     */
    public void setProx(Acorde prox) {
        this.prox = prox;
    }

    /**
     * @return the existe
     */
    public int getExiste() {
        return existe;
    }

    /**
     * @param existe the existe to set
     */
    public void setExiste(int existe) {
        this.existe = existe;
    }

    /**
     * @return the cont
     */
    public int getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * @return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }
}
