package lang.loom.scopedvalue;

import java.util.concurrent.StructuredTaskScope;

public class SimpleScopedValueExample {
    private static final ScopedValue<String> CONTEXT = ScopedValue.newInstance();

    static void insideParentThread() {
        System.out.println(STR."ThreadLocal Value in insideParentThread(): \{CONTEXT.get()}");
    }

    static String insideChildThread() {
        System.out.println(STR."ThreadLocal Value in insideChildThread(): \{CONTEXT.get()}");
        return CONTEXT.get();
    }


    public static void main(String[] args) {
        ScopedValue.runWhere(CONTEXT, "TestValue", () -> {
            insideParentThread();

            try (var scope = new StructuredTaskScope<String>()) {
                scope.fork(SimpleScopedValueExample::insideChildThread);
                scope.fork(SimpleScopedValueExample::insideChildThread);

                try {
                    scope.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
