import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod implements Comparable<TimePeriod>
{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Time period within one day
     * @param from
     * @param to
     */
    public TimePeriod(LocalDateTime from, LocalDateTime to)
    {
        this.from = from;
        this.to = to;
        if(from.toLocalDate().compareTo(to.toLocalDate()) != 0)
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

    public void appendTime(LocalDateTime visitTime)
    {
        if(from.toLocalDate().compareTo(visitTime.toLocalDate()) != 0)
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        if(visitTime.isBefore(from)) {
            from = visitTime;
        }
        if(visitTime.isAfter(to)) {
            to = visitTime;
        }
    }

    public String toString()
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String from = this.from.format(dateFormat);
        String to = this.to.format(timeFormat);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period)
    {
        LocalDate current = LocalDate.now();
        LocalDate compared = LocalDate.now();
        try {
            current = from.toLocalDate();
            compared = period.from.toLocalDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return current.compareTo(compared);
    }
}
