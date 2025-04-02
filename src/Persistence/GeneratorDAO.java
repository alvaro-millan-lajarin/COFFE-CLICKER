package Persistence;

import Business.entity.Generator;

import java.util.List;

public interface GeneratorDAO {

    void addGenerator(Generator generator);
    void updateGenerator(Generator generator);
    void deleteGenerator( Generator generator);
    Generator getGenerator(int id);
    List<Generator> getAllGenerators();


}
