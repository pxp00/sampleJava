package com.nil;

public class EnumTst {
    enum Color {
        RED;  //
    }

    // final couldn't modify, static cls.tmp;

    static class Color2 {
        private Color2() {
        }

        public  static final Color2 red = new Color2();

    }


}







class ATst {
    EnumTst.Color color = EnumTst.Color.RED;
}