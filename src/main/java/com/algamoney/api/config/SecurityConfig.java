package com.algamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration //Opcional pois a anotação a baixo já contem essa anotação.
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//ResourceServerConfigurerAdapter{

	// Utilizando BASIC
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // Autenticação em memória.
			.withUser("admin") // Usuário
			.password("admin") // Senha
			.roles("ROLE"); // Permissão
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //Autorização das requisições. 
			.antMatchers("/categorias").permitAll() //Preciso estar autenticado para qualquer requisição. Exceto a página de /categorias
			.anyRequest().authenticated()
			.and() 
		.httpBasic().and() //Tipo de autenticação HTTP bASIC
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //Não mantenha estado de sessão.  Sem sessão.
		.csrf().disable(); //Desabilitar o cors
		
	}
	
}
