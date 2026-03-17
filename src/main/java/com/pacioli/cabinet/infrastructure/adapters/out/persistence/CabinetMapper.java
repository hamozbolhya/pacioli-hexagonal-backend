package com.pacioli.cabinet.infrastructure.adapters.out.persistence;

import com.pacioli.cabinet.domain.model.Cabinet;
import org.springframework.stereotype.Component;

@Component
public class CabinetMapper {

    // Vers le Domaine (Sortie de BDD)
    public Cabinet toDomain(CabinetEntity entity) {
        if (entity == null) return null;
        return Cabinet.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .ice(entity.getIce())
                .ville(entity.getVille())
                .build();
    }

    // Vers l'Infrastructure (Entrée en BDD)
    public CabinetEntity toEntity(Cabinet domain) {
        if (domain == null) return null;
        return CabinetEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .address(domain.getAddress())
                .phone(domain.getPhone())
                .ice(domain.getIce())
                .ville(domain.getVille())
                .build();
    }
}
