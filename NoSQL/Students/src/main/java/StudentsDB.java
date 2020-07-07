import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StudentsDB {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("Students");

        collection.drop();

        try {
            List<String> lines = Files.readAllLines(Paths.get("data/mongo.csv"));
            ArrayList<Document> students = new ArrayList<>();
            for(String line : lines) {
                String[] fragments = line.split(",",3);
                fragments[2] = fragments[2].replace("\"", "");
                fragments[2] = fragments[2].replace(",", ", ");
                students.add(new Document()
                        .append("Name", fragments[0])
                        .append("Age", Integer.parseInt(fragments[1]))
                        .append("Courses", fragments[2]));
            }
            collection.insertMany(students);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Общее количество студентов: " +
                collection.countDocuments());
        System.out.println("Количество студентов старше 40 лет: " +
                collection.countDocuments(BsonDocument.parse("{Age: {$gt: 40}}")));
        System.out.println("Имя самого молодого студента: " +
                collection.find().sort(BsonDocument.parse("{Age: 1}")).limit(1).first().get("Name"));
        System.out.println("Список курсов самого старого студента: " +
                collection.find().sort(BsonDocument.parse("{Age: -1}")).limit(1).first().get("Courses"));
    }
}
