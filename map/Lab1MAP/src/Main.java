public class Main
{

    public static void main(String[] args)
    {
        NrComplex nr1 = new NrComplex(2,-3);
        System.out.println(nr1.toString());
        NrComplex nr2 = new NrComplex(-2,2);
        nr1.add(nr2);
        System.out.println(nr1.toString());


    }
}