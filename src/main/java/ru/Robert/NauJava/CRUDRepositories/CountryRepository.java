package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.Robert.NauJava.Entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{
}
