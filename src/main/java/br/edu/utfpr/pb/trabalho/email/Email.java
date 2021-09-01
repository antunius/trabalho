package br.edu.utfpr.pb.trabalho.email;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Email implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String host;

	@NotNull
	@Column(nullable = false)
	private Integer port;

	@NotEmpty
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String remetente;

	@ElementCollection
	private Set<String> properties;

	private Boolean padrao = false;

	@JsonIgnore
	public void loadLazy() {
		this.getProperties().size();
	}

	public Boolean getPadrao() {
		return padrao == null ? Boolean.FALSE : padrao;
	}

	protected String password() {
		return password;
	}

}
