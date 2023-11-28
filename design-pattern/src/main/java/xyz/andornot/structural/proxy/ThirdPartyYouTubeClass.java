package xyz.andornot.structural.proxy;

import java.util.HashMap;

public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    @Override
    public HashMap<String, Video> popularVideos() {
        connectToServer("https://www.youtube.com");
        return getRandomVideos();
    }

    @Override
    public Video getVideo(String videoId) {
        connectToServer("https://www.youtube.com/" + videoId);
        return getSomeVideo(videoId);
    }

    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min)));
    }

    private void experienceNetworkLatency() {
        var randomLatency = random(5, 10);
        for (var i = 0; i < randomLatency; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void connectToServer(String server) {
        System.out.println("Connecting to " + server + "...");
        experienceNetworkLatency();
        System.out.println("Connected!\n");
    }

    private HashMap<String, Video> getRandomVideos() {
        System.out.println("Downloading populars...");

        experienceNetworkLatency();
        HashMap<String, Video> map = new HashMap<>();
        map.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        map.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        map.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        map.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        map.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));

        System.out.println("Done!\n");
        return map;
    }

    private Video getSomeVideo(String videoId) {
        System.out.println("Downloading video...");

        experienceNetworkLatency();
        var video = new Video(videoId, "Some video title");

        System.out.println("Done!\n");
        return video;
    }
}
