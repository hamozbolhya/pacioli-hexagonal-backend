package com.pacioli.cabinet.infrastructure.adapters.in.web;

import com.pacioli.cabinet.domain.model.Cabinet;
import com.pacioli.cabinet.domain.ports.in.CreateCabinetUseCase;
import com.pacioli.cabinet.domain.ports.in.ListCabinetsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabinets")
@RequiredArgsConstructor
public class CabinetController {

    private final CreateCabinetUseCase createCabinetUseCase;
    private final ListCabinetsUseCase listCabinetsUseCase;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PACIOLI')") // Seul le Super Admin peut créer
    public ResponseEntity<Cabinet> createCabinet(@RequestBody Cabinet cabinet) {
        Cabinet created = createCabinetUseCase.execute(cabinet);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PACIOLI')") // Ou une autre autorité selon tes besoins
    public ResponseEntity<List<Cabinet>> listCabinets() {
        List<Cabinet> cabinets = listCabinetsUseCase.execute();
        return ResponseEntity.ok(cabinets);
    }
}