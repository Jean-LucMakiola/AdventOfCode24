package dayThree;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class DayThree {

    // Function to extract mul expressions and handle do() and don't() instructions
    public static long extractNumFromValidExpressions(String inputString) {
        long totalSum = 0; // Variable to accumulate the total sum
        boolean isEnabled = true; // Initially, mul operations are enabled

        // Create a regex pattern to match mul(), do(), don't(), and other unwanted characters
        String pattern = "(mul\\s*\\(\\s*\\d{1,3}\\s*,\\s*\\d{1,3}\\s*\\))|do\\(\\)|don't\\(\\)";
        Pattern regex = Pattern.compile(pattern);

        // Create a matcher to find all occurrences of mul(), do(), and don't()
        Matcher matcher = regex.matcher(inputString);

        // Process the input string
        while (matcher.find()) {
            String match = matcher.group(0).trim();

            // Handle 'do()' and 'don't()' instructions to toggle the mul operation
            if (match.equals("do()")) {
                isEnabled = true; // Enable future mul operations
            } else if (match.equals("don't()")) {
                isEnabled = false; // Disable future mul operations
            }
            // Process mul() expressions if enabled
            else if (isEnabled && match.startsWith("mul(")) {
                // Extract numbers from the mul expression
                String numPattern = "\\d{1,3}";
                Pattern numRegex = Pattern.compile(numPattern);
                Matcher numMatcher = numRegex.matcher(match);
                int[] nums = new int[2];
                int i = 0;
                while (numMatcher.find() && i < 2) {
                    nums[i++] = Integer.parseInt(numMatcher.group());
                }

                // If two numbers are found, multiply them and add to the total sum
                if (i == 2) {
                    totalSum += (long) nums[0] * nums[1];
                }
            }
        }

        return totalSum;
    }

    // Function to read the input from a text file
    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Append each line to the content
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return content.toString();
    }

    public static void main(String[] args) {
        // Specify the file path (replace with your actual file path)
        String fileName = "C:\\Users\\jeanl\\IdeaProjects\\AdventOfCode\\src\\dayThree\\dayThreeData.txt";

        // Read the content from the file
        String inputString = readFile(fileName);

        // Calculate the total sum of enabled multiplications and print it
        long totalSum = extractNumFromValidExpressions(inputString);
        System.out.println("Total Sum of Enabled Multiplications: " + totalSum);
    }
}
