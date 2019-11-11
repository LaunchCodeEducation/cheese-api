package org.launchcode.cheeseapi.repositories;

import org.launchcode.cheeseapi.models.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CheeseRepository extends JpaRepository<Cheese, Long> {
}
