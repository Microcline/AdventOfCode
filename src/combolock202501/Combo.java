package combolock202501;

import java.util.regex.Pattern;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Combo {
	public static void main(String[] args) throws FileNotFoundException {
		// Read input
		File file = new File("C:\\Workspace\\AdventOfCode\\src\\inputcombolock.txt");
		Scanner scanner = new Scanner(file);
		// Start at 50 because of the problem definition
		int comboNeedle = 50;
		int zeroNeedleCounter = 0;
		
		// Parse input: Ex. [LR][0-9]*
		while(scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (validInput(nextLine)) {
				comboNeedle = comboCalculator(nextLine, comboNeedle);
				if(comboNeedle == 0) {
					zeroNeedleCounter++;
				}
			}
		}
		System.out.println("Needle zero counts: " + zeroNeedleCounter);
	}
	
	private static int comboCalculator(String combo, int comboNeedle) {
		
		System.out.println("Currently pointing at: " + comboNeedle);
		
		// Assumes valid [LR][0-9]+ pattern
		int turnMagnitude = Integer.parseInt(combo.replaceAll("[\\D]", ""));
				
		if(combo.charAt(0) == 'L') {
			System.out.println("Turning the lock left " + turnMagnitude);
			comboNeedle = ((comboNeedle - turnMagnitude) % 100);
			if(comboNeedle < 0) {
				System.out.println("Out of range after a left turn: " + comboNeedle);
				comboNeedle += 100;
			}
		}
		else if(combo.charAt(0) == 'R') {
			System.out.println("Turning the lock right " + turnMagnitude);
			comboNeedle = (comboNeedle + turnMagnitude) % 100;
			if(comboNeedle > 99) {
				System.out.println("Out of range after a right turn: " + comboNeedle);
			}
		}
		if(comboNeedle > 99 || comboNeedle < 0) {
			System.out.println("Combo needle out of range: " + comboNeedle);
			return comboNeedle;
		}
		else {
			return comboNeedle;	
		}
	}

	public static boolean validInput(String LineInput) {
		boolean comboInputMatch = Pattern.matches("[LR][0-9]+", LineInput);
		return comboInputMatch;
	}
}
