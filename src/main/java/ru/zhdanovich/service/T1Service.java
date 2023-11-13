package ru.zhdanovich.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class T1Service {
    public String parseString(String str) {
        Map<Character, Integer> chars = countChar(str);
        StringBuilder stringBuilder = new StringBuilder();

        chars.forEach(
                (ch, v)  ->
                        stringBuilder.append("“").append(ch).append("”: ").append(v).append(", ")
        );

        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    private Map<Character, Integer> countChar(String str) {
        Map<Character, Integer> chars = new HashMap<>();

        for(char ch : str.toCharArray()) {
            if (!chars.containsKey(ch)) {
                chars.put(ch, 1);
            } else {
                chars.put(ch, chars.get(ch) + 1);
            }
        }

        return chars.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                );
    }
}
