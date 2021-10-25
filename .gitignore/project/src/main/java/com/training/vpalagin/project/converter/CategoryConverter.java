package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.CategoryDto;
import com.training.vpalagin.project.model.Category;

public interface CategoryConverter {
    Category convertFromDto(CategoryDto categoryDto);

    CategoryDto convertToDto(Category category);
}
