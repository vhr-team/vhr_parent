package cn.ddossec.core.config;

import cn.ddossec.core.entity.Hr;
import cn.ddossec.core.controller.UserService;
import cn.ddossec.core.utils.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 30315
 * @title: SecurityConfig
 * @projectName vhr_parent
 * @description: TODO
 * @date 2020-04-0615:42
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomUrlFilterInvocationSecurityMetadataSource customUrlFilterInvocationSecurityMetadataSource;
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * 登录不用经过SpringSecurity拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    /**
     * 登录成功，退出成功
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* 所有的请求都需要认证之后才可以访问 */
                // .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customUrlFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                /* 表单登录 */
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                /* 登录处理的Url */
                .loginProcessingUrl("/doLogin")
                /* 登录页面 */
                .loginPage("/login")
                /* 登录成功的回调 */
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();

                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);

                        ResponseResult success = ResponseResult.success("登录成功!", hr);
                        String res = new ObjectMapper().writeValueAsString(success);

                        out.write(res);
                        out.flush();
                        out.close();
                    }
                })
                /* 登录失败的回调 */
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();

                        ResponseResult error = ResponseResult.error("登录失败!");
                        if (e instanceof LockedException) {
                            error = ResponseResult.error("账户被锁定,请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            error = ResponseResult.error("密码过期,请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            error = ResponseResult.error("账户过期,请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            error = ResponseResult.error("账户被禁用,请联系管理员!");
                        } else if (e instanceof BadCredentialsException) {
                            error = ResponseResult.error("用户名或密码输入错误,请重新输入!");
                        }

                        String res = new ObjectMapper().writeValueAsString(error);

                        out.write(res);
                        out.flush();
                        out.close();
                    }
                })
                /* 这里配置的接口都可以直接返回 */
                .permitAll()
                .and()
                /* 注销登录 */
                .logout()
                /* 注销成功的回调 */
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(ResponseResult.success("注销成功!")));

                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable()
                // 没有登录，没有认证，没有权限，不要重定向，异常处理
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();

                ResponseResult error = ResponseResult.error("访问失败!");
                if (e instanceof InsufficientAuthenticationException) {
                    error = ResponseResult.error("请求失败,请联系管理员!");
                }
                String res = new ObjectMapper().writeValueAsString(error);
                out.write(res);
                out.flush();
                out.close();
            }
        });
    }
}
