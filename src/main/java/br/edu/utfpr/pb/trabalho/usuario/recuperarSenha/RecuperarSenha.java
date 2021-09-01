package br.edu.utfpr.pb.trabalho.usuario.recuperarSenha;

import br.edu.utfpr.pb.trabalho.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RecuperarSenha implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String chave;

    @Column(unique = true)
    private String chaveEmail;

    @Column(nullable = false)
    private LocalDateTime validade;

    public RecuperarSenha(Usuario usuario) {
        this.usuario = usuario;
        this.chave = UUID.randomUUID().toString();
        this.chaveEmail = String.valueOf( new Random().nextInt(899) + 100 );
        this.validade = LocalDateTime.now().plusMinutes(30);
    }

    public String getChaveEmail() {
        return this.chaveEmail + getId();
    }

}
