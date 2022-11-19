package xyz.andornot.creational.factorymethod;

/**
 * See <a href="https://refactoringguru.cn/design-patterns/factory-method">Factory Method</a>
 *
 * @author igaozp
 * @since 2022/11/19
 */
public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("os.name").contains("Windows")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
