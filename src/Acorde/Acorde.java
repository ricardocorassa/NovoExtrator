/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acorde;

/**
 *
 * @author ricardo
 */
public class Acorde {
    private String acorde;
    private int cont;
    private double freq;

    public Acorde(String acorde) {
        this.acorde = acorde;
        this.cont = 0;
        this.freq = 0;
    }

    /**
     * @return the acorde
     */
    public String getAcorde() {
        return acorde;
    }

    /**
     * @param acorde the acorde to set
     */
    public void setAcorde(String acorde) {
        this.acorde = acorde;
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
    public double getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(double freq) {
        this.freq = freq;
    }
}
