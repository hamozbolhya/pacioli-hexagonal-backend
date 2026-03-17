package com.pacioli.cabinet.domain.ports.in;
import com.pacioli.cabinet.domain.model.Cabinet;

public interface CreateCabinetUseCase {
    Cabinet execute(Cabinet cabinet);
}
