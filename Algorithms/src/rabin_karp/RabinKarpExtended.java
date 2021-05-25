package rabin_karp;

import java.util.*;

public class RabinKarpExtended
{
    private String text;
    private int prime = 11;
    private Map<Character, Integer> alphabet;
    private TreeMap<Integer, Integer> number2position;

    public RabinKarpExtended(String text)
    {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query)
    {
        ArrayList<Integer> indexes = new ArrayList<>();
        int queryHash = 0;
        int textHash = 0;
        int h = 1;

        for (int i = 0; i < query.length() - 1; i++) {
            h = (h * alphabet.size()) % prime;
        }

        for (int i = 0; i < query.length(); i++) {
            queryHash = (alphabet.size() * queryHash + alphabet.get(query.charAt(i))) % prime;
            textHash = (alphabet.size() * textHash + alphabet.get(text.charAt(i))) % prime;
        }

        for (int i = 0; i <= (text.length() - query.length()); i++) {
            if (queryHash == textHash) {
                int j = 0;
                for (; j < query.length(); j++) {
                    if (text.charAt(i + j) != query.charAt(j)) {
                        break;
                    }
                }
                if (j == query.length()) {
                    indexes.add(i);
                }
            }

            if (i < (text.length() - query.length())) {
                textHash = (alphabet.size() * (textHash - alphabet.get(text.charAt(i)) * h) + alphabet.get(text.charAt(i + query.length()))) % prime;
                if (textHash < 0) {
                    textHash = (textHash + prime);
                }
            }
        }
        return indexes;
    }

    private void createIndex()
    {
        List<Character> chars = new ArrayList<>();
        text.chars().distinct().forEach(c -> chars.add((char) c));
        alphabet = new HashMap<>();
        for (int i = 1; i <= chars.size(); i++){
            alphabet.put(chars.get(i - 1), i);
        }
    }
}