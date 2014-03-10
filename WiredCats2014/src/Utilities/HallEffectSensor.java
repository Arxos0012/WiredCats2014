/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.SensorBase;

/**
 *
 * @author WiredCats
 */
public class HallEffectSensor extends SensorBase {
    
    static final double kSamplesPerSecond = 50.0;
    
    static final double activatedThreshold = 5.0;
    
    private AnalogChannel m_anchan;
    
    public HallEffectSensor(int channel){
        m_anchan = new AnalogChannel(channel);
    }
    
}
