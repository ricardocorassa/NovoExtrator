/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transicoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class Transicoes {

    private static String naoacordes[] = {"!", "$", "%", "&", "'", "*", "+", ",", ".", "0", "1",
        "2", "3", "6", "8", ":", ";", "<", "=", ">", "?", "@", "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "]", "^", "_", "`", "a",
        "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "n", "o", "p", "q", "r", "s", "t", "u",
        "v", "w", "x", "y", "z", "{", "|", "}", "~", "", "€", "?", "‚", "ƒ", "„", "…", "†", "‡",
        "ˆ", "‰", "Š", "‹", "Œ", "?", "Ž", "?", "?", "‘", "’", "“", "”", "•", "–", "—", "˜", "™",
        "š", "›", "œ", "?", "ž", "Ÿ", " ", "¡", "¢", "£", "¤", "¥"};
    public static String acordes[] = {"DO", "RE", "MI", "FA", "SOL", "LA", "SI", "C", "D", "E", "F", "G", "A", "B", "m", "b", "#", "4", "5", "7", "9", "º", "6", "/",
        "(", ")", "13", "11", "sus", "maj", "dim", "+", "x |", "X2"};

    public static boolean eAcorde(String linha) {
        String linha2 = linha;
        linha2 = linha2.replace("\n", "");
        linha2 = linha2.replace("\t", "");
        linha2 = linha2.replace("\r", "");
        linha2 = linha2.replace(" ", "");
        if (linha2.equals("")) {
            return false;
        }
        for (int i = 0; i < acordes.length; i++) {
            linha2 = linha2.replace(acordes[i], "");
        }
        if (linha2.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Transicao> getTransicoes(File file) throws FileNotFoundException, IOException {
        ArrayList<Transicao> transicoes = new ArrayList<>();
        String[] acorde;
        String acorde1 = "";

        BufferedReader input = new BufferedReader(new FileReader(file));
        String linha = input.readLine();

        while (linha != null) {
            
            if(eAcorde(linha))
                acorde1+= linha;

            linha = input.readLine();
        }
        
        acorde1.replace("\n", " ");
        acorde1.replace("\t", " ");
        acorde = acorde1.split(" ");
        for (int i = 0; i < acorde.length - 1; i++) {
            if (!acorde[i].equals(" ") && !acorde[i].equals(null) && !acorde[i].equals("")) {
                Transicao transicao = new Transicao(acorde[i], acorde[i + 1]);
            }
        }
        return transicoes;
    }
}
