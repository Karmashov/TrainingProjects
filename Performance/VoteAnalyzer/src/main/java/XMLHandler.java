import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private Voter voter;
    private static DateTimeFormatter birthDayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static DateTimeFormatter visitDateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    private HashMap<Voter, Integer> voterCounts;
    private static HashMap<Integer, WorkTime> voteStationWorkTimes;
    private long recordsCount;

    public XMLHandler() {
        voterCounts = new HashMap<>();
        voteStationWorkTimes = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {

            if (qName.equals("voter")){
//                LocalDate birthDay = LocalDate.parse(attributes.getValue("birthDay"), birthDayFormat);
//                voter = new Voter(attributes.getValue("name"), birthDay);
                DBConnection.multiInsertQuery(attributes.getValue("name"),attributes.getValue("birthDay"));
                recordsCount++;
            }
//            else if (qName.equals("visit") && voter != null){
//                int count = voterCounts.getOrDefault(voter, 0);
//                voterCounts.put(voter, count + 1);
//
//                int station = Integer.parseInt(attributes.getValue("station"));
//                LocalDateTime visitDate = LocalDateTime.parse(attributes.getValue("time"), visitDateFormat);
//                WorkTime workTime = voteStationWorkTimes.get(station);
//                if(workTime == null) {
//                    workTime = new WorkTime();
//                    voteStationWorkTimes.put(station, workTime);
//                }
//                workTime.addVisitTime(visitDate);
//            }
//            else {
//                throw new RuntimeException("incorrect data");
//            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equals("voter") && (recordsCount % 80000) == 0){
//                if ((recordsCount % 80000) == 0){
//                    DBConnection.executeMultiInsert();
//                }
//            voter = null;
                DBConnection.executeMultiInsert();
            }
            if (qName.equals("voters")){
                DBConnection.executeMultiInsert();
                System.out.println(recordsCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printWorkTimes(){
        System.out.println("Voting station work times: ");
        for(int station : voteStationWorkTimes.keySet()){
            WorkTime workTime = voteStationWorkTimes.get(station);
            System.out.println(station + " - " + workTime.toString());
        }
    }

    public void printDuplicatedVoters(){
        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()){
            int count = voterCounts.get(voter);
            if (count > 1){
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
