package Utilities;

/**
 *
 * @author omarimatthews i am a cool kid 1337 haxor metaldaning ur mom
 */
public class PID {

    float kp, ki, kd;
    float errorSum;
    float errorPrev;
    float desiredPrev;
    
    /**
     * Sets up a PID loop
     * @param kp the proportional constant
     * @param ki the integral constant
     * @param kd the differential constant
     */
    public PID(float kp, float ki, float kd){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        errorSum = 0;
        errorPrev = 0;
    }
    
    public void setP(float kp){
        this.kp = kp;
    }
    
    /**
     * Sets up a PI loop
     * @param kp the proportional constant
     * @param ki the integral constant
     */
    public PID(float kp, float ki) {
        this(kp, ki, 0);
    }
    
    /**
     * Sets up a P loop
     * @param kp the proportional constant
     */
    public PID(float kp) {
        this(kp,0,0);
    }
    
    /**
     * Finds error between two powers
     * @param desired desired power
     * @param actual actual power
     * @return difference of desired and actual
     */
    public float proportional(float desired, float actual) { 
        return desired - actual;
    }
    
    /**
     * Sum of Errors
     * @param desired desired power
     * @param actual actual power
     * @return sum of errors
     */
    public float integral (float desired, float actual) {
        if(desired != desiredPrev) {
            errorSum = 0;
        }
        errorSum = errorSum + proportional(desired, actual);
        return errorSum;
    }
    
    /**
     * Finds the differential value of the current and the previous error
     * @param desired the desired power
     * @param actual the actual power
     * @return returns the differential value of the current and previous error
     */
    public float differential(float desired, float actual){
        if(desired != desiredPrev){
            errorPrev = 0;
        }
        float errorCurrent = desired - actual;
        float output = errorCurrent - errorPrev;
        errorPrev = errorCurrent;
        return output;
    }
    
    /**
     * Finds the PID
     * @param desired the desired power
     * @param actual the actual power
     * @return how much power you want to apply
     */
    public float pid(float desired, float actual){
        float out = kp*proportional(desired, actual) +
                ki*integral(desired, actual) + 
                kd*differential(desired, actual);
        desiredPrev = desired;
        return out;
    }
}
