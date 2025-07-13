package br.com.ricardo.crudcliente.controller;

import br.com.ricardo.crudcliente.dto.ClientDTO;
import br.com.ricardo.crudcliente.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Valid
@RestController
@RequestMapping("/clients")
public class ClientController {


	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(clientService.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
		dto = clientService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto
						.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
		return ResponseEntity.ok(clientService.update(id, dto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}


}
