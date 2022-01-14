package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.UserViewDto;
import com.training.vpalagin.project.model.User;

public interface UserConverter {
    User convertFromViewDto(UserViewDto userViewDto);
    UserViewDto convertToViewDto(User userViewDto);
}
