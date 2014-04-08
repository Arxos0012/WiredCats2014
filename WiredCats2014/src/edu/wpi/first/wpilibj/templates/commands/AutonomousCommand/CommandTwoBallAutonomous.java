/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandCock;
import edu.wpi.first.wpilibj.templates.commands.CommandExtendHood;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeAlpha;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeBeta;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;
import edu.wpi.first.wpilibj.templates.commands.CommandRetractHood;


/**
 *
 * @author WiredCats
 */
public class CommandTwoBallAutonomous extends CommandGroup{
    
    public CommandTwoBallAutonomous(){
        
        addParallel(new AutonomousIntake(0.4));   
        addParallel(new AutonomousStraightDrive(0.50f, true));
        addSequential(new AutonomousWait(0.01f));
        addSequential(new CommandIntakeBeta()); 
        addSequential(new CommandLaunch());
        addParallel(new AutonomousStraightDrive(0.60f,false));
        addParallel(new CommandCock());
        addParallel(new AutonomousIntake(1.25));
        addSequential(new AutonomousWait(0.01f));
        addSequential(new CommandIntakeBeta());
        addSequential(new AutonomousStraightDrive(0.50f, true));
        addSequential(new CommandLaunch());
        addSequential(new CommandCock());
        
//        addSequential(new AutonomousIntake(1.25));
//        addSequential(new CommandIntakeDelay());
//        addSequential(new CommandExtendHood());
//        addSequential(new AutonomousWait(0.50f));
//        addSequential(new CommandRetractHood());
//        addSequential(new CommandLaunch());
//        addSequential(new CommandCock());
//        addSequential(new AutonomousStraightDrive(0.45f, false));
//        addSequential(new AutonomousIntake(1.25));
//        addSequential(new CommandIntakeDelay());
//        addSequential(new AutonomousStraightDrive(0.75f, true));
//        addSequential(new AutonomousWait(0.5f));
//        addSequential(new CommandExtendHood());
//        addSequential(new AutonomousWait(0.50f));
//        addSequential(new CommandRetractHood());
//        addSequential(new CommandLaunch());
//        addSequential(new CommandCock());
//        addSequential(new AutonomousStraightDrive(0.50f, true));

    }
}
