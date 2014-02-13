/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class CommandWait extends CommandBase{
    
    private double time;
    private Timer t;
    
    public CommandWait(double time){
        this.time = time;
    }

    protected void initialize() {
        t = new Timer();
        t.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return t.get() > time;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
