package language.spi;

/**
 * SPI demo implement class
 *
 * @author igaozp
 */
public class DatabaseSearch implements Search {
    @Override
    public String searchDoc(String keyword) {
        return "Database Search";
    }
}
