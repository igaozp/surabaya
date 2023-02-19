package xyz.andornot.structural.decorators;

import java.io.*;

public class FileDataSource implements DataSource {
    private final String name;

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        var path = FileDataSource.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        var file = new File(path + name);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        var path = FileDataSource.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        var file = new File(path + name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assert buffer != null;
        return new String(buffer);
    }
}
