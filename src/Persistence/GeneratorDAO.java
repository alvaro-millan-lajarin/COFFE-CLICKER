package Persistence;



import Business.Entidades.Generator;

import java.util.List;

public interface GeneratorDAO {

    void addBasicGenerators(int idPartida);
    List<Generator> getAllGenerators();
    void updateGenerator(Generator generator, int idPartida, int numeroCafeteras);
    int numeroGenerador(Generator generator);
    Generator getGenerator(int idPartida, String nombre);


}
