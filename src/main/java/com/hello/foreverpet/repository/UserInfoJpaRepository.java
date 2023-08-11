package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.dto.response.UserDataResponse;
import com.hello.foreverpet.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserNickname(String nickname);

    Optional<UserInfo> findByUserKakaoId(Long kakaoId);

    Optional<UserInfo> findByUserEmail(String email);

    Optional<UserInfo> findOneWithAuthoritiesByUserEmail(String name);

    Optional<UserInfo> findByUserEmailAndUserDeleteFlag(String email, Boolean flag);
    @Query(value = "SELECT * FROM user_info where user_id = :userId" , nativeQuery = true)
    Optional<UserInfo> findGetUserData(@Param("userId") Long userId);
}
