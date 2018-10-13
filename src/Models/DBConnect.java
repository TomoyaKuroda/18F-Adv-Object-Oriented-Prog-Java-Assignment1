package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBConnect {
    private static String user = "sql3260896";
    private static String password = "YajJwGgIcP";

    /**
     * Get all contacts from the database
     * @return contacts
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3260896?useSSL=false",
                    user, password);

            //2.  create a statement object
            statement = conn.createStatement();

            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM contacts");

            //4.  loop over the results from the DB and add to ArrayList
            while (resultSet.next())
            {

                Contact newContact = new Contact(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                        );
                newContact.setId(resultSet.getInt("contactID"));
                contacts.add(newContact);
            }
        } catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
        return contacts;
    }

    /**
     * This method receives a Contact object and will store it in the DB
     * @param newContact - must be a valid Contact object
     * @throws SQLException
     */
    public static void insertContactIntoDB(Contact newContact) throws SQLException {
        Connection conn=null;
        PreparedStatement ps = null;

        try{
            //1.  connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3260896?useSSL=false",
                    user, password);

            //2. create a sql statement
            String sql = "INSERT INTO contacts (first_name, last_name, birthday, address, phone) " +
                    "VALUES (?,?,?,?,?);";

            //3. create the PreparedStatement
            ps = conn.prepareStatement(sql);

            //4.  bind the parameters

            ps.setString(1, newContact.getFirstName());
            ps.setString(2, newContact.getLastName());
            Date date = Date.valueOf(newContact.getBirthday().toString());
            ps.setDate(3, date);
            ps.setString(4, newContact.getAddress());
            ps.setString(5, newContact.getPhone());


            //5. execute the INSERT statement
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
        }

    }
}
