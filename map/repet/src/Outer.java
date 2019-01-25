public class Outer {
    public Hidden getInnerInstance() {
        int a=9;
        class FuncInner implements Hidden {
            private int i = 11;
            public int value() {
                i+=a;
//a++;
                return i;
            }
        }
        return new FuncInner();
    }
}
