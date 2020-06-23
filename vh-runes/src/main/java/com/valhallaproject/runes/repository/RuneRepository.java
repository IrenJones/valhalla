package com.valhallaproject.runes.repository;

import com.valhallaproject.runes.model.Rune;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuneRepository extends CrudRepository<Rune, Long> {
}
