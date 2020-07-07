import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Voter
{
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private String name;
    private LocalDate birthDay;

    public Voter(String name, LocalDate birthDay)
    {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object obj)
    {
        Voter voter = (Voter) obj;
        return name.equals(voter.name) && birthDay.equals(voter.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(birthDay);
    }

    public String toString()
    {
        return name + " (" + birthDay.format(dateFormat) + ")";
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getBirthDay()
    {
        return birthDay;
    }
}
