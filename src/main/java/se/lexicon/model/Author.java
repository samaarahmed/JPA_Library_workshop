package se.lexicon.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorID;
    private String firstName;
    private String lastName;
    @ManyToMany(
            cascade = {CascadeType.REFRESH,CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "authors"
    )

    private Set<Book> writtenBooks;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        if (writtenBooks == null) writtenBooks = new HashSet<>();
        return writtenBooks;

    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        if (writtenBooks.isEmpty()){
            if (this.writtenBooks != null){
                this.writtenBooks.forEach( book -> book.getAuthors().remove(this));
            }
        }else {
            writtenBooks.forEach(book ->book.getAuthors().add(this));
        }

        this.writtenBooks = writtenBooks;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorID == author.authorID && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(writtenBooks, author.writtenBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorID, firstName, lastName, writtenBooks);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
