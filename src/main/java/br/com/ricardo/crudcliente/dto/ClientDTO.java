package br.com.ricardo.crudcliente.dto;

import br.com.ricardo.crudcliente.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;
	private String cpf;
	private Double income;
	@PastOrPresent
	private String birthDate;
	private Integer children;

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.income = entity.getIncome();
		this.birthDate = entity.getBirthDate().toString(); // Convert LocalDate to String
		this.children = entity.getChildren();
	}
}
