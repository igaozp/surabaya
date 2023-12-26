package lang.concurrent.scopedvalue;

public class ForkScopedValueExample {
    private static final ScopedValue<String> CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.runWhere(CONTEXT, "Test Value", () -> {
            System.out.println(STR."In parent thread start the scoped value is: \{CONTEXT.get()}");
            doSomething();
            System.out.println(STR."In parent thread end the scoped value is: \{CONTEXT.get()}");
        });
    }

    private static void doSomething() {
        System.out.println(STR."In doSomething() and parent scope: \{CONTEXT.get()}");

        ScopedValue.runWhere(CONTEXT, "Change Value", () -> {
            System.out.println(STR."In doSomething() and child scope: \{CONTEXT.get()}");
            doSomethingAgain();
        });
    }

    private static void doSomethingAgain() {
        System.out.println(STR."In doSomethingAgain() and child scope: \{CONTEXT.get()}");
    }
}
