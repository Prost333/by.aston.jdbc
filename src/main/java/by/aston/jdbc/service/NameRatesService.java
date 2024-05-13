package by.aston.jdbc.service;

import by.aston.jdbc.entity.NameRates;

import java.sql.SQLException;
import java.util.List;

public interface NameRatesService {
    void save(NameRates n) throws SQLException;

    void delete(NameRates n) throws SQLException;

    NameRates findById(Long id);

    List<NameRates> findAll();
}
