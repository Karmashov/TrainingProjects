import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "1234";
        try
        {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name," +
                    "count(MONTH(subscription_date))/(TIMESTAMPDIFF(MONTH,min(subscription_date),max(subscription_date))) " +
                    "AS average_month FROM Purchaselist GROUP BY course_name;");

            while (resultSet.next())
            {
                System.out.println("\"" + resultSet.getString("course_name") +
                        "\" среднее количество покупок в месяц: " + resultSet.getString("average_month"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
