package com.supplynext.company_api.utilities;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CommonUtility {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "@#$%&*!";
    private static final int SALT_ROUNDS = 12;

    private static final String ALL = LOWER + UPPER + DIGITS + SPECIAL;
    private static final SecureRandom RANDOM = new SecureRandom();


    public static String generateIdForEntity(String enitityName) {
        String dateTime = LocalDateTime.now().format(FORMATTER);
        int random = ThreadLocalRandom.current().nextInt(100, 999);
        return enitityName + dateTime + "-" + random;
    }

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure at least one character from each category
        password.append(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        password.append(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(RANDOM.nextInt(SPECIAL.length())));

        // Fill remaining characters
        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }

        // Shuffle to avoid predictable order
        return shuffle(password.toString());
    }

    private static String shuffle(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            char temp = characters[index];
            characters[index] = characters[i];
            characters[i] = temp;
        }
        return new String(characters);
    }

    public static String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(SALT_ROUNDS));
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
