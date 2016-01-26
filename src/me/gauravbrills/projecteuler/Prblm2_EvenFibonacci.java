/*
 * Copyright (c) 2016. Gaurav Rawat  (https://github.com/gauravbrills)
 * Do contact owner before editing and using the code or raise a pull request if want to contribute to the same .
 *
 *  Cheers!
 *  Gaurav
 */

package me.gauravbrills.projecteuler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Solution to problem Even Fibonacci numbers https://projecteuler.net/problem=2 from project Euler
 */
public class Prblm2_EvenFibonacci {

    static Map<Integer, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        dict.put(0, 0);
        ExecutorService executors = Executors.newFixedThreadPool(1);
        Future<BigInteger> future1 = executors.submit(Prblm2_EvenFibonacci::getEvenFibonacciSum);
        Future<Integer> future2 = executors.submit(Prblm2_EvenFibonacci::getFibonacciMemoizedSum);
        System.out.printf("Result Simple call => %d \n", future1.get());
        System.out.printf("Result Memoized call => %d ", future2.get());
        executors.shutdown();
    }

    private static BigInteger getEvenFibonacciSum() {
        BigInteger curr = BigInteger.valueOf(2L);
        BigInteger result = BigInteger.ZERO;
        BigInteger start = BigInteger.ONE;
        BigInteger temp;
        while (curr.compareTo(new BigInteger("4000000")) != 1) {
            if (curr.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
                result = result.add(curr);
            }
            temp = curr;
            curr = start.add(curr);
            start = temp;
        }
        return result;
    }

    private static int getFibonacciMemoizedSum() {
        int curr = fibonacci(2);
        int n = 3;
        int result = 0;
        while (curr <= 4000000) {
            if (curr % 2 == 0) {
                result = result + curr;
            }
            curr = fibonacci(n++);
        }
        return result;
    }

    private static int fibonacci(int n) {
        int val;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            if (dict.get(n) == null) {
                val = fibonacci(n - 1) + fibonacci(n - 2);
                dict.put(n, val);
            } else {
                val = dict.get(n);
            }
        }
        return val;
    }

}
