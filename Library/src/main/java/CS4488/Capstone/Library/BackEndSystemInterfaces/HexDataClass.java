package CS4488.Capstone.Library.BackEndSystemInterfaces;
/**
 * Number converter methods ned by the system
 *
 * @version 1.0
 * @author Traae
 */
public interface HexDataClass {

    public void setValue(int number);
    public void setValue(String number);
    public int getValue();
    public char[] getHexChars();
    public int getMiddle2Value();
    public int getLast2Value();

}
