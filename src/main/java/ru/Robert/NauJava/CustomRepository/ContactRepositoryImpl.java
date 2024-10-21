package ru.Robert.NauJava.CustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.Robert.NauJava.Entities.Contact;

import java.util.List;
@Repository
public class ContactRepositoryImpl implements ContactRepositoryCustom {
    private final EntityManager entityManager;
    @Autowired
    public ContactRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public List<Contact> findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);

        Predicate namePredicate = criteriaBuilder.equal(contactRoot.get("name"), name);

        criteriaQuery.select(contactRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Contact> findByCountry(String country) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);

        Predicate countryPredicate = criteriaBuilder.equal(contactRoot.get("country").get("name"), country);

        criteriaQuery.select(contactRoot).where(countryPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
