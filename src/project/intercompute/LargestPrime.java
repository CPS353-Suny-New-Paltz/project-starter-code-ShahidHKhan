package project.intercompute;

public class LargestPrime {

     //Returns the largest prime number less than or equal to n.
     
    public static int largestPrimeLeq(int n) {
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // no primes ≤ n
    }

     //Checks whether a number is prime.

    private static boolean isPrime(int num) {
        if (num < 2) {
              return false;
          }
        if (num == 2) {
              return true;
          }
        if (num % 2 == 0) {
              return false;
          }

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
