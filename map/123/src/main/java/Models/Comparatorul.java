package Models;

/**
 * Clasa folosita pentru a incapsula compararile necesare
 * la filtrari, sortari si alte operatii
 */
public class Comparatorul {

    /**
     * Exista o singura instanta la un momendat a Comparatororului
     * care la inceput e null
     */
    private static Comparatorul instance = null;

    /**
     * Constructorul este privat pentru a nu permite crearea
     * a mai multor obiecte de tip Comparator
     */
    private Comparatorul() {}

    /**
     * @return instanta de Comparator
     */
    public static Comparatorul getInstance() {
        if (instance == null) {
            instance = new Comparatorul();
        }
        return instance;
    }


    /**
     * Functie care compara 2 studenti in functie de numele lor
     * @param st1 studentul 1
     * @param st2 studentul 2
     * @return 0 daca cei st1 are acelasi nume cu st2, un int mai mare ca 0 daca st1 are un nume
     * mai "mare" de cat st2, un int mai mic ca 0 daca st1 are numele "mic" de cat st2
     * compararile facanduse lexicografic
     */
    public int compareStudentByName(Student st1, Student st2) {
        return st1.getName().compareTo(st2.getName());
    }


    /**
     * Functie care compara 2 studenti in functie de grupa lor
     * @param st1 studentul 1
     * @param st2 studentul 2
     * @return 0 daca cei doi studenti au aceasi grupa, un int mai mic ca 0 daca st1 se afla intr-o grupa
     * mai mica fata de st2, un int mai mare de cat 0 daca st1 se afla intr-o grupa mai mare de cat st2
     */
    public int compareStudentByGrupa(Student st1, Student st2) {
        Integer gr1 = st1.getGroup();
        Integer gr2 = st2.getGroup();
        return gr1.compareTo(gr2);
    }

    /**
     * Functie care compara 2 note in functie de valoare lor
     * @param n1 nota 1
     * @param n2 nota 2
     * @return 0 daca cele 2 note au aceasi valoare, un int mai mare de cat 0 daca nota 1 are o
     * valoare mai mare de cat nota 2, un int mai mic de cat 0 daca nota 1 are o valoare mai mica de cat nota 2
     */
    public int compareNoteByValue(Nota n1, Nota n2) {
        Double nota1 = n1.getValoare();
        Double nota2 = n2.getValoare();
        return nota1.compareTo(nota2);
    }

    /**
     * Functie care compara 2 note in functie de id-ul studentilor
     * @param n1 nota 1
     * @param n2 nota 2
     * @return 0 daca cele 2 note au acelasi id de student, un int mai mare de cat 0 daca nota 1 are un un id
     * de stundet mai mare de cat nota 2, un int mai mic de cat 0 daca nota 1 are un id de student mai mic
     * de cat nota 2
     */
    public int compareNoteByStudent(Nota n1, Nota n2)
    {
        return n1.getIdStudent().compareTo(n2.getIdStudent());
    }

    /**
     * Functie care compara 2 teme in functie de deadline-urile lor
     * @param t1 tema 1
     * @param t2 tema 2
     * @return 0 daca cele 2 note au acelasi deadline, un int mai mare de cat 0 daca tema 1 are un deadline mai
     * mare de cat tema 2, un int mai mic de cat 0 daca tema 1 are un deadline mai mic de cat tema 2
     */
    public int compareTemeByDeadLine(Tema t1, Tema t2) {
        Integer d1 = t1.getDeadline();
        Integer d2 = t2.getDeadline();
        return d1.compareTo(d2);
    }

    /**
     * Functie care compara 2 teme in functie de id-ul lor
     * @param t1 tema 1
     * @param t2 tema 2
     * @return 0 daca cele 2 teme au acelasi id, un int mai mic de cat 0 daca tema 1 are un id mai mic,
     * un int mai mare de cat 0 daca tema 1 are un id mai mare fata de tema 2
     */
    public int compareTemeByNumar(Tema t1, Tema t2) {
        Integer nr1 = t1.getId();
        Integer nr2 = t2.getId();
        return nr1.compareTo(nr2);
    }


}
