package project001CommanPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//we can set our configuration to auth object.
		// here we are using in memory configuration..
		
		auth.inMemoryAuthentication().withUser("test").password("test123").roles("USER");
		auth.inMemoryAuthentication().withUser("test").password("test1234").roles("ADMIN");
		
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
	 return NoOpPasswordEncoder.getInstance();	
	}
	
	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/").hasAnyRole("USER","ADMIN").and().formLogin();
		http.authorizeRequests().antMatchers("/ui").hasRole("ADMIN").and().formLogin();
		
	}

}
