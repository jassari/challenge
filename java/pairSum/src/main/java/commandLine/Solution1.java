package commandLine;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution1 {
    private final static String RESULT_FILE_PATH = "result.txt";
    private final static String INPUT_SEPARATOR = ",";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_FILE_PATH));

        // 1. Reading input
        StringBuilder input = new StringBuilder(reader.readLine());

        // 2. Extracting target sum
        int startIndex = input.lastIndexOf(" ");
        String lastPart = input.substring(startIndex);
        Integer targetSum = Integer.parseInt(lastPart.trim());

        // 3. Extracting integer list
        input = input.replace(startIndex,startIndex + lastPart.length(), "");
        String[] numbers = input.toString().replaceAll(" ", "").split(INPUT_SEPARATOR);
        List<Integer> integers = Arrays.asList(numbers)
                                .stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        // 4. Getting pairs
        Map<Integer, Integer> pairs = pairsSum(integers, targetSum);

        // 5. Printing to file
        StringBuilder result = new StringBuilder();
        pairs.forEach((k, v) ->  result.append( "+ " + k + "," + v + "\n"));
        writer.write(result.toString());
        reader.close();
        writer.close();
    }

    public static Map<Integer, Integer> pairsSum(List<Integer> integersUnsorted, Integer targetSum) {
        Map<Integer, Integer> pairs = new HashMap<>();
        List<Integer> integers = integersUnsorted.stream().sorted().collect(Collectors.toList());
        for (Integer number1 : integers) {
            Integer potentialNumber2 = targetSum - number1;
            if (pairs.containsKey(potentialNumber2)) {
                continue;
            } else {
                if (potentialNumber2 != 0 && integers.contains(potentialNumber2)) {
                    pairs.put(number1, potentialNumber2);
                }
            }
        }
        return  pairs;
    }
}
