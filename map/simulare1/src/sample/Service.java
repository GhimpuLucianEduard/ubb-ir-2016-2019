package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Service {
    List<Disciplina> list = new ArrayList<>();

    public <E> List<E> genericFilt(List<E> entities, Predicate<E> filt, Comparator<E> comp) {
        return entities.stream().filter(filt).sorted(comp).collect(Collectors.toList());
    }

    public List<Disciplina> filtByDisciplina(String text) {
        Predicate<Disciplina> filt = x -> x.getDenumire().contains(text);
        Comparator<Disciplina> comp = new Comparator<Disciplina>() {
            @Override
            public int compare(Disciplina o1, Disciplina o2) {
                Integer c1 = o1.getCod();
                Integer c2 = o2.getCod();
                return c1.compareTo(c2);
            }
        };
        return genericFilt(list, filt, comp);
    }

    public List<Disciplina> filtByTipAndNr(Tip tip, int nr1, int nr2) {
        Predicate<Disciplina> filt = x-> x.getTip().compareTo(tip)==0;
        Predicate<Disciplina> filt2 = x-> x.getNrStudenti()>=nr1 && x.getNrStudenti()<=nr2;
        Predicate<Disciplina> filt3 = filt.and(filt2);
        Comparator<Disciplina> comp = new Comparator<Disciplina>() {
            @Override
            public int compare(Disciplina o1, Disciplina o2) {
                return o1.getDenumire().compareTo(o2.getDenumire());
            }
        };
        return genericFilt(list,filt3,comp);
    }

    public List<Disciplina> filtByNotRip(Tip tip) {
        Predicate<Disciplina> filt = x->x.getTip().compareTo(tip)!=0;
        Comparator<Disciplina> comp = new Comparator<Disciplina>() {
            @Override
            public int compare(Disciplina o1, Disciplina o2) {
                Integer c1 = o1.getNrStudenti();
                Integer c2 = o2.getNrStudenti();
                return c1.compareTo(c2);
            }
        };
        return genericFilt(list,filt,comp);
    }

    public Service(List<Disciplina> list) {
        this.list = list;
    }

    public List<Disciplina> getList() {
        return list;
    }
}
