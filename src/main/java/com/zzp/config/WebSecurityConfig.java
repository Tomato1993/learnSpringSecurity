package com.zzp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zzp.authentication.MyAuthenticationFailHandler;
import com.zzp.authentication.MyAuthenticationSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    /**
     * 注入 自定义的  登录成功处理类
     */
    @Autowired
    private MyAuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myFailHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .and()
                .formLogin()
                	.loginPage("/login")
                	.loginProcessingUrl("/loginUser")
                	.successForwardUrl("/user")
                	.successHandler(mySuccessHandler)
                	.failureHandler(myFailHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//                .and()
//                .csrf().disable();
        //这里不用自定义的认证过滤
        //http.addFilterAt(customFromLoginFilter(), UsernamePasswordAuthenticationFilter.class);
                
    }
    
    /**
      * 自定义认证过滤器
     */
    private CustomFromLoginFilter customFromLoginFilter() {
        return new CustomFromLoginFilter("/login");
    }
    


}
