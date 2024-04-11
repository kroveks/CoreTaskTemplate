package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Создаем соединение с базой данных
                Connection connection = getConnection();

                // Настройка Hibernate через объект StandardServiceRegistry
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydatabase")
                        .applySetting("hibernate.connection.username", "root")
                        .applySetting("hibernate.connection.password", "password123")
                        .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .build();

                // Создаем метаданные на основе аннотаций в классах сущностей
                Metadata metadata = new MetadataSources(standardRegistry)
                        .getMetadataBuilder()
                        .build();

                // Создаем фабрику сессий
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password123";
        return DriverManager.getConnection(url, user, password);
    }
}
