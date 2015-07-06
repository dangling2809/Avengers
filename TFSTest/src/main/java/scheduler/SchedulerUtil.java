package com.hcdc.coedp.safe.domain.scheduler;

import com.hcdc.coedp.safe.generic.task.Task;
import com.hcdc.coedp.safe.util.Constants;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Piyush Patel <piyushp@cdac.in>
 * @version 1
 * @since 1
 */
@Component
public class SchedulerUtil {

    @Autowired
    private ThreadPoolTaskScheduler tps;
    @Autowired
    private SimpleTriggerContext triggerContext;
    @Autowired
    private ServletContext context;

    ;
  /**
   * Schedule a task {@link Task} with a specified cron expression.
   * @param task {@link Task}
   * @param cronExpression cron expression to be applied must be a vaild one.
   * @param taskName
   * @return 
   */
    public List<Date> start(Task task, String cronExpression, String taskName) {
        CronTrigger trigger = new CronTrigger(cronExpression);
        CronSequenceGenerator generator=new CronSequenceGenerator(cronExpression, TimeZone.getTimeZone("GMT+5:30"));
        List<Date> dateList=new ArrayList<>(5);
        Date currentDate=new Date();
        for(int i=0;i<5;i++)
        {
            currentDate=generator.next(currentDate);
            dateList.add((currentDate));
            System.out.println("Next Exceution times are"+currentDate);
        }
        ScheduledFuture sf = tps.schedule(task, trigger);
        HashMap<String, ScheduledFuture> mapOfScheduledFuture = (HashMap<String, ScheduledFuture>) context.getAttribute(Constants.MAP_OF_SCHEDULED_FUTURE);
        mapOfScheduledFuture.put(taskName, sf);
        return dateList;
    }

    /**
     * Change the scheduler properties according to passed cron expression.
     *
     * @param cronExpression cron expression to be applied.
     * @param task {@link Task}
     * @param taskName must be from {@link Constants}
     */
    public List<Date> changeTrigger(String cronExpression, Task task, String taskName) {
        System.out.println("change trigger to: " + cronExpression);
        HashMap<String, ScheduledFuture> mapOfScheduledFuture = (HashMap<String, ScheduledFuture>) context.getAttribute(Constants.MAP_OF_SCHEDULED_FUTURE);
        ScheduledFuture sf = mapOfScheduledFuture.get(taskName);
        if (sf != null) {
            System.out.println("Scheduled future not null");
            sf.cancel(false);
        }
        return start(task, cronExpression, taskName);
    }

    /**
     * Un-schedule the task.
     *
     * @param task task to be unscheduled.
     */
    public void stop(String taskName) {
        HashMap<String, ScheduledFuture> mapOfScheduledFuture = (HashMap<String, ScheduledFuture>) context.getAttribute(Constants.MAP_OF_SCHEDULED_FUTURE);
        ScheduledFuture sf = mapOfScheduledFuture.get(taskName);
        if (sf != null) {
            sf.cancel(false);
        }

    }
}
