package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Robert.NauJava.Entities.Country;

@RepositoryRestResource
public interface CountryRepository extends CrudRepository<Country, Long>{
}
