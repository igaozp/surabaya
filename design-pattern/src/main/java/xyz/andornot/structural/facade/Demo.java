package xyz.andornot.structural.facade;

import java.io.File;

public class Demo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4 = converter.convertVideo("xxxx.ogg", "mp4");
    }
}
