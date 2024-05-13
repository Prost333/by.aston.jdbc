package by.aston.jdbc.service;

import by.aston.jdbc.entity.City;

import java.sql.SQLException;
import java.util.List;

public interface CityService {
    void save(City c) throws SQLException;

    void delete(City c) throws SQLException;

    City findById(Long id);

    List<City> findAll();
}
