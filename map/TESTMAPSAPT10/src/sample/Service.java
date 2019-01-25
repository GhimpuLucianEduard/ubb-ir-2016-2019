package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Service {
    private List<Articol> list;

    public Service(List<Articol> list) {
        this.list=list;
    }




    public List<Articol> getList() {
        return list;
    }

    public List<String> getAllAutori() {
        List<String> autori = new ArrayList<>();
        list.forEach(x->{
            String[] tokens = x.getAutori().split(";");
            for (String t : tokens)
                autori.add(t);
        });
        return autori;
    }



    public <E> List<E> genericFilt(List<E> entities, Predicate<E> filt, Comparator<E> comp, Comparator<E> comp2) {
        return entities.stream().filter(filt).sorted(comp).sorted(comp2).collect(Collectors.toList());
    }

    public List<Articol> filtByAutor(String text) {
        Predicate<Articol> filt = x -> x.getAutori().contains(text);
        return genericFilt(list, filt, comp1(), comp2());
    }

//    public List<Articol> filtByDomeniu(Domeniu dom, String k1, String k2, String k3, String k4) {
//        Predicate<Articol> filt = x -> x.getKeywords().contains(k1) || x.getKeywords().contains(k2) ||
//        Predicate<Articol> filt2 = x->x.getDomeniu().compareTo(dom)==0;
//        Predicate<Articol> filt3 = filt.and(filt2);
//        return genericFilt(list, filt3, comp1(), comp2());
//    }


    public List<Articol> filtByTitlu(String titlu) {
        Predicate<Articol> filt = x -> x.getTitlu().contains(titlu);
        return genericFilt(list, filt, comp1(), comp2());
    }

    public Comparator<Articol> comp1() {
        return new Comparator<Articol>() {
            @Override
            public int compare(Articol o1, Articol o2) {

                return o1.getTitlu().compareTo(o2.getTitlu());
            }
        };
    }

    public Comparator<Articol> comp2() {
        return new Comparator<Articol>() {
            @Override
            public int compare(Articol o1, Articol o2) {

                return o1.getDomeniu().compareTo(o2.getDomeniu());
            }
        };
    }



}
