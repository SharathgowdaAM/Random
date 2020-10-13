package TechArrow.Classes;

import java.util.HashMap;
import java.util.Map;

public class CompareStringsAndFindAccuracy {
    public static void main(String[] args) {
        String a="TechArrow1";
        String b="TechArrrrow2";
        System.out.println("Differences:"+getDifference(a,b));
        System.out.println("Accuracy is:"+findAccuracy(a,b,getDifference(a,b).getDiffValue()));
    }


    public static Pair<String> getDifference(String a, String b) {
        return diffHelper(a, b, new HashMap<>());
    }


    public static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        long key = ((long) a.length()) << 32 | b.length();
        if (!lookup.containsKey(key)) {
            Pair<String> value;
            if (a.isEmpty() || b.isEmpty()) {
                value = new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                value = diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    value = new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    value = new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
            lookup.put(key, value);
        }
        return lookup.get(key);
    }

    public static class Pair<T> {
        public final T first, second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public String toString() {
            return "(" + first + "," + second + ")";
        }

        public int getDiffValue() {
            int length = first.toString().length() + second.toString().length();
            return length;
        }
    }

    public static double findAccuracy(String a, String b, int totalDiffValue) {
        double accuracy;
        int totalExpected = a.length() + b.length();
        int diff = totalExpected - totalDiffValue;
        accuracy = (diff * 100) / totalExpected;
        accuracy = Math.abs(accuracy);
        return accuracy;
    }
}