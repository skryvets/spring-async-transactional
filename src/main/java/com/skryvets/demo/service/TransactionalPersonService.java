package com.skryvets.demo.service;

import com.skryvets.demo.entity.Person;
import com.skryvets.demo.repository.PersonRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransactionalPersonService {

    PersonRepository personRepository;

    public TransactionalPersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    @Async
//    @Transactional
//    public void savePersonAsync(Person person) throws InterruptedException {
//        log.info("saving person into repository - {}", person);
//        this.personRepository.save(person);
//
//        log.info("doing async stuff...");
//        Thread.sleep(5000);
//        log.info("finished doing async stuff");
//    }

    @Async
    @Transactional
    public void triggerGetPersonFromRepoAsync(Long id) throws InterruptedException {

        Optional<Person> person = this.personRepository.findById(id);
        log.info("received person from repository - {}", person);

        log.info("doing async stuff...");
        Thread.sleep(5000);
        log.info("finished doing async stuff");
    }

    @Async
    public void triggerGetPersonFromRepoAsyncException(Long id) throws InterruptedException {

        Optional<Person> person = this.personRepository.findByIdTransactional(id);
        log.info("received person from repository - {}", person);

        log.info("doing async stuff...");
        Thread.sleep(5000);
        log.info("finished doing async stuff");
    }
}
