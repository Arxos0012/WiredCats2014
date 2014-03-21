package edu.wpi.first.wpilibj.templates.commands;

import Utilities.GamePad;
import Utilities.TXTReader;
import com.sun.squawk.util.SimpleLinkedList;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemDrive;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemLDI;
import edu.wpi.first.wpilibj.templates.subsystems.SubSystemLauncher;
import java.util.Vector;

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
    
    public static GamePad jsdriver = new GamePad(RobotMap.JS_DRIVER);
    public static GamePad jssupport = new GamePad(RobotMap.JS_SUPPORT);
    
    public CommandBase(){
        super();
    }
    
    public static void init() {
        
        resources.getFromFile("wiredCatsConfig.txt");
        
        //DRIVER
        jsdriver.leftBumper.whileHeld(new CommandOuttake());
        jsdriver.rightBumper.whenPressed(new CommandLastMinuteShit());
        jsdriver.rightBumper.whenReleased(new CommandIntakeDelay());

        
        //SUPPORT
//        jssupport.y_button.whenPressed(new CommandCock());
        jssupport.a_button.whenPressed(new CommandExtendHood());
        jssupport.a_button.whenReleased(new CommandRetractHood());
        
        drivesubsystem.init();
    }
    
    public int autoParameters(){
        return 0;
    }
    
    public void autoInit(float[] vals){
        
    }

    public CommandBase(String name) {
        super(name);
    }
    
    public void updateValues(){}

}
