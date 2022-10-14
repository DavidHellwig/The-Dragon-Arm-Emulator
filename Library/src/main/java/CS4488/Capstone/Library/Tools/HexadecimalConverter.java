package CS4488.Capstone.Library.Tools;

public class HexadecimalConverter {
    // Public Static Procedural Functions - Conversion Decimal<->Hexadecimal
    public static Short hexToDecimal(char[] hexArray){
        hexArray = cleanCharHex(hexArray);
        int index = hexArray.length-1;
        int power = 1;
        int result = 0;

        while (index > 0){
            result = result + (hexValue(hexArray[index]) * power);
            index = index - 1;
            power = power * 16;

        }

        if (hexArray[0] == '-'){
            result = result * -1;
        }

        return (short) result;
    }
    public static char[] decimalToHex(short value){
        char[] output = makeBlankChar5();
        int index = output.length-1;
        int remainder;
        int v = value;

        // Check for the sign, and then flip so the math works.
        if (value<0){
            output[0] = '-';
            //v = v *-1; TODO: figure out the correct way we want to display negative numbers, and how that works with 2's compliment.
        }

        while (index > 0){
            remainder = v%16;
            v = v/16;
            output[index] = hexChar(remainder);
            index = index - 1;
        }
        return output;
    }
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
        }
        return result;
    }
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
        }
        return result;
    }

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
    //Utility Methods


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
    public static char[] makeBlankChar5(){
        char[] array = new char[5];
        array[0] = '+';
        for (int i=1; i<5; i++) {
            array[i] = '0';
        }
        return array;
    }
}
