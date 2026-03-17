package com.pacioli.cabinet.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCabinetRepository extends JpaRepository<CabinetEntity, Long> {
    // Spring Data JPA va générer automatiquement le code pour save(), findById(), etc.

    // Tu pourras ajouter plus tard des méthodes personnalisées, par exemple :
    // Optional<CabinetEntity> findByIce(String ice);
}
