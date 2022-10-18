package CS4488.Capstone.Library.Tools;

import java.security.PublicKey;

public class HexadecimalConverter {
    // GLOBAL VARIABLE Min and Max for our 4 digit hexadecimal numbers
    public static final int MAX = 65535;
    public static final int MIN = -65536;

    // Public Static Procedural Functions- Conversion Decimal<->Hexadecimal

    /**
     * Hexidecimal Char[] to int Decimal value
     * @param hexArray  any char[] will be accepted and cleaned to work.
     * @return the integer value
     */
    public static int hexToDecimal(char[] hexArray){
        hexArray = cleanCharHex(hexArray); // input scrub
        int index = hexArray.length-1;
        int power = 1;
        int result = 0;

        // check for the negative sign.
        boolean isNegative = (hexArray[0] == '-');

        // Hexadecimal conversion algorithm
        while (index > 0){
            result = result + (hexValue(hexArray[index]) * power);
            index = index - 1;
            power = power * 16;
        }

        // If negative flip
        if (isNegative){
            result = result * -1;

            // Special case for the min, due to 2's compliment trickery and Java number types.
            if (result == 0){
                result = MIN;
            }
        }

        return result;
    }

    /**
     * int Decimal value to Hexidecimal Char[]
     * @param value
     * @return char[5] of the +/- & 4 hex digits
     */
    public static char[] decimalToHex(int value){
        value = hex4digitValueWrap(value);
        char[] output = makeBlankChar5();
        int index = output.length-1;
        int remainder;
        boolean isNegative = (value<0);


        if (isNegative){
            output[0] = '-';
            value = value *-1;
        }

        while (index > 0){
            remainder = value%16;
            value = value/16;
            output[index] = hexChar(remainder);
            index = index - 1;
        }


        return output;
    }

    /**
     * Get a char of an int value
     * @param n 0-15
     * @return 0-9,a-f  otherwise 0;
     */
    public static char hexChar(int n){
        char result = '0';
        switch (n){
            case 0: result = '0';
                break;
            case 1:
                result = '1';
                break;
            case 2:
                result = '2';
                break;
            case 3:
                result = '3';
                break;
            case 4:
                result = '4';
                break;
            case 5:
                result = '5';
                break;
            case 6:
                result = '6';
                break;
            case 7:
                result = '7';
                break;
            case 8:
                result = '8';
                break;
            case 9:
                result = '9';
                break;
            case 10:
                result = 'a';
                break;
            case 11:
                result = 'b';
                break;
            case 12:
                result = 'c';
                break;
            case 13:
                result = 'd';
                break;
            case 14:
                result = 'e';
                break;
            case 15:
                result = 'f';
                break;
            default: result = '0';
        }
        return result;
    }

    /**
     * get the int value of a hexadecimal char
     * @param n 0-9,a-f
     * @return 0-15, otherwise 0;
     */
    public static int hexValue(char n){
        int result = 0;
        switch (n){
            case '0':
                result = 0;
                break;
            case '1':
                result = 1;
                break;
            case '2':
                result = 2;
                break;
            case '3':
                result = 3;
                break;
            case '4':
                result = 4;
                break;
            case '5':
                result = 5;
                break;
            case '6':
                result = 6;
                break;
            case '7':
                result = 7;
                break;
            case '8':
                result = 8;
                break;
            case '9':
                result = 9;
                break;
            case 'a':
                result = 10;
                break;
            case 'b':
                result = 11;
                break;
            case 'c':
                result = 12;
                break;
            case 'd':
                result = 13;
                break;
            case 'e':
                result = 14;
                break;
            case 'f':
                result = 15;
                break;
            default: result = 0;
        }
        return result;
    }

    /**
     * Prevent an int from being too big or small for a 4 digit hexadecimal.
     * Will wrap around to the other side of the scale.
     * @param n
     * @return int value in range [MAX (65535), MIN (-65536)]
     */
    public static int hex4digitValueWrap(int n){
        if (n > MAX){
            n = MIN + (n - MAX);
        }
        else if (n < MIN){
            n = MAX + (n - MIN);
        }

        return n;
    }

    /**
     * Cleans a Char[]
     *
     * Cleans out any nonHexadecimal characters,
     * returns a char[5] of only the Hex characters,
     * keeps a '-' iff its in index 0, otherwise the result will have a '+'
     *
     * fills in the final array from the last position, i.e. the "1's" digit.
     * If there are more than 4 hexadecimal digits the last 4 will stay.
     * If there are less, they will start in the last index, and then be filled with 0's.
     *
     * @param toClean
     * @return char[5] of hex character, +/-, and then 4 digits.
     */
    public static char[] cleanCharHex(char[] toClean){
        // Set up the result.
        char[] result = makeBlankChar5();

        // Convert toClean to a String for use of some functions.
        String input = new String(toClean);
        input.stripLeading().stripTrailing().toLowerCase().replaceAll("[^+-0-9a-f]", "");

        // check for negative (result will be set to positive by default)
        if (input.charAt(0) == '-'){ result[0] = '-'; }

        // clean out the sign
        input.replaceAll("[+-]", "");

        // Double countdown loop,
        // Counting down from the last index of each to fill in result starting from the 1's digit.
        int toCleanIndex = input.length()-1;
        int resultIndex = 4;
        while ((resultIndex>-1) && (toCleanIndex>-1)){
            result[resultIndex] = input.charAt(toCleanIndex);
            resultIndex--;
            toCleanIndex--;
        }
        return result;
    }


    /**
     * @param c charater
     * @return -1 if c is '-', else 1
     */
    public static int signToInt(char c){
        int result = 1;
        if (c == '-') {
            result = -1;
        }
        return result;
    }

    /**
     * @return char[5] = {+0000}
     */
    public static char[] makeBlankChar5(){
        char[] array = new char[5];
        array[0] = '+';
        for (int i=1; i<5; i++) {
            array[i] = '0';
        }
        return array;
    }
}
