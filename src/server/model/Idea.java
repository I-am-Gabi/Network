package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 03/05/16.
 */
public class Idea implements Serializable {
    private String name;
    private String description;
    private String technologies;
    private String student_name;
    private String student_email;

    public Idea() {
        this.description = "";
        this.name = "";
        this.technologies = "";
        this.student_email = "";
        this.student_name = "";
    }

    public Idea(String name, String description, String technologies, String student_email, String student_name) {
        this.description = description;
        this.name = name;
        this.technologies = technologies;
        this.student_email = student_email;
        this.student_name = student_name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    @Override
    public String toString() {
        return  " | name: " + name +
                " | description: " + description +
                " | technologies: " + technologies +
                " | student email: " + student_email +
                " | student name: " + student_name;
    }
}
