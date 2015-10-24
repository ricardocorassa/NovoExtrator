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

        Musicas musicas = new Musicas();
        String[] pastas = {"alegria", "amor", "decepcao", "entusiasmo", "paixao", "tristeza"};

        for (int i = 0; i < pastas.length; i++) {
            String pasta = "/home/ricardo/Área de Trabalho/Cifras/" + pastas[i] + "/";
            File raiz = new File(pasta);
            File fList[] = raiz.listFiles();
            System.out.println("Musicas a serem analisadas : " + fList.length);
            for (int j = 0; j < fList.length; j++) {
                System.out.println(fList[j]);
                Musica musica = new Musica(fList[j]);
                musica.extraiAcordes();
                musica.geraFreq();
                musicas.add(musica);
            }

        }

        musicas.toArffFrequencia();
        musicas.toArffPresenca();

        /*for (int i = 0; i < mscs.size(); i++) {
         //System.out.println(musicas.get(i).getNome()+ " " + musicas.get(i).getTom());
         }*/
    }

}
