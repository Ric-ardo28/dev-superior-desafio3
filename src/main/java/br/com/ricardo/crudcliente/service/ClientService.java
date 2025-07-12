package br.com.ricardo.crudcliente.service;

import br.com.ricardo.crudcliente.dto.ClientDTO;
import br.com.ricardo.crudcliente.entities.Client;
import br.com.ricardo.crudcliente.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClientService {

	ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id){
		Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
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
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(LocalDate.parse(dto.getBirthDate()));
		entity.setChildren(dto.getChildren());
		entity = clientRepository.save(entity);
		return new ClientDTO(entity);
	}
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = clientRepository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity.setBirthDate(LocalDate.parse(dto.getBirthDate()));
			entity.setChildren(dto.getChildren());
			entity = clientRepository.save(entity);

			return new ClientDTO(entity);
		}catch (EntityNotFoundException e){
			throw new RuntimeException("Cliente não encontrado");
		}
	}
	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado");
		}
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Erro ao deletar cliente");
		}
	}




}
