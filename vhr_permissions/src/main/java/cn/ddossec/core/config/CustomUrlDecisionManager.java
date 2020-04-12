package cn.ddossec.core.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 30315
 * @title: MyDecisionManager
 * @projectName vhr_parent
 * @description: 这个类的作用，判断当前用户是否具备，需要的角色，如果具备请求继续往下跑，不具备抛出异常
 * @date 2020-04-1217:29
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication 当前登录用户的信息
     * @param o              请求对象
     * @param collection     需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            /* 需要的角色 */
            String neeRole = configAttribute.getAttribute();
            // 如果是登录
            if ("ROLE_LOGIN".equals(neeRole)) {
                // 判断是否登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录,请登录!");
                }
            } else {
                // 已经登录
                return;
            }
            // 获取当前登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 如果相等就说明，具备需要的角色中的一个
                if (authority.getAuthority().equals(neeRole)) {
                    return;
                }
            }
        }
        // 到这里，就说明需要的角色一个没有，或者需要登录又没有登录
        throw new AccessDeniedException("权限不足,请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
