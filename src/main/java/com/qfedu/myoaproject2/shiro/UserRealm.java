package com.qfedu.myoaproject2.shiro;

import com.qfedu.myoaproject2.pojo.Resource;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.service.ResourceService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserRealm
 */

public class UserRealm extends AuthorizingRealm {

    private ResourceService resourceService;

    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 配置Shiro授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();//当前用户拥有的权限统统存在集合info内，前端传来的权限请求会进行匹配，有与否用0|1表示
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");//查询当前用户的所有拥有权限
        List<Resource> resources = resourceService.selectByUid(user.getId());
        for(Resource re : resources){ //遍历权限集合，存入info中
            info.addStringPermission(re.getPer());
        }
        return info;
    }

    /**
     * 配置Shiro认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;//1获取令牌
        if (token.getUsername().length() > 0) {//外界已经进行过登录校验，此处只需要进行对象封装
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());//存储到Session  （此步已在controller完成，也可选择在此完成）
            return info;
        }
        return null;
    }

}
