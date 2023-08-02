package com.hello.foreverpet;

import com.hello.foreverpet.controller.AuthController;
import com.hello.foreverpet.controller.UserController;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.oauth.kakao.KakaoInfoResponse;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.hello.foreverpet.service.OAuthLoginService;
import com.hello.foreverpet.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.SybaseSqmToSqlAstConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Test
    public void signup(){

        // 준비
        Address address = new Address();
        address.setCity("City");
        address.setStreet("Street");
        address.setZipcode("Zipcode");

        UserSignupRequest userSignupRequest = new UserSignupRequest(
                "nickname1",
                "email@naver.com",
                "password",
                "01012341234",
                address
        );

        // 실행
        Long userNoOne = userService.userSignup(userSignupRequest);

        Boolean memberTestTF = true;

        if(userNoOne < 1L) memberTestTF = false;

        // 단언
        assertEquals(true, memberTestTF);
    }
}
