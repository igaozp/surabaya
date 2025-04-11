package lang.concurrent.scopedvalue;

public class ScopedValueBoundExample {
    private static final ScopedValue<String> CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(CONTEXT, "TestValue").run(() -> {
            // do something
            System.out.println("Inside bounded scope isBound() is: " + CONTEXT.isBound());
            System.out.println("Inside bounded scope the scoped value is: " + CONTEXT.orElse(null));
        });

        System.out.println("Outside bounded scope isBound() is: " + CONTEXT.isBound());
        System.out.println("Outside bounded scope the scoped value is: " + CONTEXT.orElse(null));
    }
}
