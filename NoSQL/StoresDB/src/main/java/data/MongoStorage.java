package data;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoStorage {
    private MongoCollection<Document> storesCollection;
    private MongoCollection<Document> productsCollection;

    public void dbInit(){
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

        MongoDatabase database = mongoClient.getDatabase("StoresDB");

        storesCollection = database.getCollection("Store");
        productsCollection = database.getCollection("Product");

        storesCollection.drop();
        productsCollection.drop();
    }

    public void addStorage(String store){
        storesCollection.insertOne(new Document().append("Name", store));
    }

    public void addProduct(String product, int price){
        productsCollection.insertOne(new Document()
                        .append("Name", product)
                        .append("Price", price));
    }

    public void putProduct(String product, String store){
        productsCollection.updateOne(new Document().append("Name", product),
                        new Document().append("$push", new Document().append("Stores", store)));
    }

    public void showStatistics() {
        Bson lookup = new Document("$lookup",
                new Document("from", "Product")
                        .append("localField", "Name")
                        .append("foreignField", "Stores")
                        .append("as", "Products"));

        Bson unwind = new Document("$unwind", "$Products");

        Bson project = new Document("$project",
                new Document("Name", 1)
                        .append("Products.Price", 1)
                        .append("count", new Document("$add", 1)));

        Bson group = new Document("$group",
                new Document("_id", "$Name")
                        .append("Количество товаров", new Document("$sum", "$count"))
                        .append("Средняя цена товара", new Document("$avg", "$Products.Price"))
                        .append("Минимальная цена товара", new Document("$min", "$Products.Price"))
                        .append("Максимальная цена товара", new Document("$max", "$Products.Price")));

        Bson match = new Document("$match",
                new Document("Products.Price", new Document("$lt", 100)));

        Bson group2 = new Document("$group",
                new Document("_id", "$Name")
                        .append("Количество товаров, дешевле 100 рублей", new Document("$sum", "$count")));

        List<Bson> filters = new ArrayList<>();
        filters.add(lookup);
        filters.add(unwind);
        filters.add(project);
        filters.add(group);

        List<Bson> filters2 = new ArrayList<>();
        filters2.add(lookup);
        filters2.add(unwind);
        filters2.add(match);
        filters2.add(project);
        filters2.add(group2);

        AggregateIterable<Document> it = storesCollection.aggregate(filters);
        AggregateIterable<Document> it2 = storesCollection.aggregate(filters2);
        for (Document row : it) {
            System.out.println(row.toJson());
            for (Document row2 : it2) {
                if (row2.get("_id").equals(row.get("_id"))) {
                    System.out.println(row2.toJson());
                    System.out.println();
                }
            }
        }
    }

    public void print(){
        storesCollection.find().forEach((Consumer<Document>) System.out::println);
        productsCollection.find().forEach((Consumer<Document>) System.out::println);
    }
}
