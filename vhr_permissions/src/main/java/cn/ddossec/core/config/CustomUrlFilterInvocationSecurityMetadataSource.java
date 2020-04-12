package cn.ddossec.core.config;

import cn.ddossec.core.entity.Menu;
import cn.ddossec.core.entity.Role;
import cn.ddossec.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author 唐颖豪
 * @title: MyFilter
 * @projectName vhr_parent
 * @description: 这个类的作用，主要是根据用户传来的请求地址，分析出请求需要的角色
 * @date 2020-04-1216:14
 */
@Component
public class CustomUrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    /* 匹对工具 */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 当前请求的Url地址
        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        // 获取菜单对应的角色
        List<Menu> menus = menuService.getAllMenusWithRole();
        // 遍历进行匹对
        for (Menu menu : menus) {
            if(antPathMatcher.match(menu.getUrl(), requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        /* 如果没有匹对的，就需要登录之后才可以访问 */
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
