package Services;

import Models.Student;
import Models.Validators.ValidationException;
import Repos.Repository;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.ListEventType;
import Utils.ObserverDP.Observable;
import Utils.ObserverDP.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentService implements Observable<Student>  {
    private Repository<Student,String> repo;
    ArrayList<Observer<Student>> studentObservers=new ArrayList<>();

    public StudentService(Repository<Student, String> repo) {
        this.repo = repo;
    }

    public Optional<Student> addStudent(String id, String nume, int grupa, String mail, String prof) throws ValidationException {
        Student st = new Student(id,nume,grupa,mail,prof);
        Optional<Student> aux = repo.save(st);
        if(!aux.isPresent()) {
            ListEvent<Student> ev = createEvent(ListEventType.ADD,st,getAllStudents());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Student> addStudent(Student st) throws ValidationException {

        Optional<Student> aux = repo.save(st);
        if(!aux.isPresent()) {
            ListEvent<Student> ev = createEvent(ListEventType.ADD,st,getAllStudents());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Student> deleteStundet(String id) {
        Optional<Student> aux = repo.delete(id);
        if(aux.isPresent()) {
           ListEvent<Student> ev = createEvent(ListEventType.REMOVE,aux.get(),getAllStudents());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Student>  findStudentById(String id) {
        return repo.find(id);
    }

    public Optional<Student> updateStudent(String id, String nume, int grupa, String mail, String prof) throws ValidationException {
        Student st = new Student(id,nume,grupa,mail,prof);
        Optional<Student> aux = repo.update(st);
        if(aux.isPresent()) {
            ListEvent<Student> ev = createEvent(ListEventType.UPDATE,aux.get(),getAllStudents());
            notifyObservers(ev);
        }
        return aux;
    }

    public Optional<Student> updateStudent(Student st) throws ValidationException {
        Optional<Student> aux = repo.update(st);
        if(!aux.isPresent()) {
            ListEvent<Student> ev = createEvent(ListEventType.UPDATE,st,getAllStudents());
            notifyObservers(ev);
        }
        return aux;
    }

    public List<Student> getAllStudents() {
        return StreamSupport.stream(repo.getAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer<Student> o) {
        studentObservers.add(o);
    }

    @Override
    public void removeObserver(Observer<Student> o) {
        studentObservers.remove(o);
    }

    @Override
    public void notifyObservers(ListEvent<Student> event) {
        studentObservers.forEach(x->x.notifyEvent(event));
    }

    private <E> ListEvent<E> createEvent(ListEventType type, final E elem, final List<E> l){
        return new ListEvent<E>(type) {
            @Override
            public List<E> getList() {
                return l;
            }
            @Override
            public E getElement() {
                return elem;
            }
        };
    }

}

