package com.rosatom.b_hibernate.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    /**
     * Saves a specified <code>T</code>.
     */
    void save(T t) throws Exception;

    /**
     * Finds <code>T</code> by a specified <code>id</code>
     * and returns <code>Optional</code> of <code>T</code>.
     */
    Optional<T> getById(Long id) throws Exception;

    /**
     * Finds <code>T</code> by a specified <code>name</code>
     * and returns <code>Optional</code>, containing <code>List</code>
     * of <code>T</code>.
     */
    Optional<List<T>> getByName(String name) throws Exception;

    /**
     * <p>Limits size of {@link #getByName(String)} selection
     * to <code>limit</code> and skips <code>offset</code>
     * positions.</p>
     */
    Optional<List<T>> getByName(String name, Integer limit, Integer offset) throws Exception;

    /**
     * Finds <code>T</code> by a specified <code>age</code>
     * and returns <code>Optional</code>, containing <code>List</code>
     * of <code>T</code>.
     */
    Optional<List<T>> getByAge(Integer age) throws Exception;

    /**
     * <p>Limits size of {@link #getByAge(Integer)} selection
     * to <code>limit</code> and skips <code>offset</code>
     * positions.</p>
     */
    Optional<List<T>> getByAge(Integer age, Integer limit, Integer offset) throws Exception;

    /**
     * Finds all <code>T</code> and returns <code>Optional</code>,
     * containing <code>List</code> of <code>T</code>.
     */
    Optional<List<T>> getAll() throws Exception;

    /**
     * <p>Limits size of {@link #getAll()} selection
     * to <code>limit</code> and skips <code>offset</code>
     * positions.</p>
     */
    Optional<List<T>> getAll(Integer limit, Integer offset) throws Exception;

    /**
     * Updates <code>T</code> that has a specified <code>id</code>.
     */
    void updateById(Long id, T t) throws Exception;

    /**
     * Deletes <code>T</code> by a specified <code>id</code>.
     */
    void deleteById(Long id) throws Exception;
}
