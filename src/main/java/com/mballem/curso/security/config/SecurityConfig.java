package com.mballem.curso.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mballem.curso.security.domain.PerfilTipo;
import com.mballem.curso.security.service.UsuarioService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
	private static final String MEDICO = PerfilTipo.MEDICO.getDesc();
	private static final String PACIENTE = PerfilTipo.PACIENTE.getDesc();
	
	@Autowired
	private UsuarioService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//acessos publicos liberados
		http.authorizeRequests()
		.antMatchers("/", "/home").permitAll()
		.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
		
		// acessos privados admin
		.antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAuthority(MEDICO)
		.antMatchers("/u/**").hasAuthority(ADMIN)
		
		// acessos privados medicos
		.antMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(MEDICO,ADMIN)
		.antMatchers("/medicos/**").hasAuthority(MEDICO)
		
		// acessos privados especialidades
		.antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(ADMIN,MEDICO)
		.antMatchers("/especialidades/titulo").hasAnyAuthority(ADMIN,MEDICO)
		.antMatchers("/especialidades/**").hasAuthority(ADMIN)
		
		// acessos privados pacientes
		.antMatchers("/pacientes/**").hasAnyAuthority(PACIENTE)
		
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/", true)
		.failureUrl("/login-error").permitAll()
		.and()
		.logout().logoutSuccessUrl("/")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/acesso-negado");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
}
