package com.skryvets.demo.controller;

import com.skryvets.demo.service.TransactionalPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class DemoController {

    final String RESPONSE = "This is an immediate response while I'm doing async stuff...";

    TransactionalPersonService transactionalPersonService;

    public DemoController(final TransactionalPersonService transactionalPersonService) {
        this.transactionalPersonService = transactionalPersonService;
    }

    @GetMapping("/transactional/person/{id}")
    public String transactionalGetPerson(@PathVariable Long id) throws InterruptedException {
        this.transactionalPersonService.triggerGetPersonFromRepoAsync(id);
        log.info("While async sleeps I'm doing other thing here...");
        return RESPONSE;
    }

    @GetMapping("/exception/transactional/person/{id}")
    public String exceptionTransactionalGetPerson(@PathVariable Long id) throws InterruptedException {
        this.transactionalPersonService.triggerGetPersonFromRepoAsyncException(id);
        log.info("While async sleeps I'm doing other thing here...");
        return RESPONSE;
    }

//    @PostMapping("/transactional/person")
//    public void transactionalSavePerson(@RequestBody Person person) throws InterruptedException {
//        this.transactionalPersonService.savePersonAsync(person);
//        log.info("While async sleeps I'm doing other thing here...");
//    }

}
