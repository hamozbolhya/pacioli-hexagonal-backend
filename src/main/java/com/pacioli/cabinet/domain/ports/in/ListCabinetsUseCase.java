package com.pacioli.cabinet.domain.ports.in;

import com.pacioli.cabinet.domain.model.Cabinet;

import java.util.List;

public interface ListCabinetsUseCase {
    List<Cabinet> execute();
}
