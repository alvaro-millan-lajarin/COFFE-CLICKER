package Persistence;



import Business.Entidades.Generator;

import java.util.List;

public interface GeneratorDAO {

    void addBasicGenerators(int idPartida);
    List<Generator> getAllGenerators();
    void updateGenerator(String nombre, double precio, double cafesSeg, int multiplicador,
                         double tiempoGeneracion, int costMultiplicador, double incrementCost,
                         int numeroCafeteras, int idPartida);
    int numeroGenerador(Generator generator);


}
