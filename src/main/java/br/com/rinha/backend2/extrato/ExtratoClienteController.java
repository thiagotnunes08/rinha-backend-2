package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
@RestController
public class ExtratoClienteController {

    private final ClienteRepository clienteRepository;

    public ExtratoClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes/{id}/extrato")
    public ExtratoClienteResponsse busca(@PathVariable("id") Long clienteId) {

        var possivelCliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ExtratoClienteResponsse(possivelCliente);

    }
}
