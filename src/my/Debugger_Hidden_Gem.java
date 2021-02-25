package my;

import my.refresh.Somewhere;
import org.jetbrains.annotations.Debug;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

























/**
 * <div style="font-size: 30;background:#f9f9f9">
 * <div style="font-size: 45;">Debugger: Hidden Gem &#128142</div><br>
 * Egor Ushakov<br>
 * 25 Feb 2021
 * </div>
 */
public class Debugger_Hidden_Gem {


















    /**
     * <div style="font-size: 30;background:#f9f9f9">
     * <div style="font-size: 35;">Why debug?</div>
     * <br><ul>
     * <li>Find and fix bugs!</li>
     * <li>Analyze the code</li>
     * <li>Add more logging on the fly</li>
     * <li>Change behavior on the fly</li>
     * <li>Analyze memory issues</li>
     * <li>And much more</li>
     * </ul>
     * </div>
     */
    int alwaysDebug;





















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Lambdas (since 2014)</div>
     * <br>
     * <ul>
     * <li>&#128142 Breakpoints in lambdas</li>
     * <li>&#128142 Run to cursor in lambda</li>
     * <li>&#128142 Step into lambdas</li>
     * <li>&#128577 Lambda class/method name is not specified</li>
     * </ul>
     */
    public static class Lambdas {
        public static void main(String[] args) {
            Object[] res = Stream.of(1, 2, 3, 4, 5, 6).filter(i -> i % 2 == 0).filter(x -> x > 3).peek(k -> {}).toArray();
            System.out.println(Arrays.toString(res));
        }
    }















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">&#128142 Stream debugger</div>
     * <br><ul>
     * <li>also kotlin sequences</li>
     * </ul>
     */
    public static class StreamDebugger {
        public static void main(String[] args) {
            int[] result = IntStream.of(10, 87, 97, 43, 121, 20)
                    .flatMap(StreamDebugger::factorize)
                    .distinct()
                    .sorted()
                    .toArray();
            System.out.println(Arrays.toString(result));
        }

        private static IntStream factorize(int value) {
            List<Integer> factors = new ArrayList<>();
            for (int i = 2; i <= value; i++) {
                while (value % i == 0) {
                    factors.add(i);
                    value /= i;
                }
            }
            return factors.stream().mapToInt(Integer::intValue);
        }
    }




















    /**
     * <div style="font-size: 30;background:#f9f9f9">
     * <div style="font-size: 35;">Method breakpoints</div><br>
     * <ul>
     * <li>Good for interface methods</li>
     * <li>&#128515 Wildcard (any method in the project, class, in main, all methods)</li>
     * </ul>
     * </div>
     */
    public static class MethodBpt {
        public static void main(String[] args) {
            BaseInterface o = Somewhere.getObject();
//        System.out.println(o.foo());
            System.out.println(o.boo());
        }

        public interface BaseInterface {
            String foo(); // unused?
            String boo();
        }
    }

















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Breakpoint filters</div>
     * <br><ul>
     * <li>&#128142 intentions</li>
     * <li>&#128142 Caller filter</li>
     * </ul>
     */
    public static class Filters {
        public static void main(String[] args) {
            Filters w = new Filters();
            w.warmup();
            w.realWork();
        }

        private void warmup() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }
        private void realWork() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }

        private void work() {
            int a = 5; // do something
        }
    }



















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Values</div>
     * <br><ul>
     * <li>&#128142 Inline debugger</li>
     * <li>Tooltips</li>
     * <li>Quick evaluate</li>
     * <li>&#128525 Alt+click</li>
     * </ul>
     */
    static class Values {
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                if (i < 10 && check(i)) {
                    System.out.println(i);
                }
            }
        }

        static boolean check(int i) {
            return i % 2 == 0;
        }
    }




    
    
    






    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Evaluate dialog</div>
     * <br><ul>
     * <li>Modified expressions from the code</li>
     * <li>Prototyping</li>
     * <li>Check how to use an api</li>
     * <li>&#128142 code fragment</li>
     * </ul>
     */
    static class Evaluate {
        public static void main(String[] args) {
            System.out.println("OS name is " + System.getProperty("os.name"));
        }
    }






    
    
    







    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Non-suspending breakpoints?</div>
     * <br><ul>
     * <li>Log expression value, message, stack</li>
     * <li>&#128142 DND+copy</li>
     * <li>&#128142 Shift-click</li>
     * <li>&#128142 Change behavior</li>
     * </ul>
     */
    public static class Cache {
        static Cache instance;
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                System.out.println(getInstance(i));
            }
        }

        static Cache getInstance(int i) {
            if (instance == null) {
                instance = new Cache();
            }
            return instance;
        }
    }

















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Advanced stepping</div>
     * <br><ul>
     * <li>Drop frame</li>
     * <li>&#128142 Force return</li>
     * <li>&#128142 Throw exception</li>
     * </ul>
     */
    public static class Stepping {
        public static void main(String[] args) throws IOException {
            for (int i = 0; i < 100; i++) {
                try {
                    if (filter(i)) {
                        process(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private static boolean filter(int read) {
            return read != '\n' && read != 'a';
        }

        private static void process(int arg) {
            int res = arg * 2;
            res += 4;
            if (res > 1) {
                res = Math.max(10, res);
            }
            if (Math.max(res, 90) % 2 == 0) {
                System.out.println("!");
            }
        }
    }




















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Mark object</div>
     * <br><ul>
     * <li>To highlight in different contexts</li>
     * <li>&#128142 To access anywhere with m_DebugLabel</li>
     * </ul>
     */
    public static class Mark {
        public static void main(String[] args) {
            Mark m1 = new Mark(), m2 = new Mark();
            process(m2);
        }

        private static void process(Mark m) {
            System.out.println(m);
        }

        @Override
        public String toString() {
            return "Mark";
        }
    }
















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">&#128142 Memory view</div>
     * <br><ul>
     * <li>Find any object in the heap</li>
     * <li>Show referring objects</li>
     * <li>You can use mark object</li>
     * </ul>
     */
    public static class Memory {
        public static void main(String[] args) {
            Object x = new Memory(1), y = new Memory(2), z = new Memory(3);
            process(x);
        }

        private static void process(Object x) {
            System.out.println(x);
        }

        int v;
        public Memory(int v) {
            this.v = v;
        }
    }













    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Watches</div>
     * <br><ul>
     * <li>&#128142 Can use marked objects</li>
     * <li>&#128142 Class level watches</li>
     * <li>&#128142 Inline watches</li>
     * </ul>
     */
    public static class Watches {
        long millis = System.currentTimeMillis();

        public static void main(String[] args) throws IOException {
            String a = "a";
            Watches watches = new Watches();
            System.out.println(watches);
        }
    }


















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">&#128142 Async stack traces</div>
     * <br><ul>
     * <li>Shows the flow of the async execution</li>
     * <li>Presets for standard executors</li>
     * </ul>
     */
    public static class Async {
        public static void main(String[] args) throws InterruptedException {
            CompletableFuture.runAsync(Async::async1);
            Thread.sleep(10000);
        }

        private static void async1() {
            CompletableFuture.runAsync(Async::async2);
        }

        private static void async2() {
            System.out.println("Hello");
        }
    }









    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Async stack traces</div>
     * <br><ul>
     * <li>Custom setup with annotations</li>
     * </ul>
     */
    public static class AsyncSchedulerExample {
        private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        public static void main(String[] args) throws InterruptedException {
            new Thread(() -> {
                try {
                    while (true) {
                        process(queue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            schedule(1);
            schedule(2);
            schedule(3);
        }

        private static void schedule(@org.jetbrains.annotations.Async.Schedule Integer i)
                throws InterruptedException {
            System.out.println("Scheduling " + i);
            queue.put(i);
        }

        private static void process(@org.jetbrains.annotations.Async.Execute Integer i) {
            System.out.println("Processing " + i);
        }
    }











    /**
     * <div style="font-size: 30;background:#f9f9f9">
     * <div style="font-size: 35;">Renderers</div>
     * <br><ul>
     * <li>Custom children</li>
     * <li>Share with annotations</li>
     * </ul>
     */
    @Debug.Renderer(text = "\"size = \" + toArray().length", childrenArray = "toArray()")
    public static class FastLinkedList<T> {
        final T value;
        FastLinkedList<T> next;

        public static void main(String[] args) {
            FastLinkedList<String> list = new FastLinkedList<>("a").add("b").add("c").add("d").add("e");
            System.out.println(list);
        }

        public FastLinkedList(T value) {
            this.value = value;
        }

        FastLinkedList<T> add(T value) {
            Stream.iterate(this, n -> n != null, n -> n.next).reduce((a, b) -> b).get().next = new FastLinkedList<>(value);
            return this;
        }

        Object[] toArray() {
            return Stream.iterate(this, n1 -> n1 != null, n1 -> n1.next).map(n -> n.value).toArray();
        }
    }





















    /**
     * <div style="font-size: 30;background:#f9f9f9"><div style="font-size: 35;">Thank you</div>
     * <ul><br>
     * <li>&#128142 Debug more</li>
     * </ul>
     */

    int questions;
}






