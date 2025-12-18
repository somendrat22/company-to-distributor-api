package com.supplynext.company_api.utilities;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommonUtility {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");


    public static String generateCompanyId(String enitityName) {
        String dateTime = LocalDateTime.now().format(FORMATTER);
        int random = ThreadLocalRandom.current().nextInt(100, 999);
        return enitityName + dateTime + "-" + random;
    }
}
