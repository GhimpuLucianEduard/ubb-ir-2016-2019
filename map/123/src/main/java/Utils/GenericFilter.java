package Utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericFilter {
        public static <E> List<E> genericFilt(List<E> entities, Predicate<E> filt, Comparator<E> comp) {
            return entities.stream().filter(filt).sorted(comp).collect(Collectors.toList());
        }
}
