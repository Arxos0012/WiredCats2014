/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class PneumaticSystem {
    
    private static final double TOTAL_VOLUME = 7*35; //cubic inches 35 per 7 tanks.
    private static final double psi_compressed_per_second = 105/TOTAL_VOLUME;
    
    public static final double PRESSURE_SWITCH_PSI_FALLING = 103;
    public static final double PRESSURE_SWITCH_PSI_RISING = 115;
    
    //volumes when retracting.
    public static final double SHIFTING_VOLUME_R = 1.21;
    public static final double ARM_VOLUME_R = 8.04;
    public static final double HOOD_VOLUME_R = 4.11;
    public static final double WINCH_VOLUME_R = 0.39;
    //volumes when extending.
    public static final double SHIFTING_VOLUME_E = 1.27;
    public static final double ARM_VOLUME_E = 7.41;
    public static final double HOOD_VOLUME_E = 3.95;
    public static final double WINCH_VOLUME_E = 0.45;
    
    public static final double SHIFTING_WP = 60;
    public static final double ARM_WP = 40;
    public static final double HOOD_WP = 40;
    public static final double WINCH_WP = 60;
    
    Timer timer;
    private double psi;
    
    public PneumaticSystem(double psi){
        timer = new Timer();
        this.psi = psi;
    }
    
    public void set(double psi){
        this.psi = psi;
    }
    
    public void stopTimer(){
        timer.stop();
        timer.reset();
    }

    public void update( boolean compressing){
       if (timer.get() == 0) {
           timer.start();
           return;
       }
       
       if (compressing){
       psi += psi_compressed_per_second * timer.get();
       }
       timer.stop();
       timer.reset();
       timer.start();
       
       System.out.println("Current Pressure: " + psi + " psi.");
    }
    
    /**
     * Removes a certain amount
     * @param volume 
     * @param working_pressure
     * @param extending
     * 
     */
    public void actuated(double volume, double working_pressure, boolean extending){
        psi -= (working_pressure * volume)/TOTAL_VOLUME;
    }
    
}
