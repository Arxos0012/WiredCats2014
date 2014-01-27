/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.sun.squawk.io.BufferedWriter;
import java.util.Vector;

/**
 *
 * @author jackwinski
 */
public class WiredVector {

    private Vector valueList;

    public WiredVector() {
        valueList = new Vector();
    }
    
    /**
     * This function adds a value to the Blackbox's list of values
     * @param input Value to be added
     */
    public void addVal(int input) {
        valueList.addElement(new OurInteger(input));
    }
    
    public int getVal(int index){
        return((OurInteger)valueList.elementAt(index)).i;
    }
    
    public int lastElement(){
        return ((OurInteger)valueList.lastElement()).i;
    }
    
    public int size(){
        return valueList.size();
    }
    
    private class OurInteger {
        public int i;
        
        public OurInteger(int i){
            this.i = i;
        }
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
