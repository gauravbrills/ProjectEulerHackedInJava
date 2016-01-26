/*
 * Copyright (c) 2016. Gaurav Rawat  (https://github.com/gauravbrills)
 * Do contact owner before editing and using the code or raise a pull request if want to contribute to the same .
 *
 *  Cheers!
 *  Gaurav
 */

package me.gauravbrills.projecteuler;

/**
 * Created by grawat on 1/26/2016.
 */
public class Prblm4_LargestPalindrome {
    public static void main(String[] args) {
        System.out.printf("\n Largest Palindrome of digits %d is %d ", 3, (int) findLargestPalindrome(3));
    }

    private static double findLargestPalindrome(double digits) {
        int palindrome = 0;
        int product;
        int min = new Double(Math.pow(10, digits - 1)).intValue();
        int max = new Double(Math.pow(10, digits)).intValue() - 1;
        for (int i = min; i <= max; i++) {
            for (int j = min; j <= max; j++) {
                product = i * j;
                if (product > palindrome && isPalindrome(product)) {
                    palindrome = product;
                }
            }
        }
        return palindrome;
    }

    private static boolean isPalindrome(int n) {
        int r, sum = 0, temp = n;
        while (n > 0) {
            r = n % 10;  //getting remainder
            sum = (sum * 10) + r;
            n = n / 10;
        }
        return temp == sum;
    }
}
