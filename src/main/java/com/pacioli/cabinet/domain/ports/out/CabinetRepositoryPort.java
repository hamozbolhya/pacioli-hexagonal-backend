package com.pacioli.cabinet.domain.ports.out;

import com.pacioli.cabinet.domain.model.Cabinet;

import java.util.List;
import java.util.Optional;

public interface CabinetRepositoryPort {
    Cabinet save(Cabinet cabinet);
    Optional<Cabinet> findById(Long id);
    List<Cabinet> findAll();
}
