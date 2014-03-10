/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.AutonomousCommand.CommandAutonomous;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandCock;
import edu.wpi.first.wpilibj.templates.commands.CommandGroupShoot;
import edu.wpi.first.wpilibj.templates.commands.CommandIntake;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;
import edu.wpi.first.wpilibj.templates.commands.CommandOuttake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Wilson extends IterativeRobot {

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
//        autonomousCommand = new  whatever our command will be
        // Initialize all subsystems
        CommandBase.init();
        autonomousCommand = new CommandAutonomous();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
        CommandBase.resources.getFromFile("wiredCatsConfig.txt");
        if (CommandBase.drivesubsystem.getCurrentCommand() != null){
            ((CommandBase)CommandBase.drivesubsystem.getCurrentCommand()).updateValues();
        }
        if (CommandBase.ldisubsystem.getCurrentCommand() != null){
            ((CommandBase)CommandBase.drivesubsystem.getCurrentCommand()).updateValues();
        }
        if (CommandBase.launchersubsystem.getCurrentCommand() != null){
            ((CommandBase)CommandBase.drivesubsystem.getCurrentCommand()).updateValues();
        }
        
        System.out.println("[WiredCats] Updating Values.");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
//        if ( CommandBase.jsdriver.leftTrigger() && 
//                !(CommandBase.ldisubsystem.getCurrentCommand() instanceof CommandIntake)){
//           Scheduler.getInstance().add(new CommandIntake());
//        }
//        if ( CommandBase.jsdriver.rightTrigger() && 
//                !(CommandBase.ldisubsystem.getCurrentCommand() instanceof CommandLaunch) &&
//                !(CommandBase.ldisubsystem.getCurrentCommand() instanceof CommandOuttake) &&
//                (CommandBase.ldisubsystem.isExtended() )){
//           Scheduler.getInstance().add(new CommandLaunch());
//        }
        System.out.println(CommandBase.launchersubsystem.hitHESensor());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
