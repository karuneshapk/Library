package gl.mike.model;

/**
 * Created by Holub on 15.03.15.
 */
public class Book {
    private Integer id;
    private String description;
    private String author;
    private String name;

    public Book() {
    }

    public Book(String description, String author, String name) {
        this.description = description;
        this.author = author;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



