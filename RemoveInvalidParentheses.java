/*
 * TC: O(2^n) where n is the length of the string
 * SC: O(n) for the recursion stack
 */
import java.util.*;

public class RemoveInvalidParentheses {
    int maxLength = 0;
    Set<String> visited = new HashSet<>();
    List<String> result = new ArrayList<>();
    public List<String> removeInvalidParentheses(String s) {
        //if the String itself is valid, no deletions required
        if(checkIfValid(s)) return List.of(s);
        //dfs over the String to generate all possible answers
        dfs(new StringBuilder(s));
        return result;
    }
    private void dfs(StringBuilder sb) {
        //ignore everything less than maxLength
        if(sb.length() < maxLength) {
            return;
        }
        //skip if already seen
        if(visited.contains(sb.toString())) {
            return;
        }
        if(checkIfValid(sb.toString())) {
            //if valid and > maxLength, this is the new
            //best length, update result and maxLength
            if(maxLength < sb.length()) {
                result = new ArrayList<>();
                result.add(sb.toString());
                maxLength = sb.length();
            }
            //if valid and == maxLength, add to result
            else if (maxLength == sb.length()) {
                result.add(sb.toString());
            }
        }
        //mark as seen
        visited.add(sb.toString());
        //dfs removing all possible indices
        for(int i = 0; i < sb.length(); i++) {
            if(Character.isAlphabetic(sb.charAt(i))) continue;
            StringBuilder child = new StringBuilder(sb).deleteCharAt(i);
            dfs(child);
        }
    }
    // function to check if a given String
    // of parentheses and letters is valid
    private boolean checkIfValid(String s) {
        if(s.isEmpty()) return true;
        int count = 0;
        for(char c: s.toCharArray()) {
            if(c == ')') {
                if(count == 0) return false;
                count--;
            }
            else if(c == '(') {
                count++;
            }
        }
        return count == 0;
    }
}
