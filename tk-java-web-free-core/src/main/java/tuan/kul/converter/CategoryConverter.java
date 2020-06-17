package tuan.kul.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tuan.kul.dto.CategoryDto;
import tuan.kul.entity.CategoryEntity;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    public CategoryDto convertToDto(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        CategoryDto result = modelMapper.map(entity, CategoryDto.class);
        return result;
    }
    
    public CategoryEntity convertToEntity(CategoryDto dto) {
        CategoryEntity result = modelMapper.map(dto, CategoryEntity.class);
        return result;
    }
}
