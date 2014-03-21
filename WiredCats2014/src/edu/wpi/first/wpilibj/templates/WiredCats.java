/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import Utilities.GoodScanner;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.AutonomousCommand.CommandAutonomous;
import edu.wpi.first.wpilibj.templates.commands.AutonomousCommand.CommandTwoBallAutonomous;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandCock;
import edu.wpi.first.wpilibj.templates.commands.CommandGroupShoot;
import edu.wpi.first.wpilibj.templates.commands.CommandLastMinuteShit;
import edu.wpi.first.wpilibj.templates.commands.CommandLaunch;
import edu.wpi.first.wpilibj.templates.commands.CommandOuttake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class WiredCats extends IterativeRobot {

    Command autonomousCommand;
    
    Relay compressor_relay;
    DigitalInput pressure_switch;
    
//    Talon t;
//    DigitalInput pressureSwitch = new DigitalInput(RobotMap.COMPRESSOR_PRESSURE_SWITCH);
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
//        autonomousCommand = new  whatever our command will be
        // Initialize all subsystems
        CommandBase.init();
        compressor_relay = new Relay(RobotMap.COMPRESSOR_RELAY_CHANNEL);
        pressure_switch = new DigitalInput(RobotMap.COMPRESSOR_PRESSURE_SWITCH);
        autonomousCommand = new CommandAutonomous();
        
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
        compressor_relay.set(Relay.Value.kOn);
        compressor_relay.set(Relay.Value.kReverse);

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
    
    public void autonomousInit(){
        
       autonomousCommand = new CommandAutonomous("AutonomousOneBall.txt");
        autonomousCommand.start();
    }
    
    public void disabledInit(){
        compressor_relay.set(Relay.Value.kOff);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        System.out.println("ps: " + pressure_switch.get());
        
        System.out.println("hall effect: " + CommandBase.launchersubsystem.hitHESensor());
        if (pressure_switch.get()) {
            compressor_relay.set(Relay.Value.kOn);
            compressor_relay.set(Relay.Value.kReverse);
            compressor_relay.set(Relay.Value.kOff);
        }
        else {
            compressor_relay.set(Relay.Value.kOff);
            compressor_relay.set(Relay.Value.kReverse);
        }
        if (CommandBase.jsdriver.rightTrigger() &&
                !(CommandBase.launchersubsystem.getCurrentCommand() instanceof CommandGroupShoot) ){
            Scheduler.getInstance().add(new CommandGroupShoot());
        }
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
        
        
//       if (!pressureSwitch.get()){
//           t.set(1);
//       } else { t.set(0.0); } 
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
