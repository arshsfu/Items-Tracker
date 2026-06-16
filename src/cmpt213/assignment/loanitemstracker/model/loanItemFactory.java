package cmpt213.assignment.loanitemstracker.model;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Factory class responsible for creating loan item objects.
 * Provides methods for creating Book, Audio, and Video objects
 * while returning them as loanItem references.
 */
public class loanItemFactory {

    /**
     * Creates a Book loan item.
     *
     * @param name Name of the book.
     * @param dueDate Due date of the book.
     * @param publisher Publisher of the book.
     * @param loanedTo Person the book is loaned to.
     * @param pages Number of pages in the book.
     * @return A Book object as a loanItem reference.
     */
    public static loanItem createBook(String name, LocalDate dueDate, String publisher, String loanedTo, int pages){
        return new Book(name, dueDate, publisher, loanedTo, pages);
    }

    /**
     * Creates an Audio loan item.
     *
     * @param name Name of the audio item.
     * @param dueDate Due date of the audio item.
     * @param publisher Publisher of the audio item.
     * @param loanedTo Person the audio item is loaned to.
     * @param length Duration of the audio item.
     * @return An Audio object as a loanItem reference.
     */
    public static loanItem createAudio(String name, LocalDate dueDate, String publisher, String loanedTo, Duration length){
        return new Audio(name, dueDate, publisher, loanedTo, length);
    }

    /**
     * Creates a Video loan item.
     *
     * @param name Name of the video item.
     * @param dueDate Due date of the video item.
     * @param publisher Publisher of the video item.
     * @param loanedTo Person the video item is loaned to.
     * @param genre Genre of the video item.
     * @return A Video object as a loanItem reference.
     */
    public static loanItem createVideo(String name, LocalDate dueDate, String publisher, String loanedTo,String genre){
        return new Video(name, dueDate, publisher, loanedTo, genre);
    }
}
