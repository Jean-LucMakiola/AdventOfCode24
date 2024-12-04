package dayOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayOne {
    public DayOne() {
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\jeanl\\IdeaProjects\\AdventOfCode\\src\\dayOne\\Ids.txt";
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line until the end of the file
            while ((line = br.readLine()) != null) {
                // Split the line by whitespace
                String[] numbers = line.split("\\s+");

                // Distribute values into two lists
                for (int i = 0; i < numbers.length; i++) {
                    if (i % 2 == 0) {
                        list1.add(Integer.parseInt(numbers[i])); // Add to list1 if index is even
                    } else {
                        list2.add(Integer.parseInt(numbers[i])); // Add to list2 if index is odd
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list1);
        Collections.sort(list2);

        // Print the results for verification
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        System.out.println();
        System.out.println(calcDifference(list1, list2));
        System.out.println();
        System.out.println(calcSimilarity(list1, list2));
    }

    public static int calcDifference(List<Integer> a, List<Integer> b) {
        int sum = 0;
        // Ensure both lists are of the same size
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("Lists must be of the same size");
        }

        for (int i = 0; i < a.size(); i++) {
            sum += Math.abs(a.get(i) - b.get(i));
        }
        return sum;
    }

    public static int calcSimilarity(List<Integer> a, List<Integer> b) {
        int similarity = 0;

        for (int i = 0; i < a.size(); i++) {
            int countInB = 0;

            // Count occurrences of a.get(i) in list b
            for (int j = 0; j < b.size(); j++) {
                if (a.get(i).equals(b.get(j))) {
                    countInB++;
                }
            }

            // Add to similarity score
            similarity += a.get(i) * countInB;
        }

        return similarity;
    }


    private static String arrayToString(int[] a) {
        StringBuilder s = new StringBuilder();
        for (int j : a) {
            s.append(j).append(" ");
        }
        return s.toString();
    }


}
