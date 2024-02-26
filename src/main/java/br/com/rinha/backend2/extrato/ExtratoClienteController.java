package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtratoClienteController {

    private final ClienteRepository clienteRepository;

    public ExtratoClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @GetMapping("/clientes/{id}/extrato")
    @Transactional
    public ResponseEntity<?> busca(@PathVariable("id") Long clienteId) {


        if (clienteId > 5 || clienteId < 1){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new SaldoResponse(clienteRepository.buscarPorId(clienteId)));
    }
}
