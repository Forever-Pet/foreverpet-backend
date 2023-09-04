package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.entity.Category;
import com.hello.foreverpet.domain.exception.CategoryNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.repository.CategoryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(String category) {
        Category newCategory = new Category(category);
        categoryRepository.save(newCategory);
    }

    public void deleteCategory(String category) {
        Category deleteCategory = categoryRepository.findbyName(category)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(deleteCategory);
    }

    public Category findByName(String category) {
        return categoryRepository.findbyName(category)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}
