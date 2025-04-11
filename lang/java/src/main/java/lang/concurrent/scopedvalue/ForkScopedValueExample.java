package lang.concurrent.scopedvalue;

public class ForkScopedValueExample {
    private static final ScopedValue<String> CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(CONTEXT, "Test Value").run(() -> {
            System.out.println("In parent thread start the scoped value is: " + CONTEXT.get());
            doSomething();
            System.out.println("In parent thread end the scoped value is: " + CONTEXT.get());
        });
    }

    private static void doSomething() {
        System.out.println("In doSomething() and parent scope: " + CONTEXT.get());

        ScopedValue.where(CONTEXT, "Change Value").run(() -> {
            System.out.println("In doSomething() and child scope: " + CONTEXT.get());
            doSomethingAgain();
        });
    }

    private static void doSomethingAgain() {
        System.out.println("In doSomethingAgain() and child scope: " + CONTEXT.get());
    }
}
