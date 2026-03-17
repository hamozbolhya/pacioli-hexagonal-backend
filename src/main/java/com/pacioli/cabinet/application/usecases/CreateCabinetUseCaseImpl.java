package com.pacioli.cabinet.application.usecases;

import com.pacioli.cabinet.domain.model.Cabinet;
import com.pacioli.cabinet.domain.ports.in.CreateCabinetUseCase;
import com.pacioli.cabinet.domain.ports.out.CabinetRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCabinetUseCaseImpl implements CreateCabinetUseCase {

    private final CabinetRepositoryPort cabinetRepositoryPort;

    public CreateCabinetUseCaseImpl(CabinetRepositoryPort cabinetRepositoryPort) {
        this.cabinetRepositoryPort = cabinetRepositoryPort;
    }

    @Override
    @Transactional
    public Cabinet execute(Cabinet cabinet) {
        cabinet.validate();
        return cabinetRepositoryPort.save(cabinet);
    }
}
