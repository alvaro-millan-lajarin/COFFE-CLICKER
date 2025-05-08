package Persistence;



import Business.Entidades.Generator;

import java.util.List;

public interface GeneratorDAO {

    void addBasicGenerators(int idPartida);
    List<Generator> getAllGenerators();


}
