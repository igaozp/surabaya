package language.spi;

/**
 * SPI demo implement class
 *
 * @author igaozp
 */
public class FileSearch implements Search {
    @Override
    public String searchDoc(String keyword) {
        return "File Search";
    }
}
