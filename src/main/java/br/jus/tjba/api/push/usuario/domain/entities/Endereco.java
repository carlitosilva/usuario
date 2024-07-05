package br.jus.tjba.api.push.usuario.domain.entities;

import br.jus.tjba.api.push.usuario.model.EnderecoModel;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "endereco_usuario")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String uf;
    private String cep;

    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "usuario_id", nullable = false)
    /*@OneToOne(fetch = FetchType.LAZY)
    @MapsId*/
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Endereco(EnderecoModel endereco) {
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
        //this.usuario = new Usuario(endereco.usuario());
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void atulizarEndereco(EnderecoModel endereco) {
        if (endereco.rua() != null) {
            this.rua = endereco.rua();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }

        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
    }
}
