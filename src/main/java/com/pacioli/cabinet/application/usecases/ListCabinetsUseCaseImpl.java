package com.pacioli.cabinet.application.usecases;

import com.pacioli.cabinet.domain.model.Cabinet;
import com.pacioli.cabinet.domain.ports.in.ListCabinetsUseCase;
import com.pacioli.cabinet.domain.ports.out.CabinetRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListCabinetsUseCaseImpl implements ListCabinetsUseCase {

    private final CabinetRepositoryPort cabinetRepositoryPort;

    public ListCabinetsUseCaseImpl(CabinetRepositoryPort cabinetRepositoryPort) {
        this.cabinetRepositoryPort = cabinetRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true) // Bonne pratique pour les lectures
    public List<Cabinet> execute() {
        return cabinetRepositoryPort.findAll();
    }
}