/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import Utilities.GoodScanner;
import Utilities.PneumaticSystem;
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
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeAlpha;
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
    DigitalInput autonomous_switch;
    
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
        autonomous_switch = new DigitalInput(RobotMap.AUTONOMOUS_SWITCH);
        autonomousCommand = new CommandAutonomous();
        if (!pressure_switch.get()) CommandBase.pneumaticsystem = new PneumaticSystem(0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        if (pressure_switch.get()) {
            compressor_relay.set(Relay.Value.kOn);
            compressor_relay.set(Relay.Value.kReverse);
            compressor_relay.set(Relay.Value.kOff);
        }
        else {
            compressor_relay.set(Relay.Value.kOff);
            compressor_relay.set(Relay.Value.kReverse);
        }
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
        System.out.println("[WiredCats] Updating Values.");
        CommandBase.drivesubsystem.init();
    }
    
    public void autonomousInit(){
       if (!autonomous_switch.get()){
           System.out.println("[WiredCats] Running OneBallAutonomous.");
           autonomousCommand = new CommandAutonomous("OneBallAutonomous.txt");
       } else {
           System.out.println("[WiredCats] Running TwoBallAutonomous.");
           autonomousCommand = new CommandAutonomous("TwoBallAutonomous.txt");
       }
       
       autonomousCommand.start();
    }
    
    public void disabledInit(){
        compressor_relay.set(Relay.Value.kOff);
        CommandBase.pneumaticsystem.stopTimer();
    }
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        System.out.println("ps: " + pressure_switch.get());
        
        //updates the conceived pressure of the system.
        CommandBase.pneumaticsystem.update(!pressure_switch.get());
        
        if (pressure_switch.get()) {
            if (compressor_relay.get() != Relay.Value.kOff){
                //it has hit the boundary of the pressure switch.
                CommandBase.pneumaticsystem.set(PneumaticSystem.PRESSURE_SWITCH_PSI_RISING);
            }
            compressor_relay.set(Relay.Value.kOn);
            compressor_relay.set(Relay.Value.kReverse);
            compressor_relay.set(Relay.Value.kOff);
        }
        else {
            if (compressor_relay.get() != Relay.Value.kReverse){
                //it has hit the boundary of the pressure switch;
                CommandBase.pneumaticsystem.set(PneumaticSystem.PRESSURE_SWITCH_PSI_FALLING);
            }
            
            compressor_relay.set(Relay.Value.kOff);
            compressor_relay.set(Relay.Value.kReverse);
        }
        if (CommandBase.jsdriver.rightTrigger() &&
                !(CommandBase.launchersubsystem.getCurrentCommand() instanceof CommandGroupShoot) ){
            Scheduler.getInstance().add(new CommandGroupShoot());
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
