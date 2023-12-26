package lang.concurrent.thread.threadlocal;

public class SimpleThreadLocalExample {
    public static ThreadLocal<String> CONTEXT = ThreadLocal.withInitial(() -> null);
    public static InheritableThreadLocal<String> INHERITABLE_CONTEXT = new InheritableThreadLocal<>();

    static void insideParentThread() {
        System.out.println(STR."ThreadLocal Value in insideParentThread(): \{CONTEXT.get()}");
        System.out.println(STR."InheritableThreadLocal Value in insideParentThread(): \{INHERITABLE_CONTEXT.get()}");
    }

    static void insideChildThread() {
        System.out.println(STR."ThreadLocal Value in insideChildThread(): \{CONTEXT.get()}");
        System.out.println(STR."InheritableThreadLocal Value in insideChildThread(): \{INHERITABLE_CONTEXT.get()}");
    }

    public static void main(String[] args) {
        Thread parentThread = new Thread(() -> {
            CONTEXT.set("TestValue");
            INHERITABLE_CONTEXT.set("TestValue");
            insideParentThread();

            var childThread = new Thread(SimpleThreadLocalExample::insideChildThread);
            childThread.start();
            insideParentThread();
        });

        parentThread.start();
    }
}
