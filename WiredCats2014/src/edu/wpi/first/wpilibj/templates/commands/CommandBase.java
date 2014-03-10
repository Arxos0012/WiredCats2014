package edu.wpi.first.wpilibj.templates.commands;

import Utilities.GamePad;
import Utilities.TXTReader;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemDrive;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemLDI;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemLauncher;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static TXTReader resources = new TXTReader();
    
    public static SubSystemDrive drivesubsystem = new SubSystemDrive();
    public static SubSystemLauncher launchersubsystem = new SubSystemLauncher();
    public static SubSystemLDI ldisubsystem = new SubSystemLDI();
    
    public static GamePad jsdriver = new GamePad(RobotMap.DRIVER);
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        resources.getFromFile("wiredCatsConfig.txt");
        
        jsdriver.leftBumper.whileHeld(new CommandOuttake());
        jsdriver.y_button.whenPressed(new CommandCock());
        jsdriver.x_button.whenPressed(new CommandGroupShoot());
        jsdriver.rightBumper.whileHeld(new CommandIntake());
        jsdriver.a_button.whenPressed(new CommandExtendHood());
        jsdriver.a_button.whenReleased(new CommandRetractHood());
        
        //IN WILSON FILE: LEFT TRIGGER INTAKE
        //                RIGHT TRIGGER LAUNCH
        
        drivesubsystem.init();
        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData("Autotune PID", new CommandAutotunePID());
    }

    public CommandBase(String name) {
        super(name);
    }
    
    public void updateValues(){}

    public CommandBase() {
        super();
    }
}
