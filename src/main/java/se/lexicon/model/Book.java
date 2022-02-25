package se.lexicon.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String isbn;
    private String title;
    private int maxLoanDays;
    @ManyToMany
            ( cascade = {CascadeType.REFRESH,CascadeType.DETACH},
    fetch = FetchType.LAZY)

    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book_id", table ="book_author"),
            inverseJoinColumns = @JoinColumn(name = "fk_author_id", table = "book_author")
    )

    private Set<Author> authors;

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }
     public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public Set<Author> getAuthors() {
        if (authors == null) authors = new HashSet<>();
        return authors;

    }

    public void setAuthors(Set<Author> authors) {
        if (authors == null) authors = new HashSet<>();
        if (authors.isEmpty()){
            if (this.authors != null){
                this.authors.forEach( author -> author.getWrittenBooks().remove(this));
            }
        }else {
            authors.forEach(author -> author.getWrittenBooks().add(this));
        }

        this.authors = authors;


    }
    public void addBook(Author author){
        if (author == null) throw new IllegalArgumentException("Author was null");

        if(this.authors == null ) this.authors = new HashSet<>();
        this.authors.add(author);
        author.getWrittenBooks().add(this);
    }
    public void removeBook(Author author)
    {
        if (author == null) throw new IllegalArgumentException("Author was null");
        if(this.authors == null) this.authors = new HashSet<>();

        this.authors.remove(author);
        author.getWrittenBooks().remove(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId() && getMaxLoanDays() == book.getMaxLoanDays() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getIsbn(), getTitle(), getMaxLoanDays());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }
}
