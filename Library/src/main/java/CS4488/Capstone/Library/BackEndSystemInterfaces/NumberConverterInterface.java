package CS4488.Capstone.Library.BackEndSystemInterfaces;

public interface NumberConverterInterface {

    public void setValue(Short number);
    public void setValue(String number);
    public Short getShort();
    public char[] getHexChars();
    public int getMiddle2Value();
    public int getLast2Value();

}
