package Persistence;



import Business.Entidades.MilloraComprada;

import java.util.List;

public interface MilloraCompradaDAO {

    void addMilloraComprada(MilloraComprada millora);
    void updateMilloraComprada(MilloraComprada millora);
    void deleteMilloraComprada(MilloraComprada millora);
    MilloraComprada getMilloraComprada(int id);
    List<MilloraComprada> getAllMilloresComprades();




}
