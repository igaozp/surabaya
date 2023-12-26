package lang.loom.scopedvalue;

public class ScopedValueBoundExample {
    private static final ScopedValue<String> CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.runWhere(CONTEXT, "TestValue", () -> {
            // do something
            System.out.println(STR."Inside bounded scope isBound() is: \{CONTEXT.isBound()}");
            System.out.println(STR."Inside bounded scope the scoped value is: \{CONTEXT.orElse(null)}");
        });

        System.out.println(STR."Outside bounded scope isBound() is: \{CONTEXT.isBound()}");
        System.out.println(STR."Outside bounded scope the scoped value is: \{CONTEXT.orElse(null)}");
    }
}
