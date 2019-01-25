package Repos.FileRepos;

/**
 * Clasa folosita pe post de interfata
 * Incapsuleaza metode specifice lucrului cu fisier
 * @param <E> tipului de obiect pe care il are
 * @param <ID> tipul id-ului obiectului respectiv
 */
public interface FileRepository<E, ID> {
    /**
     * Functie folosita pentru a scrie intr-un fisier tot continutul repo-ului
     */
    void writeToFile();

    /**
     * Functie folosita pentru a scrie un singur obiect in fisier
     * @param entity obiectul care trebuie scris in fisier
     */
    void writeToFile(E entity);

    /**
     * Functie folosita pentru a citit tot continutul fisierului
     * care apoi va fi incarcat in repo
     */
    void readFromFile();
}
