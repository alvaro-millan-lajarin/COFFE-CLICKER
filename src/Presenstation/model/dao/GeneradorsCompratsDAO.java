package Presenstation.model.dao;

import Presenstation.model.entity.GeneradorsComprats;
import java.util.List;

public interface GeneradorsCompratsDAO {
    void addGeneradorComprat(GeneradorsComprats generador);
    void updateGeneradorComprat(GeneradorsComprats generador);
    void deleteGeneradorComprat(GeneradorsComprats generador);
    GeneradorsComprats getGeneradorComprat(int id);
    List<GeneradorsComprats> getAllGeneradorsComprats();
}
