
package com.hcdc.coedp.safe.domain.scheduler;

/**
 * Class for building cron-expressions for schedulers
 * currently supports 24-Hour time formats.
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class CronBuilder {

    
     /**
     * Create a cron-expression that sets the
     * scheduler to fire every day at the given time (hour and minute).
     * 
     * @param hour the hour of day to fire
     * @param minute the minute of the given hour to fire
     * @return cron expression string
     * 
     */
    public static String dailyAtHourAndMinute(int hour, int minute) {
        DateValidator.validateHour(hour);
        DateValidator.validateMinute(minute);

        String cronExpression = String.format("0 %d %d ? * *", minute, hour);

        return cronExpression;
    }
    
    /**
     * Create a  cron-expression that sets the
     * scheduler to fire at the given day at the given time 
     * (hour and minute) on the given days of the week.
     * @param daysOfWeek the days of the week to fire note here the days value are [MON-SUN] [1-7] unlike quartz or unix.(Not checked properly)
     * @param hour the hour of day to fire
     * @param minute the minute of the given hour to fire
     * @return cron expression string
     * @see DateValidator#MONDAY
     * @see DateValidator#TUESDAY
     * @see DateValidator#WEDNESDAY
     * @see DateValidator#THURSDAY
     * @see DateValidator#FRIDAY
     * @see DateValidator#SATURDAY
     * @see DateValidator#SUNDAY
     */
    public static String atHourAndMinuteOnGivenDaysOfWeek(int hour, int minute, int[] daysOfWeek)  {
      if(daysOfWeek == null || daysOfWeek.length == 0)
        throw new IllegalArgumentException("You must specify at least one day of week.");
      for(int dayOfWeek : daysOfWeek)
        DateValidator.validateDayOfWeek(dayOfWeek);
        DateValidator.validateHour(hour);
        DateValidator.validateMinute(minute);

        String cronExpression = String.format("0 %d %d ? * %d", minute, hour, daysOfWeek[0]);
        
        for(int i=1; i < daysOfWeek.length; i++)
          cronExpression = cronExpression + "," + daysOfWeek[i];

        return cronExpression;
    }
    
    
     /**
     * Create a cron-expression that sets the
     * scheduler to fire one per month on the given day of month at the given 
     * time (hour and minute).
     * 
     * @param dayOfMonth the day of the month to fire
     * @param hour the hour of day to fire
     * @param minute the minute of the given hour to fire
     * @return cron expression string
     */
    public static String monthlyOnDayAndHourAndMinute(int dayOfMonth, int hour, int minute) {
        DateValidator.validateDayOfMonth(dayOfMonth);
        DateValidator.validateHour(hour);
        DateValidator.validateMinute(minute);

        String cronExpression = String.format("0 %d %d %d * ?", minute, hour, dayOfMonth);

        return cronExpression;
    }
    
    
    /**
     * Create a cron expression that sets the scheduler to fire 
     * in periodic ocurrence of months and the week number with hours and minute.
     * The final statement translated should be like.
     * 
     * 
     * 
     * <p>The {First,Second,Third,Fourth} {Monday,Tuesday...} of every {1,2,3....} months</p>
     * 
     * <p>Validations pending.</p>
     * @param occurenceOfDay Must be 1,2,3,4
     * @param weekDay must be value between [1-7] representing [MON-SUN]
     * @param everyMonth int value
     * @param hour the hour of day to fire
     * @param minute the minute of the given hour to fire
     * @return cron expression string
     */
    public static String monthlyByOccurenceOfWeekDayHourAndMinute(int occurenceOfDay,int weekDay,int everyMonth,int hour,int minute)
    {
        String cronExpression=String.format("0 %d %d ? 1/%d %d#%d", minute,hour,everyMonth,weekDay,occurenceOfDay);
                return cronExpression;
    }
}
