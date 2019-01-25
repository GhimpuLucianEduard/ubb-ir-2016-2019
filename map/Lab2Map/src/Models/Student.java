package Models;

/*
    Scrieti o aplicatie care ajuta profesorul in monitorizarea temelor de laborator ale studentilor la disciplina
    MAP. Datele despre studenti se citesc din fisierul Studenti si cuprind urmatoarele informatii: idStudent
    (numarul matricol al studentului), numele, grupa, email, cadru didactic indrumator de laborator. Datele
    referitoare la temele de laborator se citesc din fisierul Teme, care contine informatii precum: numarul
    temei de laborator (identificator unic), descrierea pe scurt a cerintei, termen limita de predare (deadline),
    reprezentand saptama din cursul semestrului (1..14) pana la care nota maxima va fi 10 pentru tema
    respectiva. Profesorul acorda o nota de la 1 la 10 pentru fiecare tema de laborator. Predarea unei teme
    dupa saptamana in care este stabilit termenul de predare a temei respective conduce la diminuarea notei
    cu 2 puncte pt fiecare saptamana de intarziere. Dupa 2 saptamani de intarziere in predarea unei teme,
    tema respectiva va fi notata automat cu nota 1. Informatiile despre note se vor salva in fisierul Catalog.
 */
public class Student
{
    private int idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String profesor;


    public Student(int idStudent, String nume, int grupa, String email, String profesor)
    {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
        this.profesor = profesor;
    }

    public int getIdStudent()
    {
        return idStudent;
    }

    public void setIdStudent(int idStudent)
    {
        this.idStudent = idStudent;
    }

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public int getGrupa()
    {
        return grupa;
    }

    public void setGrupa(int grupa)
    {
        this.grupa = grupa;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getProfesor()
    {
        return profesor;
    }

    public void setProfesor(String profesor)
    {
        this.profesor = profesor;
    }
}
