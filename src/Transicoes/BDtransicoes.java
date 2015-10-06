/*
 */
package Transicoes;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class BDtransicoes {

    void BDTransicos() {
    }
    public static char acordes[] = {'C', 'D', 'E', 'F', 'G', 'A', 'B'};
    public static char subAcordes[] = {'m', '#', 'b', '4', '5', '7', '9', '°', ' '};

    public ArrayList<String> BDTrans() {
        ArrayList<String> transicoes = new ArrayList<>();
        String transicao = null, acorde1, acorde2;
        for (int i = 0; i < acordes.length; i++) {
            for (int j = 0; j < subAcordes.length; j++) {
                acorde1 = (Character.toString(acordes[i]) + Character.toString(subAcordes[j]));
                for (int k = 0; k < acordes.length; k++) {
                    for (int l = 0; l < subAcordes.length; l++) {
                        acorde2 = (Character.toString(acordes[k]) + Character.toString(subAcordes[l]));
                        if (acorde1 == null ? acorde2 != null : !acorde1.equals(acorde2)) {
                            transicoes.add(acorde1 + "->" + acorde2);
                        }
                    }
                }
            }
        }
        return transicoes;
    }
}
