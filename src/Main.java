import java.util.*;

// Stores details of a palindrome
class PalindromeInfo {
    String text;
    int startIndex;
    int length;

    PalindromeInfo(String text, int startIndex, int length) {
        this.text = text;
        this.startIndex = startIndex;
        this.length = length;
    }
}

// Responsible for checking palindromes
class PalindromeChecker {
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}

// Finds top 3 unique longest palindromes
class PalindromeFinder {
    public static List<PalindromeInfo> findTop3LongestPalindromes(String input) {
        Map<String, PalindromeInfo> uniquePalindromes = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                String sub = input.substring(i, j);
                if (PalindromeChecker.isPalindrome(sub)) {
                    // Only consider if not already found
                    if (!uniquePalindromes.containsKey(sub)) {
                        uniquePalindromes.put(sub, new PalindromeInfo(sub, i, sub.length()));
                    }
                }
            }
        }

        // Convert to list and sort by length descending
        List<PalindromeInfo> sortedList = new ArrayList<>(uniquePalindromes.values());
        sortedList.sort((a, b) -> b.length - a.length);

        return sortedList.subList(0, Math.min(3, sortedList.size()));
    }
}

// Main runner with input and edge handling
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a string to find top 3 longest unique palindromes: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("❌ Error: Input string cannot be empty.");
                return;
            }

            List<PalindromeInfo> result = PalindromeFinder.findTop3LongestPalindromes(input);

            if (result.isEmpty()) {
                System.out.println("⚠️ No palindromes found in the input.");
            } else {
                System.out.println("\n✅ Top longest unique palindromes (desc by length):");
                for (PalindromeInfo p : result) {
                    System.out.printf("🔹 Palindrome: \"%s\", Start Index: %d, Length: %d%n",
                            p.text, p.startIndex, p.length);
                }
            }

        } catch (Exception e) {
            System.out.println("⚠️ An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
