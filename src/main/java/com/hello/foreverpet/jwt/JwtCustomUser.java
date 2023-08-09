package com.hello.foreverpet.jwt;

// lombok 사용
// Security 에서 사용되는 User 에서 파라미터를 추가함.
//@Data         // constructor 도중 에러가 발생하므로 사용하지 않음

import lombok.Data;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class JwtCustomUser extends User {

    private String userNickName;

    public JwtCustomUser(String username,String password, Collection authorities, String userNickName) {
        super(username, password
                , authorities);
        this.userNickName = userNickName;
    }
}