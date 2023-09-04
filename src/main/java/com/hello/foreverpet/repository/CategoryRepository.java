package com.hello.foreverpet.repository;

import com.hello.foreverpet.domain.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findbyName(String category);
}
