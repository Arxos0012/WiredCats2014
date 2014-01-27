/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.CommandArcadeDrive;
import edu.wpi.first.wpilibj.templates.commands.CommandTankDrive;

/**
 *
 * @author Robotics
 */
public class GamePad extends Joystick{
 
    public JoystickButton a_button;
    public JoystickButton b_button;
    
    public GamePad(int port){
        super(port);
        a_button = new JoystickButton(this, 1);
        b_button = new JoystickButton(this, 2);
    }
    
    public double leftY(){
        return this.getRawAxis(2);
    }
    
    public double rightY(){
        return this.getRawAxis(5);
    }
    
    public double rightX(){
        return this.getRawAxis(4);
    }
    
    public boolean isAPressed(){
        
        return this.getRawButton(1);
    }
}
