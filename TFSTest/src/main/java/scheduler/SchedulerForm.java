package com.hcdc.coedp.safe.domain.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
public class SchedulerForm {

    private String schedulerName;
    private DailyForm dailyForm;
    private WeeklyForm weeklyForm;
    private MonthlyForm monthlyForm;
    private List<Date> nextScheduledDates = new ArrayList<>();

    public List<Date> getNextScheduledDates() {
        return nextScheduledDates;
    }

    public void setNextScheduledDates(List<Date> nextScheduledDates) {
        this.nextScheduledDates = nextScheduledDates;
    }

    public String getSchedulerName() {
        return schedulerName;
    }

    public void setSchedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
    }

    public DailyForm getDailyForm() {
        return dailyForm;
    }

    public void setDailyForm(DailyForm dailyForm) {
        this.dailyForm = dailyForm;
    }

    public MonthlyForm getMonthlyForm() {
        return monthlyForm;
    }

    public void setMonthlyForm(MonthlyForm monthlyForm) {
        this.monthlyForm = monthlyForm;
    }

    public WeeklyForm getWeeklyForm() {
        return weeklyForm;
    }

    public void setWeeklyForm(WeeklyForm weeklyForm) {
        this.weeklyForm = weeklyForm;
    }

}
