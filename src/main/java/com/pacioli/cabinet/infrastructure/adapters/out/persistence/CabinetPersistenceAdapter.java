package com.pacioli.cabinet.infrastructure.adapters.out.persistence;

import com.pacioli.cabinet.domain.model.Cabinet;
import com.pacioli.cabinet.domain.ports.out.CabinetRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CabinetPersistenceAdapter implements CabinetRepositoryPort {

    private final JpaCabinetRepository repository; // Ton interface JpaRepository classique
    private final CabinetMapper mapper;

    @Override
    public Cabinet save(Cabinet cabinet) {
        CabinetEntity entity = mapper.toEntity(cabinet);
        CabinetEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cabinet> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cabinet> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}