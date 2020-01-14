package src.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public enum HibernateConfig {

    INSTANCE;

    private SessionFactory sessionFactoryInstance;

    HibernateConfig() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactoryInstance = metadata.getSessionFactoryBuilder().build();
    }

    public SessionFactory getSessionFactory(){
        return sessionFactoryInstance;
    }
}
