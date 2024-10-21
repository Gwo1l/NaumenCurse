package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Robert.NauJava.Entities.Address;

@RepositoryRestResource
public interface AddressRepository extends CrudRepository<Address, Long> {
}
