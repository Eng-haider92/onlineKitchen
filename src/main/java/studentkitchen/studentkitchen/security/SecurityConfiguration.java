package studentkitchen.studentkitchen.security;


import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{


    String[] resources = new String[]{
        "/","/pictureCheckCode","/include/**",
        "/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                        .antMatchers("/").permitAll()
                        .antMatchers("/api/recipes","/api/recipe/**", "/api/delete/**","/api/updateRecipe/**","/api/users/addUser").permitAll()
                        .antMatchers(resources).permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/signup").permitAll()
                        .antMatchers("/home", "/recipes","/recipes/**","home/**").hasAuthority("USER")
                        .antMatchers("recipes/details","recipes/details/**").hasAuthority("USER")
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login?error=true").defaultSuccessUrl("/home")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .and().logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").and().exceptionHandling()
                        .accessDeniedPage("/access-denied");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                );
                
                http.csrf().disable();
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.
		jdbcAuthentication()
			.usersByUsernameQuery("select email, password,active from user where email=?")
            .authoritiesByUsernameQuery("select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?")
            .dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);    
    }

}


    
