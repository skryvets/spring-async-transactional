package com.skryvets.demo.repository;

import com.skryvets.demo.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional
    @Query("select p from Person p where p.id = :id")
    Optional<Person> findByIdTransactional(@Param("id") Long aLong);
}
