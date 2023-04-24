import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
    }

    public void clear() {
        // TODO: Wyczyść zawartość HashMap.
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        return false;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return -1;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        return null;
    }

    public void put(TKey key, TValue value) {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
        return null;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return -1;
    }
}
