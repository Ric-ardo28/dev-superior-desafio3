package br.com.ricardo.crudcliente.service;

import br.com.ricardo.crudcliente.dto.ClientDTO;
import br.com.ricardo.crudcliente.entities.Client;
import br.com.ricardo.crudcliente.repository.ClientRepository;
import br.com.ricardo.crudcliente.service.exceptions.DatabaseException;
import br.com.ricardo.crudcliente.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}


	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		return new ClientDTO(client);

	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> result = clientRepository.findAll(pageable);
		return result.map(ClientDTO::new);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = clientRepository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = clientRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = clientRepository.save(entity);
			return new ClientDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
	}

	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro ao deletar cliente");
		}
	}

	public void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());


	}


}
