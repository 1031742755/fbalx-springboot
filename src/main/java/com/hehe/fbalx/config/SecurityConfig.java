package com.hehe.fbalx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    // 配置自定义的用户信息加载逻辑
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("root".equals(username)) {
                // 使用 BCrypt 加密密码
                String encodedPassword = passwordEncoder().encode("huikeroot");  // 对明文密码加密
                return User.withUsername("root")
                        .password(encodedPassword)  // 使用加密后的密码
                        .roles("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    // 配置安全策略
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/index", "GET")).permitAll()  // 放行登录页面等静态资源
                .anyRequest().authenticated()  // 其他请求需要认证
                .and()
                .formLogin()
                .loginPage("/index")  // 自定义登录页面的URL
                .loginProcessingUrl("/login")  // 提交用户名和密码的URL
                .defaultSuccessUrl("/toggleTask", true)  // 登录成功后的页面
                .failureUrl("/index?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll();  // 允许注销
        return http.build();
    }

    // 配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}