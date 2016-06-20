package exempel;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class StopWatchMeasures {
   private final static int MAX_REP = 20;

    public static void main(String[] args) {
        long max = 0;
        long min = 10000;
        long total = 0;
        long[] times = new long[MAX_REP];
        /* For Windows native command use: "cmd /C <command>". */
        if (args.length == 0) {
            System.err.printf("Usage: java %s <command> <parameter> ...",
                              StopWatchMeasures.class.getName());
            System.exit(1);
        }
        for (int i = 0; i < MAX_REP; i++) {
            ProcessBuilder proc = new ProcessBuilder();
            proc.command(args);
            proc.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            proc.redirectInput(ProcessBuilder.Redirect.INHERIT);
            proc.redirectError(ProcessBuilder.Redirect.INHERIT);
            proc.directory(new File("."));
            Instant start = Instant.now();
            try {
                proc.start().waitFor();
            } catch (IOException ioe) {
                System.err.println("Unexecutable commandline. " + ioe.getMessage());
                System.exit(2);
            } catch (InterruptedException ie) {
                System.err.println("Command interrupted.");
                System.err.println(ie.getMessage());
                System.exit(3);
            }
            Instant stop = Instant.now();
            long time = Duration.between(start, stop).toMillis();
            times[i] = time;
            if (time < min)
                min = time;
            if (time > max)
                max = time;
            total = total + time;
        }
        Arrays.sort(times);
        long median = times[times.length / 2];
        long mean = total / MAX_REP;
        double accumulation = 0;
        for (long num : times)
            accumulation += Math.pow((num - mean) , 2);
        double standard = Math.sqrt(accumulation / (MAX_REP - 1));
        /* Time includes calling environment and process overhead. */
        System.out.printf("For %d repetitions", MAX_REP);
        System.out.printf("\nMax Execution time: %sms\n", max);
        System.out.printf("\nMin Execution time: %sms\n", min);
        System.out.printf("\nMean Execution time: %sms\n", mean );
        System.out.printf("\nMedian Execution time: %sms\n", median);
        System.out.printf("\nStandard Deviation : %.3fms\n", standard);
    }
}
