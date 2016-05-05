package server.model;

import java.io.Serializable; 

/**
 * @version 03/05/16.
 */
@SuppressWarnings("serial")
public class Idea implements Serializable {
    private String name;
    private String description;
    private String technologies;
    private String student_name;
    private String student_email;

    /**
     * Idea's constructor.
     */
    public Idea() {
        this.description = "";
        this.name = "";
        this.technologies = "";
        this.student_email = "";
        this.student_name = "";
    }

    /**
     * Idea's constructor.
     * @param name
     * @param description
     * @param technologies
     * @param student_email
     * @param student_name
     */
    public Idea(String name, String description, String technologies, String student_email, String student_name) {
        this.description = description;
        this.name = name;
        this.technologies = technologies;
        this.student_email = student_email;
        this.student_name = student_name;
    }

    /**
     * Method to get idea name.
     * @return idea name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the idea name.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get the idea description.
     * @return idea description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the Idea description.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method to get the idea technologies.
     * @return idea technologies.
     */
    public String getTechnologies() {
        return technologies;
    }

    /**
     * Set the technologies.
     * @param technologies
     */
    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    /**
     * Method to get student name.
     * @return student name.
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     * Set student name.
     * @param student_name
     */
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    /**
     * Method to get the student email.
     * @return student email.
     */
    public String getStudent_email() {
        return student_email;
    }

    /**
     * Method to set the student email.
     * @param student_email
     */
    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    @Override
    public String toString() {
        return  " { \n" +
                "   name          => " + name + "\n"+
                "   description   => " + description + "\n"+
                "   technologies  => " + technologies + "\n"+
                "   student email => " + student_email + "\n"+
                "   student name  => " + student_name + "\n"+
                " } \n";
    }
}
