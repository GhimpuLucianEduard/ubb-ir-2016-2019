public class ClassC extends ClassA {
    public void functionC(){
        ClassA a=new ClassA();
        ClassB b=new ClassB();
        a.functionA2();
        b.functionB();
        b.functionA2();
        //a.functionA();
    }
}