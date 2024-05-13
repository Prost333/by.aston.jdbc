package by.aston.jdbc.service;
import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.Rates;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface RatesService {

    void save(Rates r) throws SQLException;

    void delete(Long id) throws SQLException;

    RatesResp findById(Long id);

    List<RatesResp> findAll();


}
