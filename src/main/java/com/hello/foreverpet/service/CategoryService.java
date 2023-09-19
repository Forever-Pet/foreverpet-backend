package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.entity.Category;
import com.hello.foreverpet.domain.exception.AlreadyExistCategoryException;
import com.hello.foreverpet.domain.exception.CategoryNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(String category) {
        Category newCategory = new Category(category);

        Optional<Category> byName = categoryRepository.findByName(category);

        if (byName.isPresent()) {
            throw new AlreadyExistCategoryException(ErrorCode.CATEGORY_ALREADY_EXIST);
        } else {
            categoryRepository.save(newCategory);
        }
    }

    public Category findByName(String category) {
        return categoryRepository.findByName(category)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Transactional
    public void updateCategory(Long id, String category) {
        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        findCategory.updateCategory(category);
    }

    public void deleteCategory(String category) {
        Category deleteCategory = categoryRepository.findByName(category)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(deleteCategory);
    }

}
