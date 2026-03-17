package com.example.paciolibackend.Cabinet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Cabinet {
    private final Long id;
    private final String name;
    private final String address;
    private final String phone;
    private final String ice;   // Identifiant Commun de l’Entreprise
    private final String ville;

    // Logique métier : Un cabinet peut valider son propre ICE
    public void validate() {
        if (this.ice == null || this.ice.length() < 10) {
            throw new DomainException("ICE invalide pour le cabinet " + name);
        }
    }
}
