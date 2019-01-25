public class Student2 extends Student {
    private int medie;

    public Student2(String nume, int age, int medie) {
        super(nume, age);
        this.medie = medie;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "medie=" + medie +
                '}';
    }
}
