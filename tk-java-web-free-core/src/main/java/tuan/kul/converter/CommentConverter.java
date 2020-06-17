package tuan.kul.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tuan.kul.dto.CommentDto;
import tuan.kul.entity.CommentEntity;

@Component
public class CommentConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    public CommentDto convertToDto(CommentEntity entity) {
        CommentDto result = modelMapper.map(entity, CommentDto.class);
        return result;
    }
    
    public CommentEntity convertToEntity(CommentDto dto) {
        CommentEntity result = modelMapper.map(dto, CommentEntity.class);
        return result;
    }
}
