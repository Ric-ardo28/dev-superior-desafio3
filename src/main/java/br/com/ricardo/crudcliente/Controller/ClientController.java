package br.com.ricardo.crudcliente.Controller;

import br.com.ricardo.crudcliente.dto.ClientDTO;
import br.com.ricardo.crudcliente.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(clientService.findById(id));
	}
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(clientService.findAll(pageable));
	}


}
