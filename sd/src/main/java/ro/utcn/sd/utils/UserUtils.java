package ro.utcn.sd.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserUtils {

    public String generateRandomCode(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 6;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
