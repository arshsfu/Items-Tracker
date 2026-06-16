package cmpt213.assignment.loanitemstracker.textui;

import java.util.Scanner;

import cmpt213.assignment.loanitemstracker.model.loanItem;
import cmpt213.assignment.loanitemstracker.model.loanItemFactory;
import cmpt213.assignment.loanitemstracker.model.loanItemManager;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * Provides a text-based user interface for the
 * Loan Items Tracker application.
 * Handles user input, menu navigation,
 * and displaying information.
 */
public class TextUI {
    private loanItemManager manager ;
    private Scanner scanner;

    /**
     * Constructs a TextUI object.
     *
     * @param manager The loan item manager used by the UI.
     */
    public TextUI(loanItemManager manager){
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu options
     */
    private void displayMenu(){
        System.out.println();
        System.out.println("######################");
        System.out.println("# Loan Items Tracker #");
        System.out.println("######################");

        System.out.println("1: List all items");
        System.out.println("2: Add an item");
        System.out.println("3: Remove an item");
        System.out.println("4: List overdue items");
        System.out.println("5: List upcoming items");
        System.out.println("6: List all items of the same type");
        System.out.println("7: Exit");
    }

    /**
     * Prompts the user for a menu choice and validates it
     * @return A valid menu option between 1 and 7
     */
    private int getMenuChoice(){
        while(true){
            System.out.println("Choose an option by entering 1-7: ");
            try{
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice >=1 && choice <=7){
                    return choice;
                }
            }catch(Exception e){
                
            }
                System.out.println("Error: option must be between 1 and 7 ");
        }
    }

    /**
     * Displays a list of loan items
     * @param items The loan items to display
     */
    private void displayItems(List<loanItem> items){
        if(items.isEmpty()){
            System.out.println("No items to show");
            return;
        }
        int count = 1;
        for(loanItem item : items){
            System.out.println();
            System.out.println("#" + count);
            System.out.println("Loan Item Type: " + item.getType());
            System.out.println(item.getName() + " - published by " + item.getPublisher() + " - loaned to " + item.getloanedTo() + " - due on " + item.getDueDate() + " (" + item.getDueStatus() +  ") -" + item.getExtraInfo());
            count++;
        }
    }

    /**
     * Reads a string from the user.
     *
     * @param prompt Message displayed to the user.
     * @return The entered string.
     */
    private String readString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Reads and validates an integer from the user.
     *
     * @param prompt Message displayed to the user.
     * @return A valid integer.
     */
    private int readInt(String prompt){
        while(true){
            System.out.println(prompt);
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("Error: enter a valid integer ");
            }
        }
    }

    /**
     * Reads and validates a date from the user.
     *
     * @return A valid LocalDate.
     */
    private LocalDate readDate(){
        while(true){
            int year = readInt("Enter the year of the due date:  ");
            int month = readInt("Enter the onth of the due date: ");
            int day = readInt("Enter the day of the due date: ");
            try{
                return LocalDate.of(year, month, day);
            }catch(Exception e){
                System.out.println("Error: this date does not exist ");
            }
        }
    }

    /**
     * Reads and validates a loan item type.
     *
     * @return 'b', 'a', or 'v'.
     */
    private char readItemType(){
        while(true){
            System.out.println("Enter the type of loan item to add (b: book, a: audio, v: video): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equals("b") || input.equals("a") || input.equals("v")){
                return input.charAt(0);
            }
            System.out.println("Error: type must be b/a/v ");
        }
    }

    /**
     * Reads and validates a page count.
     *
     * @return A non-negative page count.
     */
    private int readPages(){
        while(true){
            int pages = readInt("Enter the number of pages: ");
            if(pages >=0){
                return pages;
            }
            System.out.println("Error: number of pages cannot be negative ");
        }
    }

    /**
     * Reads and validates an audio duration.
     *
     * @return A Duration object representing the audio length.
     */
    private Duration readAudioLength(){
        while(true){
            int hours = readInt("Enter the number of hours of the audio: ");
            int minutes = readInt("Enter the number of minutes of the audio: ");
            int seconds = readInt("Enter the number of seconds of the audio: ");
            if(hours < 0){
                System.out.println("Error: hours cannot be negative ");
                continue;
            }
            if(minutes < 0){
                System.out.println("Error: minutes cannot be negative ");
                continue;
            }
            if(seconds < 0){
                System.out.println("Error: seconds cannot be negative ");
                continue;
            }
            return Duration.ofHours((hours)).plusMinutes(minutes).plusSeconds(seconds);
        }
    }

    /**
     * Reads and validates a video genre.
     *
     * @return The entered genre.
     */
    private String readGenre(){
        while(true){
            System.out.println("Enter the genre (if unknown type \"unknown\"): ");

            String genre = scanner.nextLine().trim();
            if(!genre.isEmpty()){
                return genre;
            }
            System.out.println("Error: genre must not be empty ");
        }
    }

    /**
     * Prompts the user for information and adds
     * a new loan item to the manager.
     */
    private void addItem() {
        String name = readString("Enter the name of the loan item: ");
        LocalDate dueDate = readDate();
        String publisher = readString("Enter the publisher of the loan item: ");
        String loanedTo = readString("Enter the name to which the item is loaned: ");
        char type = readItemType();
        loanItem item;

        if (type == 'b') {
            int pages = readPages();
            item = loanItemFactory.createBook(name,dueDate,publisher,loanedTo,pages);
        } else if (type == 'a') {
            Duration length = readAudioLength();
            item = loanItemFactory.createAudio(name,dueDate,publisher,loanedTo,length);
        } else {
            String genre = readGenre();

            item = loanItemFactory.createVideo(name,dueDate,publisher,loanedTo,genre);
        }

        manager.addItem(item);

        System.out.println(name + " has been added to the list.");
    }

    /**
     * Reads and validates the item number to remove.
     *
     * @return A valid item number or 0 to cancel.
     */
    private int readRemovalChoice() {
        while (true) {
            int choice = readInt("Enter the item number you want to remove (0 to cancel): ");
            if (choice >= 0 && choice <= manager.getSize()) {
                return choice;
            }
            System.out.println("Error: invalid item number.");
        }
    }

    /**
     * Removes a selected loan item from the manager
     */
    private void removeItem() {
        if (manager.getSize() == 0) {
            System.out.println("No items to remove.");
            return;
        }
        displayItems(manager.getAllItems());
        int choice =readRemovalChoice();
        if (choice == 0) {
            return;
        }
        loanItem removed = manager.removeItem(choice - 1);
        System.out.println(removed.getName() + " has been removed from the list.");
    }

    /**
     * Reads and validates the type of item to list
     * @return The selected item type
     */
    private String readTypeForListing() {
        while (true) {
            System.out.print("Enter the type of loan item to list (b: book, a: audio, v: video): ");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "b":
                    return "Book";
                case "a":
                    return "Audio";
                case "v":
                    return "Video";
            }
            System.out.println("Error: type must be b/a/v.");
        }
    }

    /**
     * Displays all loan items of a specified type
     */
    private void listItemsByType() {
        String type = readTypeForListing();
        displayItems(manager.getItemsByType(type));
    }

    /**
     * Starts the user interface and processes menu commands
     * until the user exits the application
     */
    public void start(){
        boolean running = true;

        while(running){
            displayMenu();
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    displayItems(manager.getAllItems());
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    displayItems(manager.getOverDueItems());
                    break;
                case 5:
                    displayItems(manager.getUpcomingItems());
                    break;
                case 6:
                    listItemsByType();
                    break;
                case 7:
                    manager.saveToFile();
                    running = false;
                    break;
            }
        }
        System.out.println();
        System.out.println("Thanks for using Loan Items Tracker.");
    }
}
