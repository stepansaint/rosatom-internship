package com.test_task;


import java.sql.*;

// if there are maximum experiences that are the same,
// this query will return just the next surname
// (but with the identical maximum experience)
// the query:
    /*
        SELECT surname
          FROM table
         ORDER BY experience DESC
        OFFSET 1
         LIMIT 1;
    */

// if there should be another (really second) maximum,
// and we should show many people that can have the identical second maximum,
// this query should be executed:
    /*
        SELECT surname
          FROM table
         WHERE experience =
               (SELECT MAX(experience)
                  FROM table
                 WHERE experience <
                       (SELECT MAX(experience)
                         FROM table));
     */

// if there should be only one second maximum       // IN CODE
// (just first surname), use this query:            // USED THIS
    /*                                              // QUERY
        SELECT surname
          FROM table
         WHERE experience =
               (SELECT MAX(experience)
                  FROM table
                 WHERE experience <
                       (SELECT MAX(experience)
                         FROM table))
         LIMIT 1;
     */
public class First { // using JDBC (PostgreSQL driver)
    
    public static void main(String[] args) throws SQLException {
        String surname = getSecondEmployee("postgres", "");
        System.out.println(surname);
    }
    
    /**
     * Finds and returns the surname of the employee that has the second max experience
     * or null, if there is no such employee.
     *
     * @param user     username to log in to the database
     * @param password password to log in to the database
     * @return surname of the employee that has the second max experience
     */
    public static String getSecondEmployee(String user, String password)
            throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/test_db";
        final String max = "SELECT surname FROM atom " +
                "WHERE experience = (SELECT MAX(experience) FROM atom " +
                "WHERE experience < (SELECT MAX(experience) FROM atom)) LIMIT 1";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(max)) {
            ResultSet result = statement.executeQuery();
            
            if (!result.next()) {
                throw new IllegalArgumentException("There's no second maximum experience in this table");
            }
            
            return result.getString("surname");
        }
    }
}
