package Persistence;
import Business.Entidades.Generator;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con los generadores del juego.
 */
public interface GeneratorDAO {

    /**
     * Inserta los generadores básicos (Cafetera, Cheta y God) para una partida específica.
     *
     * @param idPartida ID de la partida.
     */
    void addBasicGenerators(int idPartida);

    /**
     * Recupera todos los generadores almacenados en la base de datos.
     *
     * @return Lista de generadores.
     */
    List<Generator> getAllGenerators();

    /**
     * Actualiza los datos de un generador específico.
     *
     * @param generator Generador a actualizar.
     * @param idPartida ID de la partida asociada.
     * @param numeroCafeteras Número total de generadores de ese tipo.
     */
    void updateGenerator(Generator generator, int idPartida, int numeroCafeteras);

    /**
     * Devuelve el número de generadores de un tipo asociado a una partida.
     *
     * @param generator Generador del cual se desea conocer la cantidad.
     * @return Número de generadores.
     */
    int numeroGenerador(Generator generator);

    /**
     * Recupera un generador específico por nombre e ID de partida.
     *
     * @param idPartida ID de la partida.
     * @param nombre Nombre del generador.
     * @return Generador correspondiente o null si no existe.
     */
    Generator getGenerator(int idPartida, String nombre);


}
