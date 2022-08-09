package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection conn = Util.getMYSQLConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users47(id BIGINT" +
                        " NOT NULL PRIMARY KEY AUTO_INCREMENT, name varchar(100), lastName varchar(100), age tinyint)");
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void dropUsersTable() {
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                statement.executeUpdate("DROP TABLE users47");
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement pr = conn.prepareStatement("INSERT INTO users47 (name, lastName, age) values(?,?,?)");
                pr.setString(1,name);
                pr.setString(2,lastName);
                pr.setByte(3,age);
                pr.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void removeUserById(long id) {
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users47 WHERE id = ?");
                ps.setLong(1,id);
                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM users47");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getString("name"),rs.getString("lastName"),rs.getByte("age")));
                }
            }
        } catch (SQLException e) {
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMYSQLConnection()) {
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users47");
                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
