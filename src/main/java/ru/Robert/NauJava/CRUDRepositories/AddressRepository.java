package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.Robert.NauJava.Entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
