package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.entity.Authority;
import com.hello.foreverpet.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorityJpaRepository extends JpaRepository<Authority, String> {

    @Query(value = "select * from authority a where authority_name = :role" , nativeQuery = true)
    Optional<Authority> findAuthRole(@Param("role") String role);
}
