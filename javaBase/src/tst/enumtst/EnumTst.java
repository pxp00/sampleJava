package tst.enumtst;

public class EnumTst {
}

 enum GenderType {
    Male("male"),
    Female("Female"),
    Other("other");

    final private String strGender;
    private GenderType(String strGender){
        this.strGender = strGender;
    }
    @Override
    public String toString() {
        return strGender;
    }
}