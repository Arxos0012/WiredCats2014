/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import Utilities.GoodScanner;
import com.sun.squawk.util.SimpleLinkedList;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandCock;
import edu.wpi.first.wpilibj.templates.commands.CommandExtendHood;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeAlpha;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeBeta;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;
import edu.wpi.first.wpilibj.templates.commands.CommandRetractHood;
import java.util.Vector;

/**
 *
 * @author WiredCats
 */
public class CommandAutonomous extends CommandGroup{
    
    
    public CommandAutonomous(String fileName){
        
        Vector commands = new Vector();
        Vector type = new Vector();
        float[] vals;
        
         try {
            GoodScanner sc = new GoodScanner(fileName);
            String tempString;
            while (sc.hasNext()) {
                tempString = sc.next();
                System.out.println(tempString);
                CommandBase command;
                
                if (tempString.equals("AutonomousIntake")){
                   command = new AutonomousIntake();
                } else if (tempString.equals("CommandIntakeBeta")){
                   command = new CommandIntakeBeta();
                } else if (tempString.equals("CommandExtendHood")){
                   command = new CommandExtendHood();
                } else if (tempString.equals("CommandRetractHood")){
                   command = new CommandRetractHood();
                } else if (tempString.equals("AutonomousWait")){
                   command = new AutonomousWait();
                } else if (tempString.equals("CommandLaunch")){
                   command = new CommandLaunch();
                } else if (tempString.equals("CommandCock")){
                   command = new CommandCock();
                } else if (tempString.equals("AutonomousStraightDrive")){
                   command = new AutonomousStraightDrive();
                } else if (tempString.equals("AutonomousTimedDrive")){
                   command = new AutonomousTimedDrive();  
                } else if (tempString.equals("AutonomousOuttake")){
                   command = new AutonomousOuttake();
                } else if (tempString.equals("AutonomousLaunch")){
                   command = new AutonomousLaunch();
                } else {
                   System.out.println(tempString +" is not a valid command name.");
                   throw new Exception();
                } 
                commands.insertElementAt(command,0);
                System.out.println("param array length: " + ((CommandBase)commands.elementAt(0)).autoParameters());
                if (((CommandBase)commands.elementAt(0)).autoParameters()>0){
                vals = new float[((CommandBase)commands.firstElement()).autoParameters()];
                 for (int i = 0; i < vals.length; i++){
                    vals[i] = Float.parseFloat(sc.next());
                    System.out.print(", "+vals[i]);
                 }
                 System.out.println();
                  ((CommandBase)commands.firstElement()).autoInit(vals);
                }
                type.insertElementAt(sc.next(), 0);
                System.out.println("type: " + type.elementAt(0));
            }
            sc.close();
            
            while (commands.size() > 0){
                if (((String)type.lastElement()).indexOf('p') != -1){
                    addParallel((CommandBase)commands.lastElement());
                } else {
                    addSequential((CommandBase)commands.lastElement());
                }
                commands.removeElementAt(commands.size()-1);
                type.removeElementAt(type.size()-1);
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();  
            System.out.println("[WiredCats] Could not read Autonomous file. Mission Failed!!!");
        }
    }
    
    public CommandAutonomous(){
//        addSequential(new AutonomousIntake(1.5));
//        
//        addSequential(new CommandLaunch());
//        addSequential(new CommandCock());
//        addSequential(new AutonomousIntake(1));
//        addSequential(new CommandLaunch());
//        addSequential(new CommandCock());
//        addSequential(new AutonomousStraightDrive(0.75f));
        
       //****SINGLE BALL*****
        
        addSequential(new AutonomousIntake(1.5));
        addSequential(new CommandIntakeBeta());     
        addSequential(new AutonomousStraightDrive(0.50f, true));
        addSequential(new CommandExtendHood());
        addSequential(new AutonomousWait(0.50f));
        addSequential(new CommandRetractHood());
        addSequential(new CommandLaunch());
        addSequential(new CommandCock());
    }
    
}
