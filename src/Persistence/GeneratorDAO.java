package Persistence;



import Business.Entidades.Generator;

import java.util.List;

public interface GeneratorDAO {

    void addGenerator(Generator generator);
    void addBasicGenerators(int idPartida);
    void deleteGenerator( Generator generator);
    Generator getGenerator(int id);
    List<Generator> getAllGenerators();


}
