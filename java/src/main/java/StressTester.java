
import java.time.Duration;
import java.time.Instant;

public class StressTester {

    private static boolean shouldStop = true;
    private static int numTests = 100;

    public static void main(String[] args) {
        int count = 1;
        while (true) {
            System.out.println("Test " + count++);
            APlusBData data = APlusBDataGenerate.generate();
            Instant startTime = Instant.now();
            Object naive = APlusB.naiveAlgorithm(data);
            Instant stopTimeN = Instant.now();
            Object implemented = APlusB.implementedAlgorithm(data);
            Instant stopTimeI = Instant.now();

            System.out.println("Naive: " + Duration.between(startTime, stopTimeN)
                    + ", implemented: " + Duration.between(stopTimeN, stopTimeI));

            if (!naive.equals(implemented)) {
                System.out.println("Test failed!");
                System.out.println("Solution: " + naive);
                System.out.println("Yours:    " + implemented);
                // debug if needed
                APlusB.implementedAlgorithm(data);
                break;
            }

            if (shouldStop && count > numTests) {
                System.out.println("Success!");
                break;
            }
        }
    }

}
