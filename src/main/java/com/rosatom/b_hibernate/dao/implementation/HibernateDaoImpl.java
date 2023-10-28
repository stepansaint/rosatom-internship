package com.rosatom.b_hibernate.dao.implementation;

import com.rosatom.b_hibernate.dao.Dao;
import com.rosatom.b_hibernate.entity.UserHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/// FOR TEST PURPOSES ONLY ///
class TestHibernate {
    public static void main(String[] args) throws SQLException {
        try (HibernateDaoImpl connection = new HibernateDaoImpl(
                "test_db", "postgres", "", HibernateDaoImpl.HibernateDaoMode.HQL)) {
            UserHibernate user = new UserHibernate("another", "one", 4, "ff");

            System.err.println(connection.getById(1L));
//            System.out.println(connection.getByAge(44).get());
        }
    }
}
//////////////////////////////

public class HibernateDaoImpl implements Dao<UserHibernate>, AutoCloseable {
    private final SessionFactory factory;
    private final HibernateDaoMode mode;

    public HibernateDaoImpl(String dbName, String user, String password, HibernateDaoMode mode) {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserHibernate.class)
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/" + dbName)
                .setProperty("hibernate.connection.username", user)
                .setProperty("hibernate.connection.password", password)
                .buildSessionFactory();
        this.mode = mode;
    }

    @Override
    public void save(UserHibernate user) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.save(user); // HQL and Criteria API don't have INSERT INTO ... VALUES functionality

            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<UserHibernate> getById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<UserHibernate> users = Collections.emptyList();
            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<UserHibernate> query = builder.createQuery(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .select(root)
                            .where(builder.equal(root.get("id"), id));

                    users = session
                            .createQuery(query)
                            .getResultList();
                }
                case HQL -> {
                    users = session
                            .createQuery("FROM UserHibernate WHERE id = ?1", UserHibernate.class)
                            .setParameter(1, id)
                            .getResultList();
                }
            }

            session.getTransaction().commit();

            return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
        }
    }

    @Override
    public Optional<List<UserHibernate>> getByName(String name) {
        return getByName(name, Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserHibernate>> getByName(String name, Integer limit, Integer offset) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<UserHibernate> users = Collections.emptyList();
            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<UserHibernate> query = builder.createQuery(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .select(root)
                            .where(builder.equal(root.get("name"), name));

                    session.createQuery(query)
                            .setMaxResults(limit)
                            .setFirstResult(offset);

                    users = session.createQuery(query).getResultList();
                }
                case HQL -> {
                    users = session
                            .createQuery("FROM UserHibernate WHERE name = ?1", UserHibernate.class)
                            .setParameter(1, name)
                            .setMaxResults(limit)
                            .setFirstResult(offset)
                            .getResultList();
                }
            }

            session.getTransaction().commit();

            return Optional.of(users);
        }
    }

    @Override
    public Optional<List<UserHibernate>> getByAge(Integer age) {
        return getByAge(age, Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserHibernate>> getByAge(Integer age, Integer limit, Integer offset) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<UserHibernate> users = Collections.emptyList();
            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<UserHibernate> query = builder.createQuery(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .select(root)
                            .where(builder.equal(root.get("age"), age));

                    session.createQuery(query)
                            .setMaxResults(limit)
                            .setFirstResult(offset);

                    users = session.createQuery(query).getResultList();
                }
                case HQL -> {
                    users = session
                            .createQuery("FROM UserHibernate WHERE age = ?1", UserHibernate.class)
                            .setParameter(1, age)
                            .setMaxResults(limit)
                            .setFirstResult(offset)
                            .getResultList();
                }
            }

            session.getTransaction().commit();

            return Optional.of(users);
        }
    }

    @Override
    public Optional<List<UserHibernate>> getAll() {
        return getAll(Integer.MAX_VALUE, 0);
    }

    @Override
    public Optional<List<UserHibernate>> getAll(Integer limit, Integer offset) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<UserHibernate> users = Collections.emptyList();
            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<UserHibernate> query = builder.createQuery(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .select(root);

                    session.createQuery(query)
                            .setMaxResults(limit)
                            .setFirstResult(offset);

                    users = session.createQuery(query).getResultList();
                }
                case HQL -> {
                    users = session
                            .createQuery("FROM UserHibernate", UserHibernate.class)
                            .setMaxResults(limit)
                            .setFirstResult(offset)
                            .getResultList();
                }
            }

            session.getTransaction().commit();

            return Optional.of(users);
        }
    }

    @Override
    public void updateById(Long id, UserHibernate user) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaUpdate<UserHibernate> query = builder.createCriteriaUpdate(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .set(root.get("name"), user.getName())
                            .set(root.get("surname"), user.getSurname())
                            .set(root.get("age"), user.getAge())
                            .set(root.get("hobby"), user.getHobby())
                            .where(builder.equal(root.get("id"), id));

                    session.createQuery(query).executeUpdate();
                }
                case HQL -> {
                    session
                            .createQuery("UPDATE UserHibernate SET name = ?1, surname = ?2, age = ?3, hobby = ?4 WHERE id = ?5")
                            .setParameter(1, user.getName())
                            .setParameter(2, user.getSurname())
                            .setParameter(3, user.getAge())
                            .setParameter(4, user.getHobby())
                            .setParameter(5, id)
                            .executeUpdate();
                }
            }

            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            switch (mode) {
                case CRITERIA -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaDelete<UserHibernate> query = builder.createCriteriaDelete(UserHibernate.class);
                    Root<UserHibernate> root = query.from(UserHibernate.class);

                    query
                            .where(builder.equal(root.get("id"), id));

                    session.createQuery(query).executeUpdate();
                }
                case HQL -> {
                    session
                            .createQuery("DELETE UserHibernate WHERE id = ?1")
                            .setParameter(1, id)
                            .executeUpdate();
                }
            }

            session.getTransaction().commit();
        }
    }

    @Override
    public void close() {
        if (factory != null) {
            factory.close();
        }
    }

    public enum HibernateDaoMode {
        CRITERIA, HQL
    }
}
