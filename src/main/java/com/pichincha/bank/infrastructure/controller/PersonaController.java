package com.pichincha.bank.infrastructure.controller;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.domain.request.RegisterPersonaRequest;
import com.pichincha.bank.domain.request.UpdatePersonaRequest;
import com.pichincha.bank.domain.service.PersonaService;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping(value = "/registrar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegisterPersonaRequest> registrarPersona(@RequestBody RegisterPersonaRequest request) throws RestException {
        personaService.savePersona(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/obtener", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Persona> obtenerPersona(@RequestHeader int idPersona) throws RestException {
        return new ResponseEntity<>(personaService.findPersona(idPersona), HttpStatus.FOUND);
    }

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PersonaEntity>> listarPersona() throws RestException {
        return new ResponseEntity<>(personaService.getAllPersona(), HttpStatus.OK);
    }

    @PatchMapping(value = "/actualizar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Persona> actualizarPersona(@RequestBody UpdatePersonaRequest request) throws RestException {
        personaService.updatePersona(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> eliminarPersona(@RequestHeader int idPersona) throws RestException {
        personaService.deletePersona(idPersona);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
