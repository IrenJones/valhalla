package com.valhallaproject.runes.repository;

import com.valhallaproject.runes.model.Formula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends CrudRepository<Formula, Long> {
}
