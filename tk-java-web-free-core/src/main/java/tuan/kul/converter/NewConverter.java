package tuan.kul.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tuan.kul.dto.NewDto;
import tuan.kul.entity.NewEntity;

@Component
public class NewConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    public NewDto convertToDto(NewEntity entity) {
        if (entity == null) {
            return null;
        }
        NewDto result = modelMapper.map(entity, NewDto.class);
        return result;
    }
    
    public NewEntity convertToEntity(NewDto dto) {
        NewEntity result = modelMapper.map(dto, NewEntity.class);
        return result;
    }
}
