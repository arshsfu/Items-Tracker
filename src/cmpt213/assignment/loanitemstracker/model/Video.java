package cmpt213.assignment.loanitemstracker.model;

import java.time.LocalDate;

/**
 * Represents a video loan item.
 * Stores the genre of the video in addition to the
 * common loan item information inherited from loanItem.
 */
public class Video extends loanItem{
    private String genre;

    /**
     * Constructs a Video object.
     *
     * @param name Name of the video.
     * @param duDate Due date of the video.
     * @param publisher Publisher of the video.
     * @param loanedTo Person the video is loaned to.
     * @param genre Genre of the video.
     */
    public Video(String name, LocalDate duDate, String publisher, String loanedTo, String genre){
        super(name, duDate, publisher, loanedTo);
        setItemType("video");
        this.genre = genre;
    }

    /**
     * Gets the genre of the video.
     *
     * @return The video genre.
     */
    public String getGenre(){
        return genre;
    }

    /**
     * Gets the type of loan item.
     *
     * @return "Video"
     */
    @Override
    public String getType(){
        return "Video";
    }

    /**
     * Gets video-specific information for display.
     *
     * @return A string containing the video genre.
     */
    @Override
    public String getExtraInfo(){
        return genre + " genre";
    }
}
