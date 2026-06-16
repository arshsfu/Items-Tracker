package cmpt213.assignment.loanitemstracker.model;
import java.time.LocalDate;

/**
 * Represents a book loan item.
 * Stores the number of pages in addition to the
 * common loan item information inherited from loanItem.
 */
public class Book extends loanItem{
    private int pages;
    /**
     * Constructs a Book object.
     *
     * @param name Name of the book.
     * @param dueDate Due date of the book.
     * @param publisher Publisher of the book.
     * @param loanedTo Person the book is loaned to.
     * @param pages Number of pages in the book.
     */
    public Book(String name, LocalDate dueDate, String publisher, String loanedTo, int pages){
        super(name, dueDate, publisher, loanedTo);
        setItemType("book");
        this.pages = pages;
    }

    /**
     * Gets the number of pages in the book.
     *
     * @return The page count.
     */
    public int getPages(){
        return pages;
    }

    /**
     * Gets the type of loan item.
     *
     * @return "Book"
     */
    @Override
    public String getType(){
        return "Book";
    }

    /**
     * Gets book-specific information for display.
     *
     * @return The page count as a formatted string.
     */
    @Override
    public String getExtraInfo(){
        return pages + " pages";
    }
}
