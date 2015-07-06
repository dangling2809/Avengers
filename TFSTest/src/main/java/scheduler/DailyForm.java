
package com.hcdc.coedp.safe.domain.scheduler;

/**
 * Spring form bean for daily setting of scheduler.
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class DailyForm {
    
    private int hours;
    
    private int minutes;

    /**
     * 
     * @return
     */
    public int getHours() {
        return hours;
    }

    /**
     * 
     * @param hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * 
     * @return
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * 
     * @param minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    

}
