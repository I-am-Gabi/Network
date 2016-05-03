package util;

import server.model.Idea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 02/05/16.
 */
public class DBHelper {
    private Connection c;

    private void connectDB() {
        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ideas.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void insertDB(Idea idea)
    {
        connectDB();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO ideas (name, description, technologies, student_name, student_email) " +
                    "VALUES ('"+ idea.getName() + "', '"+ idea.getDescription() + "', '"+ idea.getTechnologies() + "'," +
                    "'"+ idea.getStudent_name() + "'," + "'"+ idea.getStudent_email() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public List<Idea> selectDB()
    {
        connectDB();
        List<Idea> ideas = new ArrayList<>();
        Statement stmt;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ideas;" );
            while ( rs.next() ) {
                Idea idea = new Idea();
                idea.setName(rs.getString("name"));
                idea.setDescription(rs.getString("description"));
                idea.setTechnologies(rs.getString("technologies"));
                idea.setStudent_name(rs.getString("student_name"));
                idea.setStudent_email(rs.getString("student_email"));
                ideas.add(idea);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return ideas;
    }

    public Idea selectIdeaByName(String id)
    {
        Idea idea = null;
        connectDB();
        Statement stmt;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ideas WHERE id="+ id +";" );
            while ( rs.next() ) {
                idea = new Idea();
                idea.setName(rs.getString("name"));
                idea.setDescription(rs.getString("description"));
                idea.setTechnologies(rs.getString("technologies"));
                idea.setStudent_name(rs.getString("student_name"));
                idea.setStudent_email(rs.getString("student_email"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return idea;
    }

    public static void main(String[] args) {
        DBHelper helper = new DBHelper();
        List<Idea> ideas = helper.selectDB();

        for (Idea i: ideas) {
            System.out.print(i.toString() + "\n");
        }

        Idea idea = new Idea("Logiciel", "presentation", "java", "cesar@email.com", "cesar");
        helper.insertDB(idea);

        ideas = helper.selectDB();
        for (Idea i: ideas) {
            System.out.print(i.toString());
        }

        idea = helper.selectIdeaByName("1");
        System.out.print(idea.toString());
    }
}
