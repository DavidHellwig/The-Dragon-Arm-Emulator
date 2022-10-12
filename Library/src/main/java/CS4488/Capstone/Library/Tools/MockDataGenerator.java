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

    public static char getJunkChar(){
        char result = '0';
        int c = random.nextInt(16);
        result = (char)(c + 103);
        return result;
    }
    public static char[] getJunkChar4(){
        char[] result = new char[4];
        for (char c: result) {
            c = getJunkChar();
        }
       return result;
    }


}
