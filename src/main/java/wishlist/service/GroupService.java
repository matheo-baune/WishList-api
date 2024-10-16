package wishlist.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.dto.GroupDTO;
import wishlist.dto.UserDTO;
import wishlist.entity.User;
import wishlist.mapper.GroupMapper;
import wishlist.entity.Group;
import wishlist.mapper.UserMapper;
import wishlist.repository.GroupMembersRepository;
import wishlist.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMembersRepository groupMembersRepository;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDTO)
                .toList();
    }

    public GroupDTO getGroupById(Long id) {
        return groupRepository.findById(id)
                .map(groupMapper::toDTO)
                .orElse(null);
    }

    public List<UserDTO> getAllUsersOfGroup(Long id) {
        List<User> usersList = groupMembersRepository.findUsersByGroupId(id);
        return usersList.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = groupMapper.toEntity(groupDTO);
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDTO(savedGroup);
    }

    public GroupDTO updateGroup(Long id, @Valid GroupDTO groupDTO) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        group.setName(groupDTO.getName());
        group.setCreatedBy(groupDTO.getCreatedBy());
        group.setCode(groupDTO.getCode());

        Group updatedGroup = groupRepository.save(group);
        return groupMapper.toDTO(updatedGroup);

    }

    public boolean deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return !groupRepository.existsById(id);
        }
        return false;
    }
}
