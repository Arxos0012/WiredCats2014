/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author WiredCats
 */
public class ATKJoystick extends Joystick{

    public ATKJoystick(int port) {
        super(port);
    }
    
    /**
     * Every button on the ATK has a number on it,
     * which corresponds to its port. So, buttons 
     * are pretty easy for these.
     * @param buttonNumber
     * @return 
     */
    public boolean getButton(int port){
        return this.getRawButton(port);
    }
}
