/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivoArff;

import Acorde.Acorde;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class ArquivoArff {

    private String relations;
    private ArrayList<String> atributes;
    private ArrayList<String> classe;
    private ArrayList<Data> dataList;

    public ArquivoArff(String relations) {
        this.relations = relations;
        classe = new ArrayList<String>();
        atributes = new ArrayList<String>();
        dataList = new ArrayList<Data>();
    }

    /*
     * Escreve o arquivo
     */
    public void writeArff(String caminho) throws IOException {

        if (this.relations == "" || this.atributes.isEmpty() || this.classe.isEmpty() || this.dataList.isEmpty()) {
            System.out.println("Arquivo incompleto");
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));

            //escreve o relations
            writer.write("@RELATION\t" + relations + "\n");
            System.out.println(relations);
            
            //escreve os atributos
            for (int i = 0; i < atributes.size(); i++) {
                System.out.println(atributes.get(i));
                writer.write("@ATTRIBUTE\t" + atributes.get(i) + "\tNUMERIC\n");
            }

            //escreve a linha class
            writer.write("@ATTRIBUTE CLASS {");
            for (long i = 0; i < classe.size(); i++) {
                if (i == classe.size() - 1) {
                    writer.write(classe.get((int) i) + "}\n");
                } else {
                    writer.write(classe.get((int) i) + ",");
                }
            }

            //escreve os dados
            writer.write("@DATA\n");
            for (int i = 0; i < dataList.size(); i++) {
                ArrayList<Acorde> dados = dataList.get(i).getDados();
                for (int j = 0; j < dados.size() - 1; j++) {
                    //se for o primeiro escrever somente
                    if (j == 0) {
                        writer.write(dados.get(j).getAcorde());
                    } else { //para os demais, escreve uma virgula antes
                        writer.write("," + dados.get(j));
                    }
                }
                //escrever no final da linha a classe que ele pertence
                writer.write("," + dataList.get(i).getClasse() + "\n");
            }
        }
    }

    /*
     * Ler um arquivo e transformar em arff
     */
    public void readArff(String caminho) throws FileNotFoundException, IOException {
        BufferedReader arquivo = new BufferedReader(new FileReader(caminho));

        String linha = arquivo.readLine();

        while (linha != null) {

            if (linha.contains("@RELATION")) {
                relations = linha.replace("@RELATION \t", "");

            } else if (linha.contains("@ATTRIBUTE") && linha.contains("NUMERIC")) {
                linha = linha.replace("@ATTRIBUTE \t", "");
                linha = linha.replace("\tNUMERIC", "");
                addAtribute(linha);

            } else if (linha.contains("@ATTRIBUTE 	Class")) {
                String[] aClass = linha.replace("@ATTRIBUTE\tClass\t{", "").replace("}", "").replace(" ", "").split(",");
                for (int j = 0; j < aClass.length; j++) {
                    addClasse(aClass[j]);
                }
            } else if (linha.contains("@Data")) {

                linha = arquivo.readLine();

                while (linha != null) {

                    Data data = new Data(); /*cria um valor para armazenar os dados e 
                     *a classe final, assim faz uma matris de array por array*/

                    ArrayList<String> data_val = new ArrayList<>();

                    String[] aDados = linha.split(",");

                    for (int k = 0; k < aDados.length; k++) {
                        if (classe.contains(aDados[k])) {
                            data.setClasse(aDados[k]);
                        } else {
                            data_val.add(aDados[k]);
                        }
                    }
                    //data.setDados(data_val);
                    data_val = null;

                    dataList.add(data);

                    linha = arquivo.readLine();
                }
            }
            linha = arquivo.readLine();
        }
    }

    /*
     *adicionar atributos para o arquivo Arff
     */
    public void addAtribute(String atribute) {
        this.atributes.add(atribute);
    }

    /*
     * adidionar os dados do arquivo Arff
     */
    public void addDados(Data data) {
        //System.out.println(dado);
        this.dataList.add(data);
    }

    public void addLineDados(String dado) {

    }

    /*
     * Adicionar atributos do class
     */
    public void addClasse(String aClass) {
        this.classe.add(aClass);
    }

    /**
     * @return the relations
     */
    public String getRelations() {
        return relations;
    }

    /**
     * @param relations the relations to set
     */
    public void setRelations(String relations) {
        this.relations = relations;
    }

    /**
     * @return the atributes
     */
    public ArrayList<String> getAtributes() {
        return atributes;
    }

    /**
     * @param atributes the atributes to set
     */
    public void setAtributes(ArrayList<String> atributes) {
        this.atributes = atributes;
    }

    /**
     * @return the atribute_class
     */
    public ArrayList<String> getClasse() {
        return classe;
    }

    /**
     * @param atribute_class the atribute_class to set
     */
    public void setClasse(ArrayList<String> atribute_class) {
        this.classe = atribute_class;
    }

    /**
     * @return the dados
     */
    public ArrayList<Data> getDados() {
        return dataList;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(ArrayList<Data> data) {
        this.dataList = data;
    }

}
