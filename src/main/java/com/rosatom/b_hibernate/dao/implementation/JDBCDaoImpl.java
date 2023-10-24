package com.rosatom.b_hibernate.dao.implementation;

import com.rosatom.b_hibernate.dao.Dao;
import com.rosatom.b_hibernate.entity.UserJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/// FOR TEST PURPOSES ONLY ///
class Test {
    public static void main(String[] args) throws SQLException {
        try (JDBCDaoImpl connection = new JDBCDaoImpl("test_db", "postgres", "")) {
//            User user = new User(1L, "Misha", "Mischievous", 20, "fishing");

//            System.out.println(connection.getById(7L));
//            System.out.println(connection.getByName("Misha"));
//            System.out.println(connection.getByAge(-4));
        }
    }
}
//////////////////////////////

/**
 * Represents a connection to a PostgreSQL database using JDBC.
 * Allows performing CRUD operations (with filtering and pagination).
 */
public class JDBCDaoImpl implements Dao<UserJDBC>, AutoCloseable {
    private final Connection connection;

    public JDBCDaoImpl(String dbName, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/" + dbName,
                user,
                password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet set = statement.executeQuery(" ");
    }

    @Override
    public void save(UserJDBC userJDBC) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO public.users (name, surname, age, hobby) " +
                             "VALUES (?, ?, ?, ?)")) {

            setAndExecuteStatement(statement, userJDBC, null);
        }
    }

    @Override
    public Optional<UserJDBC> getById(Long id) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM public.users WHERE id = ?;")) {

            statement.setLong(1, id);
            return getUserFromResultSet(statement.executeQuery(), id);
        }
    }

    @Override
    public Optional<List<UserJDBC>> getByName(String name) throws SQLException {
        return getByName(name, Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserJDBC>> getByName(String name, Integer limit, Integer offset)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM public.users " +
                             "WHERE name = ? LIMIT ? OFFSET ?;")) {

            int parameterIndex = 1;
            statement.setString(parameterIndex++, name);
            return getUsersListFromStatement(statement, limit, offset, parameterIndex);
        }
    }

    @Override
    public Optional<List<UserJDBC>> getByAge(Integer age) throws SQLException {
        return getByAge(age, Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserJDBC>> getByAge(Integer age, Integer limit, Integer offset)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM public.users " +
                             "WHERE age = ? LIMIT ? OFFSET ?;")) {

            int parameterIndex = 1;
            statement.setInt(parameterIndex++, age);
            return getUsersListFromStatement(statement, limit, offset, parameterIndex);
        }
    }

    @Override
    public Optional<List<UserJDBC>> getAll() throws SQLException {
        return getAll(Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserJDBC>> getAll(Integer limit, Integer offset) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM public.users LIMIT ? OFFSET ?;")) {

            return getUsersListFromStatement(statement, limit, offset, 1);
        }
    }

    @Override
    public void updateById(Long id, UserJDBC userJDBC) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE public.users SET " +
                             "name = ?, surname = ?, age = ?, hobby = ? WHERE id = ?;")) {

            setAndExecuteStatement(statement, userJDBC, id);
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM public.users WHERE id = ?")) {

            setAndExecuteStatement(statement, null, id);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private void setAndExecuteStatement(PreparedStatement statement, UserJDBC userJDBC, Long id)
            throws SQLException {
        int parameterIndex = 1;

        if (userJDBC != null) {
            statement.setString(parameterIndex++, userJDBC.getName());
            statement.setString(parameterIndex++, userJDBC.getSurname());
            statement.setInt(parameterIndex++, userJDBC.getAge());
            statement.setString(parameterIndex++, userJDBC.getHobby());
        }

        if (id != null) {
            statement.setLong(parameterIndex, id);
        }

        statement.executeUpdate();
    }

    private Optional<List<UserJDBC>> getUsersListFromStatement(PreparedStatement statement,
                                                               Integer limit, Integer offset,
                                                               int parameterIndex)
            throws SQLException {

        statement.setInt(parameterIndex++, limit);
        statement.setInt(parameterIndex, offset);

        final ResultSet result = statement.executeQuery();

        Optional<List<UserJDBC>> optionalUsers = Optional.empty();
        Optional<UserJDBC> user = getUserFromResultSet(result, null);
        if (user.isPresent()) {
            optionalUsers = Optional.of(new ArrayList<>());

            do {
                optionalUsers.get().add(user.get());
                user = getUserFromResultSet(result, null);
            } while (user.isPresent());
        }

        return optionalUsers;
    }

    private Optional<UserJDBC> getUserFromResultSet(ResultSet result, Long id)
            throws SQLException {
        return result.next()
                ? Optional.of(new UserJDBC(
                (id == null ? result.getLong("id") : id),
                result.getString("name"),
                result.getString("surname"),
                result.getInt("age"),
                result.getString("hobby")))
                : Optional.empty();
    }
}
