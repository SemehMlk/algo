package com.algorithmic.algo;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Override
	public void run(String... args) {
		// save a couple of customers

		log.info("-------------------------------");
		log.info("Here we are using commandLineRunner");
		int[] intArray = new int[] { 1, -11, 2, -2, 3, 4, 5, 6, 17, -80, 93, 10, 5, 12 };
		// clozeToZero(intArray);
		// clozeToZero2(intArray);
		// clozeToZero3(intArray);
		// clozeToZero4(intArray);
		opptimalChange(1031);
		log.info("-------------------------------");

	}

	public static int clozeToZero(int[] array) {

		if (array.length == 0) {
			return 0;
		} else {
			// verify if table contain 0
			if (!Arrays.stream(array).anyMatch(i -> i == 0)) {
				// push an element at the end of the table
				array = IntStream.concat(Arrays.stream(array), IntStream.of(0)).toArray();
			}
			// sort the array
			Arrays.sort(array);
			// index of an element in a **sorted** array
			int key = Arrays.binarySearch(array, 0);

			if (key == 0) {
				System.out.println("my element is: " + array[1] + " :)");
				return array[1];
			}
			int keyPrev = Arrays.binarySearch(array, 0) - 1;
			int keyNext = Arrays.binarySearch(array, 0) + 1;
			if (Math.abs(array[keyPrev]) < Math.abs(array[keyNext])) {
				System.out.println("my element is: " + array[keyPrev]);
				return array[keyPrev];
			} else {
				System.out.println("my element is: " + array[keyNext]);
				return array[keyNext];
			}
		}
	}

	public static int clozeToZero2(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int curr = 0;
		int near = array[0];
		Arrays.sort(array);
		// find the element nearest to zero
		for (int i = 0; i < array.length; i++) {
			curr = array[i] * array[i];
			if (curr <= (near * near)) {
				near = array[i];
				System.out.println("myval is: " + near);
			}
		}
		System.out.println(near);
		return 0;
	}

	// incompleted solution
	public static int clozeToZero3(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int closedToZero = 0;
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			// System.out.println(array[i]);
			if (Math.abs(array[i]) <= Math.abs(array[closedToZero]))
				closedToZero = i;
		}
		System.out.println(array[closedToZero]);
		return 0;
	}

	// internet solution loool
	public static int clozeToZero4(int[] array) {
		Arrays.stream(array).filter(i -> i != 0)
				.reduce((a, b) -> Math.abs(a) < Math.abs(b) ? a : (Math.abs(a) == Math.abs(b) ? Math.max(a, b) : b))
				.ifPresent(System.out::println);
		return 0;
	}

	// optimal change
	public static void opptimalChange(int nbr) {
		if (nbr < 4 && nbr != 2) {
			System.out.println("error");
		} else {
			int dv = nbr / 5;
			int rst = nbr % 5;
			if (rst % 2 == 0) {
				System.out.println(nbr / 10 + " pieces of 10 et " + (nbr % 10) / 5 + " pieces of 5 et " + (nbr % 5) / 2
						+ " pieces of 2");
			} else {
				dv -= 1;
				rst += 5;
				System.out.println((nbr-5) / 10 + " pieces of 10 et " + ((nbr-5) % 10) / 5 + " pieces of 5 et " + rst / 2
						+ " pieces of 2");
			}

		}
	}
}