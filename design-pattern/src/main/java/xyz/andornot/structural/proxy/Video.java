package xyz.andornot.structural.proxy;

public class Video {
    public final String id;
    public final String title;
    public final String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}
