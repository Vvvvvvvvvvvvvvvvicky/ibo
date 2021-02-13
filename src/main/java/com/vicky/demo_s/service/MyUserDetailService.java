package com.vicky.demo_s.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vicky.demo_s.mapper.UsersMapper;
import com.vicky.demo_s.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //自定义配置类简单设置实现
//        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
//        return new User("aaa",new BCryptPasswordEncoder().encode("111"),auths);

        //web实例（数据库的）
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username",s);
        Users user = usersMapper.selectOne(wrapper);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_sale");
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }
}
