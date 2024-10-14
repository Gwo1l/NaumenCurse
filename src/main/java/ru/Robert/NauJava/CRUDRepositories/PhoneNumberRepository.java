package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.Robert.NauJava.Entities.PhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
}
