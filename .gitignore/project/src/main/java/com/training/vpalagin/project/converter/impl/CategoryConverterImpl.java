package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.CategoryConverter;
import com.training.vpalagin.project.dto.CategoryDto;
import com.training.vpalagin.project.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public Category convertFromDto(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    @Override
    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryDto.getName());
        return categoryDto;
    }
}
