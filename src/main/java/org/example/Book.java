package org.example;

/**
 * Represents each book in the library.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private int published_year;
    private String genre;
    private boolean available;

    public Book(int id, String title, String author, int published_year, String genre, boolean available) {
        this.id = 0; //id is auto incremented in the database
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published_year=" + published_year +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                '}';
    }
}
