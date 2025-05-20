package Business.Entidades;

/**
 * Clase que representa un par de valores clave-valor.
 *
 * @param <K> Tipo de la clave.
 * @param <V> Tipo del valor.
 */
public class Pair<K, V> {
    private final K key;
    private final V value;

    /**
     * Constructor de la clase Pair.
     *
     * @param key Clave del par.
     * @param value Valor del par.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Devuelve la clave del par.
     *
     * @return Clave.
     */
    public K getKey() {
        return key;
    }

    /**
     * Devuelve el valor del par.
     *
     * @return Valor.
     */
    public V getValue() {
        return value;
    }
}
