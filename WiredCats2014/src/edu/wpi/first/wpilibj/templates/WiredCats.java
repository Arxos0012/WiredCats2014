/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import Utilities.GoodScanner;
import Utilities.PneumaticSystem;
import Utilities.WiredVector;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
    
    AnalogChannel pressure_transducer;
    
    public static Timer autoTimer = new Timer();
    
    double pt_zero = 0;
    double pt_scale = 1;
    
    WiredVector pressures = new WiredVector();
    
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
        if (!pressure_switch.get()) CommandBase.pneumaticsystem = new PneumaticSystem(0);
        pressure_transducer = new AnalogChannel(RobotMap.PRESSURE_TRANSDUCER);
        autonomousCommand = new CommandAutonomous();
        pressures.addVal(111);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        System.out.println("Gyro: " + CommandBase.drivesubsystem.getAngle());
        System.out.println("Dist: " + CommandBase.drivesubsystem.getDistance());
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
        
        pt_zero = CommandBase.resources.getValue("pt_zero");
        pt_scale = CommandBase.resources.getValue("pt_scale");
        
        
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
        CheesyVisionServer.getInstance().stopSamplingCounts();
        compressor_relay.set(Relay.Value.kOff);
        CommandBase.pneumaticsystem.stopTimer();
    }
    
    public float getPressure(){
        //TODO
        float sum = 0;
        float newVel = (float)pressure_transducer.getAverageVoltage();
        pressures.addVal(newVel);
        for (int i = 0; i < pressures.size(); i++){
            sum+= pressures.getVal(i);
        }
        if (pressures.size() > 15) pressures.removeFirst();
        return sum / pressures.size();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //updates the conceived pressure of the system.
        
        double pressure = getPressure();
        pressure = pt_scale*(pressure - pt_zero);    

        System.out.println("Pressure: " + pressure);
        System.out.println("Hall Effect: " + CommandBase.launchersubsystem.hitHESensor());
        System.out.println("Dist: " + CommandBase.drivesubsystem.getDistance());
        System.out.println("Gyro: " + CommandBase.drivesubsystem.getAngle());
//        
//        System.out.println("Left Hot Goal: " + CheesyVisionServer.getInstance().getLeftStatus() + ", " + 
//                                CheesyVisionServer.getInstance().getLeftCount());
//        
//        System.out.println("Right Hot Goal: " + CheesyVisionServer.getInstance().getRightStatus() + ", " + 
//                                CheesyVisionServer.getInstance().getRightCount());
        
//        CommandBase.drivesubsystem.printTicks();
        
        
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
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
