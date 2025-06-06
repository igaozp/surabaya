package lang.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * Compare two files content by memory-mapped file
 *
 * @author igaozp
 * @since 2021/8/23
 */
public class RandomAccessFileExercise {
    public static void main(String[] args) throws IOException {
        var firstPath = Files.createTempFile("file1Test", ".txt");
        var secondPath = Files.createTempFile("file2Test", ".txt");

        Files.writeString(firstPath, String.format("testing line 1%sline 2", System.lineSeparator()));
        Files.writeString(secondPath, String.format("testing line 1%sline 2", System.lineSeparator()));

        try (var randomAccessFile1 = new RandomAccessFile(firstPath.toFile(), "r");
             var randomAccessFile2 = new RandomAccessFile(secondPath.toFile(), "r")) {

            var firstChannel = randomAccessFile1.getChannel();
            var secondChannel = randomAccessFile2.getChannel();
            if (firstChannel.size() != secondChannel.size()) {
                System.out.println("files size mismatch");
            }
            var size = firstChannel.size();
            var firstBuffer = firstChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            var secondBuffer = secondChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size);

            System.out.println(firstBuffer.equals(secondBuffer));
        }
    }
}
