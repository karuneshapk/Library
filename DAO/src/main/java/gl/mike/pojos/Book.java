package gl.mike.pojos;

import java.io.Serializable;

/**
 * Created by user on 17.04.2016.
 */
public class Book implements Serializable {
    private static final long serialVersionUTD = -23242424242L;



    private Integer id;
    private String name;
    private String author;
    private String description;

    public Book() {
    }

    public Book(String description, String author, String name) {
        this.name = name;
        this.author = author;
        this.description = description;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getId().equals(book.getId())) return false;
        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        return getDescription().equals(book.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
