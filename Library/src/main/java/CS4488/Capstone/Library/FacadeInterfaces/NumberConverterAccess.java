package CS4488.Capstone.Library.FacadeInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

/**
 * Number converter methods for the U.I.
 * 
 * @version 1.0
 * @author Traae
 */
public interface NumberConverterAccess {
    public char[] convertToHexChars(Short number);
    public int convertToInt(char[] number);
}
