package exercise.coding.kiwee.ai.prompt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Nested
	@DisplayName("Fibonacci Tests")
	class FibonacciTest {

		// Test cases for the Fibonacci function
		@Test
		@DisplayName("Fibonacci of 0")
		void testFibonacciZero() {
			assertEquals(0, SimpleMath.fibonacci(0));
		}

		@Test
		@DisplayName("Fibonacci of 1")
		void testFibonacciOne() {
			assertEquals(1, SimpleMath.fibonacci(1));
		}

		@Test
		@DisplayName("Fibonacci of small positive integers")
		void testFibonacciSmallNumbers() {
			assertEquals(1, SimpleMath.fibonacci(2));
			assertEquals(2, SimpleMath.fibonacci(3));
			assertEquals(3, SimpleMath.fibonacci(4));
			assertEquals(5, SimpleMath.fibonacci(5));
			assertEquals(8, SimpleMath.fibonacci(6));
		}

		@Test
		@DisplayName("Fibonacci of larger numbers")
		void testFibonacciLargerNumbers() {
			assertEquals(55, SimpleMath.fibonacci(10));
			assertEquals(6765, SimpleMath.fibonacci(20));
			assertEquals(832040, SimpleMath.fibonacci(30));
			assertEquals(9227465, SimpleMath.fibonacci(35));
		}

		@Test
		@DisplayName("Fibonacci with negative input")
		void testFibonacciNegativeInput() {
			assertEquals(-1, SimpleMath.fibonacci(-1));
		}

		@Test
		@DisplayName("Fibonacci of large index")
		void testFibonacciLargeIndex() {
			assertEquals(1134903170, SimpleMath.fibonacci(45)); // Testing with a larger Fibonacci number
		}
	}

	@Nested
	@DisplayName("Prime Tests")
	class PrimeTest {

		// Test cases for prime method
		@Test
		@DisplayName("Largest prime below a small number")
		void testPrimeBelowSmallNumber() {
			assertEquals(3, SimpleMath.prime(5));
		}

		@Test
		@DisplayName("Largest prime below a prime number")
		void testPrimeBelowPrimeNumber() {
			assertEquals(5, SimpleMath.prime(7));
		}

		@Test
		@DisplayName("Largest prime below a non-prime number")
		void testPrimeBelowNonPrimeNumber() {
			assertEquals(11, SimpleMath.prime(13));
		}

		@Test
		@DisplayName("Largest prime below a large number")
		void testPrimeBelowLargeNumber() {
			assertEquals(97, SimpleMath.prime(100));
		}

		@Test
		@DisplayName("Largest prime below 2")
		void testPrimeBelowTwo() {
			assertEquals(-1, SimpleMath.prime(2));
		}

		@Test
		@DisplayName("Largest prime below 1")
		void testPrimeBelowOne() {
			assertEquals(-1, SimpleMath.prime(1));
		}

		@Test
		@DisplayName("Largest prime below 0")
		void testPrimeBelowZero() {
			assertEquals(-1, SimpleMath.prime(0));
		}

		@Test
		@DisplayName("Largest prime below a negative number")
		void testPrimeBelowNegativeNumber() {
			assertEquals(-1, SimpleMath.prime(-10));
		}

		@Test
		@DisplayName("Largest prime below a large prime number")
		void testPrimeBelowLargePrimeNumber() {
			assertEquals(101, SimpleMath.prime(103));
		}
	}

	@Nested
	@DisplayName("GCD Tests")
	class GCDTest {

		// Test cases for GCD
		@Test
		@DisplayName("GCD of two positive numbers")
		void testGcdPositiveNumbers() {
			assertEquals(6, SimpleMath.gcd(54, 24));
		}

		@Test
		@DisplayName("GCD of one positive and one negative number")
		void testGcdPositiveNegativeNumbers() {
			assertEquals(6, SimpleMath.gcd(-54, 24));
		}

		@Test
		@DisplayName("GCD of two negative numbers")
		void testGcdNegativeNumbers() {
			assertEquals(6, SimpleMath.gcd(-54, -24));
		}

		@Test
		@DisplayName("GCD when one number is zero")
		void testGcdWithZero() {
			assertEquals(24, SimpleMath.gcd(0, 24));
			assertEquals(54, SimpleMath.gcd(54, 0));
		}

		@Test
		@DisplayName("GCD when both numbers are zero")
		void testGcdBothZero() {
			assertEquals(0, SimpleMath.gcd(0, 0));
		}

		@Test
		@DisplayName("GCD of co-prime numbers")
		void testGcdCoPrimeNumbers() {
			assertEquals(1, SimpleMath.gcd(13, 17));
		}

		@Test
		@DisplayName("GCD of identical numbers")
		void testGcdIdenticalNumbers() {
			assertEquals(5, SimpleMath.gcd(5, 5));
		}
	}

	@Nested
	@DisplayName("LCM Tests")
	class LCMTest {

		// Test cases for LCM
		@Test
		@DisplayName("LCM of two positive numbers")
		void testLcmPositiveNumbers() {
			assertEquals(216, SimpleMath.lcm(54, 24));
		}

		@Test
		@DisplayName("LCM of one positive and one negative number")
		void testLcmPositiveNegativeNumbers() {
			assertEquals(216, SimpleMath.lcm(-54, 24));
		}

		@Test
		@DisplayName("LCM of two negative numbers")
		void testLcmNegativeNumbers() {
			assertEquals(216, SimpleMath.lcm(-54, -24));
		}

		@Test
		@DisplayName("LCM when one number is zero")
		void testLcmWithZero() {
			assertEquals(0, SimpleMath.lcm(0, 24));
			assertEquals(0, SimpleMath.lcm(54, 0));
		}

		@Test
		@DisplayName("LCM when both numbers are zero")
		void testLcmBothZero() {
			assertEquals(0, SimpleMath.lcm(0, 0));
		}

		@Test
		@DisplayName("LCM of co-prime numbers")
		void testLcmCoPrimeNumbers() {
			assertEquals(221, SimpleMath.lcm(13, 17));
		}

		@Test
		@DisplayName("LCM of identical numbers")
		void testLcmIdenticalNumbers() {
			assertEquals(5, SimpleMath.lcm(5, 5));
		}
	}
}