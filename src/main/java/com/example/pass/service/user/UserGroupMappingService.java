package com.example.pass.service.user;

import com.example.pass.repository.user.UserGroupMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupMappingService {

    private final UserGroupMappingRepository userGroupMappingRepository;

    public List<String> getAllUserGroupIds(){
        // user group id를 중복없이 user group id 역순으로 조회.
        return userGroupMappingRepository.findDistinctUserGroupId();
    }
}
