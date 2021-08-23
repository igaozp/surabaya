package language;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Compare two files content by memory-mapped file
 *
 * @author igaozp
 * @since 2021/8/23
 */
public class RandomAccessFileExercise {
    public static void main(String[] args) throws IOException {
        Path firstPath = Files.createTempFile("file1Test", ".txt");
        Path secondPath = Files.createTempFile("file2Test", ".txt");

        Files.writeString(firstPath, "testing line 1" + System.lineSeparator() + "line 2");
        Files.writeString(secondPath, "testing line 1" + System.lineSeparator() + "line 2");

        try (RandomAccessFile randomAccessFile1 = new RandomAccessFile(firstPath.toFile(), "r");
             RandomAccessFile randomAccessFile2 = new RandomAccessFile(secondPath.toFile(), "r")) {

            FileChannel firstChannel = randomAccessFile1.getChannel();
            FileChannel secondChannel = randomAccessFile2.getChannel();
            if (firstChannel.size() != secondChannel.size()) {
                System.out.println("files size mismatch");
            }
            long size = firstChannel.size();
            MappedByteBuffer firstBuffer = firstChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            MappedByteBuffer secondBuffer = secondChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size);

            System.out.println(firstBuffer.equals(secondBuffer));
        }
    }
}
