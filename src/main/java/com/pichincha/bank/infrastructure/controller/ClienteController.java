package com.pichincha.bank.infrastructure.controller;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.domain.request.RegisterClienteRequest;
import com.pichincha.bank.domain.request.UpdateClienteRequest;
import com.pichincha.bank.domain.service.ClienteService;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping(value = "/registrar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegisterClienteRequest> registrarCliente(@RequestBody RegisterClienteRequest request) throws RestException {
        clienteService.saveCliente(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/obtener", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> obtenerCliente(@RequestHeader int idCliente) throws RestException {
        return new ResponseEntity<>(clienteService.findCliente(idCliente), HttpStatus.FOUND);
    }

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ClienteEntity>> listarCliente() throws RestException {
        return new ResponseEntity<>(clienteService.getAllCliente(), HttpStatus.OK);
    }

    @PatchMapping(value = "/actualizar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody UpdateClienteRequest request) throws RestException {
        clienteService.updateCliente(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> eliminarCliente(@RequestHeader int idCliente) throws RestException {
        clienteService.deleteCliente(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
