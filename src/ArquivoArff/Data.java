/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivoArff;

import Acorde.Acorde;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class Data {

    private String classe;
    private ArrayList<Acorde> dados;

    void Data() {
        classe = "";
        dados = new ArrayList<>();
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
     * @return the dados
     */
    public ArrayList<Acorde> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(ArrayList<Acorde> dados) {
        this.dados = dados;
    }

}
