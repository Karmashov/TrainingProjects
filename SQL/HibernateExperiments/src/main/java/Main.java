import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main
{

    public static void main(String[] args)
    {
        wrightLinkedPurchaseList(readPurchaseList());

    }
    private static SessionFactory getSessionFactory()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }
    private static List readPurchaseList()
    {
        Session session = getSessionFactory().openSession();

        List<PurchaseList> purchaseLists = session.createQuery("From " + PurchaseList.class.getSimpleName()).getResultList();
        List<Student> studentList = session.createQuery("From " + Student.class.getSimpleName()).getResultList();
        List<Course> courseList = session.createQuery("From " + Course.class.getSimpleName()).getResultList();
        List<LinkedPurchaseList> linkedPurchaseList = new ArrayList<LinkedPurchaseList>();

        for (PurchaseList purchase : purchaseLists)
        {
            Student studentId = null;
            Course courseId = null;
            for (Student student : studentList)
            {
                if (purchase.getStudentName().equals(student.getName()))
                {
                    studentId = student;
                }
                continue;
            }
            for (Course course : courseList)
            {
                if (purchase.getCourseName().equals(course.getName()))
                {
                    courseId = course;
                }
                continue;
            }
            linkedPurchaseList.add(new LinkedPurchaseList(studentId,courseId,purchase.getPrice(),purchase.getPurchaseDate()));
        }
        getSessionFactory().close();
        return linkedPurchaseList;
    }
    private static void wrightLinkedPurchaseList (List<LinkedPurchaseList> list)
    {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        for (LinkedPurchaseList purchase : list)
        {
            session.save(purchase);
        }
        tx.commit();
    }
}
