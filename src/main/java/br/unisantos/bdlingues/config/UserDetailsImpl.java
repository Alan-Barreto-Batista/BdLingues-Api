package br.unisantos.bdlingues.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
	}

	public boolean hasRole(String perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil));
	}

	public UserDetailsImpl(Long id, String login, String senha, Boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(admin ? "ROLE_ADMIN" : "ROLE_CLIENTE"));
		this.authorities = new ArrayList<>(setAuths);
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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

	@Override
	public String toString() {
		return "UserDetailsImpl [id=" + id + ", login=" + login + ", senha=" + senha + ", authorities=" + authorities
				+ "]";
	}
}
