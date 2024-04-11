package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();

            User user1 = new User(null, "Никита", "Бабичев", (byte) 27);
            User user2 = new User(null, "Маккалей", "Галкин", (byte) 20);
            User user3 = new User(null, "Борис", "Ельцин", (byte) 99);
            User user4 = new User(null, "Егор", "Ракитин", (byte) 25);

            userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
            userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
            userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
            userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

            List<User> users = userService.getAllUsers();
            System.out.println("Список пользователей из базы данных:");
            for (User user : users) {
                System.out.println(user);
            }
            userService.cleanUsersTable();
            System.out.println("Таблица User очищена");
            userService.dropUsersTable();
            System.out.println("Таблица User удалена");
    }
}