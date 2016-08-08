package com.cooper.countries;

import com.cooper.countries.model.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Application {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        String format = "%-30s%-15s%s%n";
        System.out.printf(format,"Country","Inet users", "Literacy");
        System.out.println("--------------------------------------------------------");
//        getCountries().forEach(System.out::println);
        System.out.println("maximum literacy: " + maxInternetUsers(getCountries()));
    }

    @SuppressWarnings("unchecked")
    private static List<Country> getCountries(){
        Session session =sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Country.class);
        List<Country> countries = criteria.list();

        session.close();
        return countries;
    }

    private static Double maxInternetUsers(List<Country> countries){

        return countries.stream()
                .filter(e->e.getAdultLiteracyRate()!=null)
                .max(Comparator.comparing(Country::getAdultLiteracyRate))
                .orElse(null).getAdultLiteracyRate();
        
    }
}
