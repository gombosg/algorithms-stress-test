
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class StressTesterReflect {

    private static Class testedClass = APlusB.class;
    private static Class dataClass = APlusBData.class;
    private static Class generator = APlusBDataGenerate.class;

    private static boolean shouldStop = true;
    private static int numTests = 100;

    public static void main(String[] args)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        int count = 1;
        while (true) {
            System.out.println("Test " + count++);
            Object data = runGenerate();
            Instant startTime = Instant.now();
            Object naive = runNaive(data);
            Instant stopTimeN = Instant.now();
            Object implemented = runImplemented(data);
            Instant stopTimeI = Instant.now();

            System.out.println("Naive: " + Duration.between(startTime, stopTimeN)
                    + ", implemented: " + Duration.between(stopTimeN, stopTimeI));

            if (!naive.equals(implemented)) {
                System.out.println("Test failed!");
                System.out.println("Data: " + data);
                System.out.println("Solution: " + naive);
                System.out.println("Yours:    " + implemented);
                // debug if needed
                runImplemented(data);
                break;
            }

            if (shouldStop && count > numTests) {
                System.out.println("Success!");
                break;
            }
        }
    }

    private static Object runGenerate()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method method = generator.getMethod("generate", null);
        return method.invoke(null);
    }

    private static Object runNaive(Object data)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method method = testedClass.getMethod("naiveAlgorithm", dataClass);
        return method.invoke(null, data);
    }

    private static Object runImplemented(Object data)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method method = testedClass.getMethod("implementedAlgorithm", dataClass);
        return method.invoke(null, data);
    }

}
