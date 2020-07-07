import java.time.LocalDateTime;
import java.util.TreeSet;

public class WorkTime
{
    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime()
    {
        periods = new TreeSet<>();
    }

    public void addVisitTime(LocalDateTime visitTime)
    {
        TimePeriod newPeriod = new TimePeriod(visitTime, visitTime);
        for(TimePeriod period : periods)
        {
            if(period.compareTo(newPeriod) == 0)
            {
                period.appendTime(visitTime);
                return;
            }
        }
        periods.add(new TimePeriod(visitTime, visitTime));
    }

    public String toString()
    {
        String line = "";
        for(TimePeriod period : periods)
        {
            if(!line.isEmpty()) {
                line += ", ";
            }
            line += period;
        }
        return line;
    }
}
