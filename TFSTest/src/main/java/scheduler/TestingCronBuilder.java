package com.hcdc.coedp.safe.domain.scheduler;

/**
 *
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class TestingCronBuilder {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Daily at hour and minute " + CronBuilder.dailyAtHourAndMinute(10, 10));
        int[] i=new int[]{DateValidator.MONDAY};
        System.out.println("atHourAndMinuteOnGivenDaysOfWeek " + CronBuilder.atHourAndMinuteOnGivenDaysOfWeek(10, 10,i));
        System.out.println("monthlyOnDayAndHourAndMinute " + CronBuilder.monthlyOnDayAndHourAndMinute(10, 10, 10));
        
        System.out.println("---------->"+CronBuilder.monthlyByOccurenceOfWeekDayHourAndMinute(2, 2, 2, 12, 12));
    }
}
