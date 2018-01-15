package com.kevin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// A ForkJoinTask (via RecursiveAction) that transforms
// the elements in an array of doubles into their square roots.
class SqrtTransform extends RecursiveAction {
    // The threshold value is arbitrarily set at 1,000 in this example.
    // In real-world code, its optimal value can be determined by
    // profiling and experimentation.
    final int seqThreshold = 1000;

    // Array to be accessed.
    double[] data;

    // Determines what part of data to process.
    int start, end;

    public SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    // This is the method in which parallel computation will occur.
    @Override
    protected void compute() {
        // If number of elements is below the sequential threshold,
        // then process sequentially.
        if ((end - start) < seqThreshold) {
            //Transorm each element into its sqrt
            for (int i = start; i < end; i++) data[i] = Math.sqrt(data[i]);
        } else {
            // Otherwise, continue to break the data into smaller pieces.

            //Find the mid point
            int middle = (start + end) / 2;

            // Invoke new tasks, using the subdivided data.
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end));
        }
    }
}

public class App {
    public static void main(String[] args) {
        // These variables are used to time the task.
        long beginT, endT;

        //create a task pool
        ForkJoinPool fjp = new ForkJoinPool(3);

        double[] nums = new double[1000];

        // Give nums some values.
        for (int i = 0; i < nums.length; i++) nums[i] = (double) i;

        System.out.println("A portion of the original sequence");

        for (int i = 0; i < 10; i++) System.out.print(nums[i] + " ");
        System.out.println("\n");

        SqrtTransform sq = new SqrtTransform(nums, 0, nums.length);

        //starting the timing
        beginT = System.nanoTime();

        //start the main forkJoinTask
        fjp.invoke(sq);

        //ending th etiming
        endT = System.nanoTime();

        System.out.println("A portion of the transformed sequence" + " (to four decimal places):");
        for (int i = 0; i<10; i++) System.out.format("%.4f", nums[i]);
        System.out.println();

//        Runtime r = Runtime.getRuntime();
//        System.out.println(r.availableProcessors());
        System.out.println("Elapsed time: " + (endT - beginT) + " ns");
    }
}
