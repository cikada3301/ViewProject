package com.training.vpalagin.project.converter.impl;

import com.training.vpalagin.project.converter.UserConverter;
import com.training.vpalagin.project.dto.user.UserViewDto;
import com.training.vpalagin.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User convertFromViewDto(UserViewDto userViewDto) {
        return User.builder()
                .id(userViewDto.getId())
                .firstName(userViewDto.getFirstName())
                .lastName(userViewDto.getLastName())
                .role(userViewDto.getRole())
                .build();
    }

    @Override
    public UserViewDto convertToViewDto(User user) {
        if (user == null) {
            return UserViewDto.builder().build();
        }
        return UserViewDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
