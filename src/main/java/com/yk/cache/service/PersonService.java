package com.yk.cache.service;

import com.yk.cache.entity.Organization;
import com.yk.cache.entity.OrganizationType;
import com.yk.cache.entity.Person;
import com.yk.cache.exception.ResourceNotFoundException;
import com.yk.cache.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.lang.IgniteFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    private static final AtomicLong ID_GEN = new AtomicLong();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Ignite igniteInstance;

    public Person addPerson(Person person) {
        long id = new Random().nextLong();
        return personRepository.save(id, person);
    }

    public Person updatePerson(Person person, long personId) {
        log.debug( "updatePerson" , personId, person.toString() );
        return personRepository.findById(personId)
                .map(existingPerson -> {
                    existingPerson.setLastName(person.getLastName());
                    existingPerson.setFirstName(person.getFirstName());
                    existingPerson.setAge(person.getAge());
                    return personRepository.save(personId, existingPerson);
                }).orElseGet(() -> {
                    long id = new Random().nextLong();
                    return personRepository.save(id, person);
                });
    }

    public Person getPerson(long id) {
        log.debug("id" ,id);
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> getPersons(String firstName, Integer age) {
        if (firstName != null) {
            return personRepository.findByFirstNameLike(firstName);
        } else if (age != null) {
            return personRepository.selectPersonsByAge(age);
        } else {
            List<Person> target = new ArrayList<>();
            personRepository.findAll().forEach(target::add);
            return target;
        }
    }

    public List<Person> getPersonsByOrganization(String organizationName) {
        List<?> personList = personRepository.selectPersonByOrganization(organizationName);
        return personList.stream().map(p -> mapPerson(p)).collect(Collectors.toList());
    }

    private Person mapPerson(Object l) {
        log.info("PersonService.getPersonsByOrganization: {}", l);
        return personRepository.findById((Long) l).get();
    }


    /**
     * Fills the repository in with some sample data.
     */

    @PostConstruct
    private void populateRepository() {

        IgniteCache<Long, Organization> orgCache = igniteInstance.cache("ORG_CACHE");

        // Clear cache before running the example.
        orgCache.clear();

        // Organizations.
        Organization org1 = Organization.builder().name("ApacheIgnite").type(OrganizationType.NON_PROFIT).id(ID_GEN.incrementAndGet()).build();
        Organization org2 = Organization.builder().name("Other").type(OrganizationType.GOVERNMENT).id(ID_GEN.incrementAndGet()).build();

        orgCache.put(org1.getId(), org1);
        orgCache.put(org2.getId(), org2);

        Map<Long, Person> persons = new TreeMap<>();

        persons.put(1L, Person.builder().id(1L).firstName("John").lastName("Smith").age(30).orgId(org1.getId()).build());
        persons.put(2L, Person.builder().id(2L).firstName("Brad").lastName("Pitt").age(25).orgId(org1.getId()).build());
        persons.put(3L, Person.builder().id(3L).firstName("Mark").lastName("Tomson").age(32).orgId(org1.getId()).build());

        /**
         * Using IgniteDataStreamer to populate the Cache. IgniteDataStreamer should be used for bulk operation
         */
        // Create a streamer to stream words into the cache.
        //https://www.javadoc.io/doc/org.apache.ignite/ignite-core/2.5.0/org/apache/ignite/IgniteDataStreamer.html
        try (IgniteDataStreamer<Long, Person> stmr = igniteInstance.dataStreamer("PersonCache")) {
            stmr.allowOverwrite(true);

            IgniteFuture<?> future = stmr.addData(persons);
            stmr.flush();
            future.get();

        }
        log.info("\n>>> Added " + personRepository.count() + " Persons into the repository.");
    }

    public Iterable<Person> getCustomers() {
        return personRepository.findAll();
    }
}
