package my.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

@SuppressWarnings("ALL")
public class Somewhere {
    public static class Clazz1 implements my.Debugger_Hidden_Gem.MethodBpt.BaseInterface {
        @Override
        public String foo() {
            System.out.println("Called foo");
            return "Clazz1";
        }

        @Override
        public String boo() {
            return "Clazz1";
        }
    }

    public static class Clazz2 extends Clazz1 {
        @Override
        public String boo() {
            return "Clazz2";
        }
    }

    public static class Clazz3 extends Clazz2 {
        @Override
        public String foo() {
            System.out.println("Called foo");
            return "Clazz3";
        }
    }

    public static my.Debugger_Hidden_Gem.MethodBpt.BaseInterface getObject() {
        Clazz3 obj = new Clazz3();
        doSomething(obj);
        return obj;
    }

    private static void doSomething(Object obj) {
        try {
            obj.getClass().getMethod("foo").invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
