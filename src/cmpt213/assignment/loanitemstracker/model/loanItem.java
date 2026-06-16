package cmpt213.assignment.loanitemstracker.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Abstract base class representing a loan item
 * Stores information common to all loan items such as
 * name, due date, publisher, loanedto
 * Subclasses include Book, Audio and Video
 */
public abstract class loanItem implements Comparable<loanItem>{
    private String name;
    private LocalDate duedate;
    private String publisher;
    private String loanedTo;
    private String itemType;

    /**
     * Constructs a loan item with the specified information.
     *
     * @param name Name of the loan item.
     * @param duedate Due date of the loan item.
     * @param publisher Publisher of the loan item.
     * @param loanedTo Person the item is loaned to.
     */
    public loanItem(String name, LocalDate duedate, String publisher, String loanedTo){
        this.name = name;
        this.duedate = duedate;
        this.publisher = publisher;
        this.loanedTo = loanedTo;
    }
    /**
     * Gets the name of the loan item
     * @return the item name
     */
    public String getName(){
        return name;
    }
    /**
     * Gets the due date of the loan item 
     * @return the due date
     */
    public LocalDate getDueDate(){
        return duedate;
    }
    /**
     * Gets the publisher of the loan item
     * @return The publisher name
     */
    public String getPublisher(){
        return publisher;
    }
    /**
     * Gets the name of the borrower
     * @return The borrower name
     */
    public String getloanedTo(){
        return loanedTo;
    }
    /**
     * Gets the type label used for JSON serialization.
     *
     * @return The item type.
     */
    public String getItemType(){
        return itemType;
    }
    /**
     * Sets the type label used for JSON serialization.
     *
     * @param itemType The item type to store.
     */
    public void setItemType(String itemType){
        this.itemType = itemType;
    }
    /**
     * Calculates and returns the due status of the item.
     *
     * @return A string describing whether the item is overdue,
     * due today, or due in the future.
     */
    public String getDueStatus(){
        long days = ChronoUnit.DAYS.between(LocalDate.now(), duedate);
        if(days < 0){
            return "overdue by "+ (-days) + "days(s)" ;
        }
        if(days ==0){
            return "due today";
        }
        return "due in " + days + "day(s)";
    }
    /**
     * Compares two loan items based on their due dates.
     *
     * @param item The loan item to compare against.
     * @return Negative if this item is earlier, positive if later,
     * and zero if both have the same due date.
     */
    @Override
    public int compareTo(loanItem item){
        return duedate.compareTo(item.duedate);
    }
    /**
     * Gets the type of loan item.
     *
     * @return The item type.
     */
    public abstract String getType();
    /**
     * Gets subclass-specific information about the item.
     *
     * @return Additional information for display purposes.
     */
    public abstract String getExtraInfo();  


}
