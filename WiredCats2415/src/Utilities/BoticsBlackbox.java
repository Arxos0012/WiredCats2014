/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.util.SimpleLinkedList;

/**
 *
 * @author jackwinski
 */
public class BoticsBlackbox {

    private SimpleLinkedList valueList;

    public BoticsBlackbox() {
        valueList = new SimpleLinkedList();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * This function adds a value to the Blackbox's list of values
     * @param input Value to be added
     */
    public void addVal(int input) {
        valueList.
    }

//    /**
//     * This writes all of the values in the LinkedList to a csv
//     * @param filename The name of the file to be written to
//     */
//    public void toCsv(String filename) {
//        try {
//            File valuefile = new File(filename);
//            if (!valuefile.exists()) {
//                valuefile.createNewFile();
//            }
//            FileWriter vWriter = new FileWriter(valuefile);
//            BufferedWriter valWriter = new BufferedWriter(vWriter);
//            String temp = "";
//            for(int i = 0;i < valueList.size() ; i++){
//                temp = temp + "," + valueList.;
//            }
//            valWriter.write(temp);
//            valWriter.flush();
//            valWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
