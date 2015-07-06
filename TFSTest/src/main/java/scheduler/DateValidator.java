package com.hcdc.coedp.safe.domain.scheduler;

/**
 * DateValidator class used by cron builder to vaildate day month hours mins and
 * seconds.i.e.
 * <pre>
 * <code>
 *         DateValidator.validateDayOfWeek(dayOfWeek);
 *         will throw an illegal argument exception if the day value is not 1-7.
 *
 *     </code>
 * </pre>
 *
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class DateValidator {

    /**
     * Integer constant for day MONDAY.
     */
    public static final int MONDAY = 1;
    /**
     * Integer constant for day TUESDAY.
     */
    public static final int TUESDAY = 2;
    /**
     * Integer constant for day WEDNESDAY.
     */
    public static final int WEDNESDAY = 3;
    /**
     * Integer constant for day THURSDAY.
     */
    public static final int THURSDAY = 4;
    /**
     * Integer constant for day FRIDAY.
     */
    public static final int FRIDAY = 5;
    /**
     * Integer constant for day SATURDAY.
     */
    public static final int SATURDAY = 6;
    /**
     * Integer constant for day SUNDAY.
     */
    public static final int SUNDAY = 7;

    /**
     * Validate day of the week.
     *
     * @param dayOfWeek
     */
    public static void validateDayOfWeek(int dayOfWeek) {
        if (dayOfWeek < MONDAY || dayOfWeek > SUNDAY) {
            throw new IllegalArgumentException("Invalid day of week.");
        }
    }

    /**
     * Validate hour of day.
     *
     * @param hour
     */
    public static void validateHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException(
                    "Invalid hour (must be >= 0 and <= 23).");
        }
    }

    /**
     * Validate minute of hour.
     *
     * @param minute
     */
    public static void validateMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException(
                    "Invalid minute (must be >= 0 and <= 59).");
        }
    }

    /**
     * Validate second of minute.
     *
     * @param second
     */
    public static void validateSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException(
                    "Invalid second (must be >= 0 and <= 59).");
        }
    }

    /**
     * Validate day of month.
     *
     * @param day
     */
    public static void validateDayOfMonth(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day of month.");
        }
    }
}
