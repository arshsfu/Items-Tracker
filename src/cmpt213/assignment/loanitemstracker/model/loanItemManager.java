package cmpt213.assignment.loanitemstracker.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.Duration;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.File;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import cmpt213.assignment.loanitemstracker.gson.extras.RuntimeTypeAdapterFactory;

/**
 * Manages the collection of loan items.
 * Responsible for adding, removing, searching,
 * filtering, saving, and loading loan items.
 */
public class loanItemManager{
    private ArrayList<loanItem> items;
    private static final String FILE_NAME = "./list.json";
    
    /**
     * Constructs an empty loan item manager.
     */
    public loanItemManager(){
        items = new ArrayList<>();
    }

    /**
     * Adds a loan item to the collection and
     * maintains items in sorted order.
     *
     * @param item The loan item to add.
     */
    public void addItem(loanItem item){
        items.add(item);
        Collections.sort(items);
    }

    /**
     * Removes a loan item from the collection.
     *
     * @param index Index of the item to remove.
     * @return The removed loan item.
     */
    public loanItem removeItem(int index){
        return items.remove(index);
    }
    /**
     * Gets the number of loan items currently stored.
     *
     * @return The number of loan items.
     */
    public int getSize(){
        return items.size();
    }

    /**
     * Gets all loan items.
     *
     * @return A list containing all loan items.
     */
    public List<loanItem> getAllItems(){
        return items;
    }

    /**
     * Retrieves all overdue loan items.
     *
     * @return A list containing overdue loan items.
     */
    public List<loanItem> getOverDueItems(){
        ArrayList<loanItem> results = new ArrayList<>();
        for(loanItem item : items){
            if(item.getDueDate().isBefore(LocalDate.now())){
                results.add(item);
            }
        }
        return results;
    }

    /**
     * Retrieves all loan items that are due today
     * or in the future.
     *
     * @return A list containing upcoming loan items.
     */
    public List<loanItem> getUpcomingItems(){
        ArrayList<loanItem> results = new ArrayList<>();
        for(loanItem item : items){
            if(!item.getDueDate().isBefore(LocalDate.now())){
                results.add(item);
            }
        }
        return results;
    }

    /**
     * Retrieves all loan items of a specified type.
     *
     * @param type The type to search for.
     * @return A list of matching loan items.
     */
    public List<loanItem> getItemsByType(String type){
        ArrayList<loanItem> results = new ArrayList<>();
        for(loanItem item : items){
            if(item.getType().equalsIgnoreCase(type)){
                results.add(item);
            }
        }
        return results;
    }

    /**
     * Creates and configures a Gson instance
     * capable of serializing and deserializing
     * loan item subclasses, LocalDate, and Duration.
     *
     * @return A configured Gson object.
     */
    private Gson createGson() {
        RuntimeTypeAdapterFactory<loanItem> loanItemAdapter = RuntimeTypeAdapterFactory.of(loanItem.class, "itemType").registerSubtype(Book.class, "book").registerSubtype(Audio.class, "audio").registerSubtype(Video.class, "video");
        return new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).registerTypeAdapter(Duration.class,new DurationAdapter()).registerTypeAdapterFactory(loanItemAdapter).setPrettyPrinting().create();
    }

    /**
     * Saves all loan items to the JSON file
     */
    public void saveToFile(){
        Gson  gson = createGson();
        try(FileWriter writer = new FileWriter(FILE_NAME)){
            gson.toJson(items, writer);
            System.out.println("Saving the list to "+ FILE_NAME + "...");

        }catch(IOException e){
            System.out.println("Error saving file ");
        }
    }

    /**
     * Loads loan items from the JSON file if it exists
     * Restores all saved loan items into memory
     */
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        Gson gson = createGson();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<ArrayList<loanItem>>() {}.getType();
            ArrayList<loanItem> loadedItems = gson.fromJson(reader, listType);

            if (loadedItems != null) {
                items.clear();
                items.addAll(loadedItems);
                Collections.sort(items);
            }
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}
