package tuan.kul.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import tuan.kul.dto.UserDto;
import tuan.kul.entity.UserEntity;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    public UserDto convertToDto (UserEntity entity) {
    	if (StringUtils.isEmpty(entity)) {
    		return null;
    	}
        UserDto result = modelMapper.map(entity, UserDto.class);
        return result;
    }
    
    public UserEntity convertToEntity (UserDto dto) {
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
}
