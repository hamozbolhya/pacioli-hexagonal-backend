package com.example.paciolibackend.Cabinet.domain.ports.in;
import com.example.paciolibackend.Cabinet.domain.model.Cabinet;

public interface CreateCabinetUseCase {
    Cabinet execute(Cabinet cabinet);
}
