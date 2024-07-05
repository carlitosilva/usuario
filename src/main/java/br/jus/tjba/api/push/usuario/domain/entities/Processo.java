package br.jus.tjba.api.push.usuario.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Table(name = "usuario_processo_sistema")
@Entity(name = "Processo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
/*
Versao utilizando a anotacao EmbeddedId
 */
/*public class Processo {

    @EmbeddedId
    private ProcessoId id;

    @Column(length = 25)
    private String numeroProcesso;
    *//*
            @OneToOne
            @MapsId
            @JoinColumn(name = "usuario_id")
            private Usuario usuario;

            @OneToOne
            @MapsId
            @JoinColumn(name = "sistema_id")
            private Sistema sistema;*//*
 *//*@Id
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;*//*

    public Processo(Usuario usuario, Sistema sistema, String numeroProcesso) {
        this.id = new ProcessoId(usuario.getId(), sistema.getId());
        //this.usuario = usuario;
        //this.sistema = sistema;
        this.numeroProcesso = numeroProcesso;
    }
}*/
public class Processo {

    //@EmbeddedId
    //private ProcessoId id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25)
    private String numeroProcesso;

    @Column(name = "data_publicacao", columnDefinition = "TIMESTAMP")
    private LocalDate dataPublicacao;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Usuario usuario;


    @OneToOne(optional = false)
    @JoinColumn(name = "sistema_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Sistema sistema;

    public Processo(Usuario usuario, Sistema sistema, String numeroProcesso) {
        //this.id = new ProcessoId(usuario.getId(), sistema.getId());
        this.usuario = usuario;
        this.sistema = sistema;
        this.numeroProcesso = numeroProcesso;
    }
}