package dayTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class dayTwo {
    static List<Integer[]> list1 = new ArrayList<>();

    public static void main(String[] args) {
        readDataFromFile();

        System.out.println("SafeReports: " + countSafeReports(list1));
    }

    public static void readDataFromFile() {
        String filePath = "C:\\Users\\jeanl\\IdeaProjects\\AdventOfCode\\src\\dayTwo\\dayTwoData.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line until the end of the file
            while ((line = br.readLine()) != null) {
                // Check if the line is not empty
                if (!line.trim().isEmpty()) {
                    // Split the line by whitespace
                    String[] numbers = line.split("\\s+");
                    Integer[] report = new Integer[numbers.length];

                    // Parse each number and add it to the report array
                    for (int j = 0; j < numbers.length; j++) {
                        if (!numbers[j].isEmpty()) { // Check if the string is not empty
                            report[j] = Integer.parseInt(numbers[j]);
                        }
                    }
                    list1.add(report);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the results for verification
        System.out.println("List 1: " + list1);


    }

    public static int countSafeReports(List<Integer[]> reports) {
        int safeCount = 0;

        for (Integer[] report : reports) {
            if (isSafe(List.of(report)) || canBeMadeSafeByRemovingOne(List.of(report))) {
                safeCount++;
            }
        }

        return safeCount;
    }


    private static boolean isSafe(List<Integer> levels) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < levels.size(); i++) {
            int diff = Math.abs(levels.get(i) - levels.get(i - 1));
            if (diff < 1 || diff > 3) {
                return false; // Adjacent levels differ by more than allowed
            }
            if (levels.get(i) > levels.get(i - 1)) {
                isDecreasing = false; // Found an increase
            } else if (levels.get(i) < levels.get(i - 1)) {
                isIncreasing = false; // Found a decrease
            }
        }

        return (isIncreasing || isDecreasing); // Safe if either condition holds
    }

    private static boolean canBeMadeSafeByRemovingOne(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            // Create a new list without the current element
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }
        return false;
    }
}
