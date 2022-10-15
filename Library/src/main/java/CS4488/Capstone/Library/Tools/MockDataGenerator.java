package CS4488.Capstone.Library.Tools;

import java.util.Random;

/**
 * This class is for randomly generating "Junk Data" characters.
 * It's basically just a static instance of Random.
 *
 * @author Traae
 * @version 1.0
 */
public class MockDataGenerator {
    private static Random random = new Random();

    public static char getRandomHexChar(){
        char result = '0';
        int c = random.nextInt(16);
        result = Hex4digit.hexChar(c);
        return result;
    }

    public static int getRandomHexValue(){
        return random.nextInt(16);
    }

    public static int getRandomHexValue4(){
        int result = 1;
        result = result * (random.nextInt(15) + 1);
        result = result * (random.nextInt(15) + 1);
        result = result * (random.nextInt(15) + 1);
        result = result * (random.nextInt(15) + 1);
        if (random.nextBoolean()){
            result = result * -1;
        }
        return result;
    }

    public static char[] getRandomHexChar5(){
        char[] result = new char[5];
        for (int i=1; i<5; i++) {
            result[i] = getRandomHexChar();
        }
        result[0] = '-';
        if (random.nextBoolean()){
            result[0] = '+';
        }

        return result;
    }

    public static char getJunkChar(){
        char result = '0';
        int c = random.nextInt(16);
        result = (char)(c + 103);
        return result;
    }
    public static char[] getJunkChar4(){
        char[] result = new char[4];
        for (int i=0; i<4; i++) {
            result[i] = getJunkChar();
        }
       return result;
    }


}
