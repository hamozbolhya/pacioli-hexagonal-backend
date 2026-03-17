package com.pacioli.cabinet.domain.model;

import com.pacioli.cabinet.domain.exception.DomainException;
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
    private final String ice;
    private final String ville;

    public void validate() {
        if (this.ice == null || this.ice.length() < 10) {
            throw new DomainException("ICE invalide pour le cabinet " + name);
        }
    }
}
