package xyz.andornot.structural.decorators;

public interface DataSource {
    void writeData(String data);

    String readData();
}
