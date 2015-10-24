/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Musica.Musica;
import Musica.Musicas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class NovoExtrator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        File raiz = new File("/home/ricardo/Área de Trabalho/Cifras/alegria/");
        File fList[] = raiz.listFiles();
        
        Musicas musicas = new Musicas();
        
        System.out.println("Musicas a serem analisadas : " + fList.length);
        
        for (int i = 0; i < fList.length; i++) {
            Musica musica = new Musica(fList[i]);
            musica.extraiAcordes();
            musica.geraFreq();
            musicas.add(musica);
        }

        musicas.toArffFrequencia();

        /*for (int i = 0; i < mscs.size(); i++) {
         //System.out.println(musicas.get(i).getNome()+ " " + musicas.get(i).getTom());
         }*/
    }

}
