/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.CommandCock;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;

/**
 *
 * @author WiredCats
 */
public class CommandAutonomous extends CommandGroup{
    
    public CommandAutonomous(){
        addSequential(new AutonomousSetArm());
        addSequential(new CommandLaunch());
        addSequential(new CommandCock());
        addSequential(new AutonomousIntake(3));
        addSequential(new CommandLaunch());
        addSequential(new CommandCock());
        addSequential(new AutonomousStraightDrive(2));
    }
    
}
