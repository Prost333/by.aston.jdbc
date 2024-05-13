package by.aston.jdbc.service.imp;

import by.aston.jdbc.entity.City;
import by.aston.jdbc.repository.CityDao;
import by.aston.jdbc.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImp implements CityService {
    private CityDao cityDao;

    @Override
    public void save(City c) throws SQLException {
        cityDao.addCity(c);
    }

    @Override
    public void delete(City c) throws SQLException {
        cityDao.deleteCity(c.getId());
    }

    @Override
    public City findById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findByAll();
    }

}
