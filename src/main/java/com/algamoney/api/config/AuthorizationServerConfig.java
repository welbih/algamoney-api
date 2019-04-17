package com.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {  // Configurar a Aplicação, o cliente. 
		clients.inMemory()  // Deixar me memória que vai servir para o cliente angular
			.withClient("angular") //NOme do cliente
			.secret("@ngul@r0") // senha do cliente
			.scopes("read", "write") // Escopo do cliente. Para limitar o acesso deste cliente: exem. angular
			.authorizedGrantTypes("password", "refresh_token") // Password A aplicação recebe o usuário e senha do usuário. E enviar para pegar o access token. Só feito quando você tem confiança na aplicação
			// REFRESH_TOKEN usado para nos dar um novo access token
//			.accessTokenValiditySeconds(1800); //Quantos segundos esse token vai ficar ativo 1800 = 30min
			.accessTokenValiditySeconds(30)
			.refreshTokenValiditySeconds(3600 * 24) // Um dia para expirar o refresh token
		.and()
			.withClient("mobile") 
			.secret("m0b1l30") 
			.scopes("read") 
			.authorizedGrantTypes("password", "refresh_token") 
			.accessTokenValiditySeconds(3600 * 24)
			.refreshTokenValiditySeconds(3600 * 24);
		
	}
	
	@Override 
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore()) // Armazenar o token
			.accessTokenConverter(accessTokenConverter()) 
			.reuseRefreshTokens(false) // Sempre que pedir um access token usando refresh token. Um novo refresh token será enviado. A ideia é que ele não se deslogue. 
			.authenticationManager(authenticationManager); // VAlidar se está tudo certo com usuário e senha.
			
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("algaworks"); // Senha que valida o Token
		return accessTokenConverter;
	}

	@Bean  
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
//		return new InMemoryTokenStore(); // Após o uso do JWT não é necessário armarzenar. // Esse retorno serve para armazenar o token em memória.
	}
	
}
