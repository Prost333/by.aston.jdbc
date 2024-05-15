package by.aston.jdbc.mapper;

import by.aston.jdbc.dto.RatesReq;
import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.City;
import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.entity.Rates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatesMapper {

    @Mapping(source = "city.name", target = "city")
    RatesResp toResponse(Rates rates);

    @Mapping(target = "id", ignore = true)
    Rates toRequest(RatesReq ratesReq);

    default String map(City city) {
        return city.getName();
    }
    default String map(NameRates nameRates) {
        return nameRates.getName();
    }

    default NameRates map(String name) {
        NameRates nameRates = new NameRates();
        nameRates.setName(name);
        return nameRates;
    }
    default City mapString(String name) {
        City city = new City();
        city.setName(name);
        return city;
    }

}
