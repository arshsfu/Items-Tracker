package cmpt213.assignment.loanitemstracker.model;

import java.time.LocalDate;
import java.time.Duration;

/**
 * Represents an audio loan item.
 * Stores the duration of the audio recording in addition
 * to the common loan item information inherited from loanItem.
 */
public class Audio extends loanItem{
    private Duration length;

    /**
     * Constructs an Audio object.
     *
     * @param name Name of the audio item.
     * @param duDate Due date of the audio item.
     * @param publisher Publisher of the audio item.
     * @param loanedTo Person the audio item is loaned to.
     * @param length Duration of the audio item.
     */
    public Audio(String name, LocalDate duDate, String publisher, String loanedTo, Duration length){
        super(name, duDate, publisher, loanedTo);
        this.length = length;
        setItemType("audio");
    }

    /**
     * Gets the duration of the audio item.
     *
     * @return The audio duration.
     */
    public Duration getlength(){
        return length;
    }

    /**
     * Gets the type of loan item.
     *
     * @return "Audio"
     */
    @Override
    public String getType(){
        return "Audio";
    }

    /**
     * Gets audio-specific information for display.
     *
     * @return A formatted string describing the audio duration.
     */
    @Override
    public String getExtraInfo(){
        long hours = length.toHours();
        long minutes = length.toMinutes() % 60;
        long seconds = length.toSeconds() %60;
        return hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s) long";
    }
}
