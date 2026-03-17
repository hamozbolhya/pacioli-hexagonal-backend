package com.example.paciolibackend.Cabinet.domain.ports.out;

import com.example.paciolibackend.Cabinet.domain.model.Cabinet;

import java.util.List;
import java.util.Optional;

public interface CabinetRepositoryPort {
    Cabinet save(Cabinet cabinet);
    Optional<Cabinet> findById(Long id);
    List<Cabinet> findAll();
}
