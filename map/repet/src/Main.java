public class Main {

    public static void initStudent(Student s, String n, int age)
    {
        s.setNume(n);
        s.setAge(age);
    }

    public static void main(String[] args)
    {

        Student s = new Student2("andu,",2,3);
        System.out.println(s.toString());

        Outer out = new Outer();
        Hidden in3 = out.getInnerInstance();
        System.out.println(in3.value());

        ClassC ca = new ClassC();
        ca.functionC();

        T1 t1 = new T1();
        T1 t2 = new T2();
        T1 t3 = new T3();


    }


}
