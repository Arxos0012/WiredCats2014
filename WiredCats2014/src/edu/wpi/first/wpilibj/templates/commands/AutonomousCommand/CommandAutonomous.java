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
import edu.wpi.first.wpilibj.templates.commands.CommandLastMinuteShit;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeDelay;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;
import edu.wpi.first.wpilibj.templates.commands.CommandRetractHood;

/**
 *
 * @author WiredCats
 */
public class CommandAutonomous extends CommandGroup{
    
    
    public CommandAutonomous(String fileName){
        
        SimpleLinkedList commands = new SimpleLinkedList();
        float[] vals;
        
         try {
            GoodScanner sc = new GoodScanner(fileName);
            String tempString;
            while (sc.hasNext()) {
                tempString = sc.next();
                Class c = Class.forName(tempString);
                commands.addFirst((CommandBase)c.newInstance());
                vals = new float[((CommandBase)commands.getFirst()).autoParameters()];
                for (int i = 0; i < vals.length; i++){
                    vals[i] = Float.parseFloat(sc.next());
                }
                ((CommandBase)commands.getFirst()).autoInit(vals);
            }
            sc.close();
            
            while (commands.size() > 0){
                addSequential((CommandBase)commands.getFirst());
                commands.removeFirst();
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();  
            System.out.println("[WiredCats] Could not read Autonomous file. Mission Failed!!!");
//            addSequential(new AutonomousIntake(1.5));
//            addSequential(new CommandIntakeDelay());
//            addSequential(new CommandExtendHood());
//            addSequential(new AutonomousWait(0.50f));
//            addSequential(new CommandRetractHood());
//            addSequential(new CommandLaunch());
//            addSequential(new CommandCock());
//            addSequential(new AutonomousStraightDrive(0.50f, true));
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
        addSequential(new CommandIntakeDelay());
        addSequential(new CommandExtendHood());
        addSequential(new AutonomousWait(0.50f));
        addSequential(new CommandRetractHood());
        addSequential(new CommandLaunch());
        addSequential(new CommandCock());
        addSequential(new AutonomousStraightDrive(0.50f, true));
    }
    
}
