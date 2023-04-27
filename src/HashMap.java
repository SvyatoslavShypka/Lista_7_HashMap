import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {

    public Node[] nodeArray;;
    // capacity is the number of buckets - by default 16
    private int initialSize;
    int current_no_elements;

    // load_factor is used to perform calculations and guess if automatic increase
    // is required
    private final double loadFactor;
    // current_size should be increment when current_no_elements x load_factor becomes large
    int current_size;

    private LinkedHashMap<Integer, String> hashMap;
    private int hash;
    private Function<TKey, Integer> hashFunction;

    class Node {
        private TKey key;
        private TValue value;

        Node(TKey k, TValue v) {
            key = k;
            value = v;
        }

        TKey getKey() {
            return key;
        }

        TValue getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((key == null) ? 0 : key.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (key == null) {
                if (other.key != null)
                    return false;
            } else if (!key.equals(other.key))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }
    }


    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);

        nodeArray = (Node[]) Array.newInstance(Node.class, initialSize);
        current_size = initialSize;
        current_no_elements = 0;

        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        if (containsKey(key)) {
            throw new DuplicateKeyException();
        }
        else {
            nodeArray[current_no_elements++] = new Node(key, value);
        }
        if (current_no_elements >= (current_size * loadFactor)) {
            increaseHashmap();
        }
    }

    public void clear() {
        // TODO: Wyczyść zawartość HashMap.
        nodeArray = (Node[]) Array.newInstance(Node.class, initialSize);
        current_size = initialSize;
        current_no_elements = 0;
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        for (int i = 0; i < current_size; i++) {
            if (nodeArray[i] != null) {
                if (nodeArray[i].getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        for (int i = 0; i < current_size; i++) {
            if (nodeArray[i] != null) {
                if (nodeArray[i].getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return current_no_elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        if (!containsKey(key)) throw new NoSuchElementException();

        TValue value = null;
        for (int i = 0; i < current_size; i++) {
            if (nodeArray[i] != null) {
                if (nodeArray[i].getKey().equals(key)) {
                    value = nodeArray[i].getValue();
                    return value;
                }
            }
        }
        return value;
    }

    public void put(TKey key, TValue value) {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        // capactiy is automatically increased when:
        if (current_no_elements >= (current_size * loadFactor)) {
            increaseHashmap();
        }
        // calculate position to insert the new element
        int position = calculatePosition(key);

        Node element = new Node(key, value);
        // If position is filled, new element overwrite it without increasing number of
        // elements in array
        if (nodeArray[position] == null) {
            // increment elements no
            current_no_elements++;
        }
        nodeArray[position] = element;
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
        if (!containsKey(key)) {
            return null;
        }
        TValue result = get(key);
        int position = calculatePosition(key);
        for (int i = position; i < current_no_elements -1; i++) {
        nodeArray[i] = nodeArray[i + 1];
        }
        current_no_elements--;
        return result;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return current_size;
    }

    // HashMap should be increased when current_no_elements becomes large (up to 75% capacity)
    public void increaseHashmap() {
        int newLength = current_size * 2;
        nodeArray = Arrays.copyOf(nodeArray, newLength);
        current_size = newLength;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + initialSize;
        result = prime * result + current_no_elements;
        result = prime * result + current_size;
        result = prime * result + Float.floatToIntBits((float) loadFactor);
        result = prime * result + Arrays.hashCode(nodeArray);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HashMap other = (HashMap) obj;
        if (initialSize != other.initialSize)
            return false;
        if (current_no_elements != other.current_no_elements)
            return false;
        if (current_size != other.current_size)
            return false;
        if (Float.floatToIntBits((float) loadFactor) != Float.floatToIntBits((float) other.loadFactor))
            return false;
        if (!Arrays.equals(nodeArray, other.nodeArray))
            return false;
        return true;
    }

    private int calculatePosition(TKey key) {

        int index;
        index = key.hashCode() & (current_size - 1);
        return index;
    }
}
