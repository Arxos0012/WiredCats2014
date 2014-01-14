/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Robotics
 */
public class JoystickDriver extends Joystick{
 
    
    public JoystickDriver(int port){
        super(port);
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
