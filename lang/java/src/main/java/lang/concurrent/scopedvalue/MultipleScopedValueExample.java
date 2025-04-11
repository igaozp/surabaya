package lang.concurrent.scopedvalue;

record ApplicationContext(String name, String ip) {
}

public class MultipleScopedValueExample {
    private static final ApplicationContext APP_CONTEXT = new ApplicationContext("Java", "127.0.0.1");
    private static final ScopedValue<ApplicationContext> CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(CONTEXT, APP_CONTEXT).run(() -> System.out.println("ApplicationContext scoped value is: " + CONTEXT.get()));
    }
}
