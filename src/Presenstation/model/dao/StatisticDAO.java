package Presenstation.model.dao;

import Presenstation.model.entity.Statistic;

import java.util.List;

public interface StatisticDAO {

    void addEstadistica(Statistic estadistica);
    void updateEstadistica(Statistic estadistica);
    void deleteEstadistica(Statistic estadistica);
    Statistic getEstadistica(int id);
    List<Statistic> getAllEstadisticas();




}
