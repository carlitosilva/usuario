package br.jus.tjba.api.push.usuario.domain.entities;

import br.jus.tjba.api.push.usuario.model.UsuarioAtualizacaoModel;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(length = 14)
    private String cpf;
    private String login;
    private String senha;
/*    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "usuario")
    private Endereco endereco;*/

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;

    public Usuario(UsuarioModel usuario) {
        this.nome = usuario.nome();
        this.cpf = usuario.cpf();
        this.login = usuario.login();
        this.senha = usuario.senha();
        //this.endereco = new Endereco(usuario.endereco());
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizar(UsuarioAtualizacaoModel usuario) {

        if (usuario.nome() != null) {
            this.nome = usuario.nome();
        }
        if (usuario.cpf() != null) {
            this.cpf = usuario.cpf();
        }
        if (usuario.endereco() != null) {
            this.endereco.atulizarEndereco(usuario.endereco());
        }
    }
}
