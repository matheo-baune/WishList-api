package wishlist.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.dto.GroupDTO;
import wishlist.mapper.GroupMapper;
import wishlist.entity.Group;
import wishlist.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

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

    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = groupMapper.toEntity(groupDTO);
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDTO(savedGroup);
    }

    public boolean deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return !groupRepository.existsById(id);
        }
        return false;
    }

    public GroupDTO updateGroup(Long id, @Valid GroupDTO groupDTO) {
        return groupRepository.findById(id).map(existingGroup -> {
            if (groupDTO.getName() != null) {
                existingGroup.setName(groupDTO.getName());
            }
            if (groupDTO.getCreatedBy() != null) {
                existingGroup.setCreatedBy(groupDTO.getCreatedBy());
            }
            if (groupDTO.getCode() != null) {
                existingGroup.setCode(groupDTO.getCode());
            }
            Group updatedGroup = groupRepository.save(existingGroup);
            return groupMapper.toDTO(updatedGroup);
        }).orElse(null);
    }
}
