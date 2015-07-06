
package com.hcdc.coedp.safe.domain.scheduler;

/**
 * Spring form bean for monthly setting of scheduler.
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class MonthlyForm {
    
    private int dayOfMonth;
    
    private int hours;
    
    private int minutes;
    
    private int occurenceOfDay;
    
    private int weekDay;
    
    private int everyMonth;

    public int getEveryMonth() {
        return everyMonth;
    }

    public void setEveryMonth(int everyMonth) {
        this.everyMonth = everyMonth;
    }

    public int getOccurenceOfDay() {
        return occurenceOfDay;
    }

    public void setOccurenceOfDay(int occurenceOfDay) {
        this.occurenceOfDay = occurenceOfDay;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
    
     
    
    /**
     * 
     * @return
     */
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * 
     * @param dayOfMonth
     */
    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

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
