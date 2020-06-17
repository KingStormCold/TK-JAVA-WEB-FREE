package tuan.kul.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tuan.kul.converter.UserConverter;
import tuan.kul.dto.MyUserDetail;
import tuan.kul.dto.RoleDto;
import tuan.kul.dto.UserDto;
import tuan.kul.entity.UserEntity;
import tuan.kul.repository.UserRepository;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findOne(username);
        UserDto userDTO = userConverter.convertToDto(userEntity);

        if (userDTO == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(RoleDto roleDto : userDTO.getRolesOauth()) {
        	authorities.add(new SimpleGrantedAuthority(roleDto.getRoleId()));
        }

        MyUserDetail myUserDetail = new MyUserDetail(username, userDTO.getPassWord(), true, true, true, true,
                authorities);
        BeanUtils.copyProperties(userDTO, myUserDetail);
        return myUserDetail;
    }
}
