package pl.benzo.enzo.server.api.model.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.model.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDto convertToUserDto(UserEntity userEntity){
        final UserDto userDto = new UserDto();

        userDto.setName(userEntity.getName());
        userDto.setPesel(userEntity.getPesel());
        userDto.setPhone(userEntity.getPhone());
        userDto.setLastName(userEntity.getLastName());

        return userDto;
    }
}
