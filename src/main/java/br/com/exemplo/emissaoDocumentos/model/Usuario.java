package br.com.exemplo.emissaoDocumentos.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "USUARIO_SEQ")
@Getter
@Setter
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USUARIO_SEQ")
	private Long id;

	@Size(max = 11)
	@Column(name = "NUMERO_JCE")
	private String numeroJCE;

	@Size(max = 25)
	@Column(name = "PRIMEIRO_NOME")
	private String primeiroNome;

	@Size(max = 25)
	@Column(name = "SEGUNDO_NOME")
	private String segundoNome;

	@Lob
	@Column(name = "DIGITAL")
	private byte[] digital;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "PFIS", targetEntity = PessoaFisica.class) })
	private Pessoa pessoa;

	@Transient
	private Perfil perfil;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
