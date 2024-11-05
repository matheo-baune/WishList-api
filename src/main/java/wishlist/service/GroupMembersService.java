package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.entity.Group;
import wishlist.entity.GroupMember;
import wishlist.entity.User;
import wishlist.repository.GroupMembersRepository;
import wishlist.repository.GroupRepository;
import wishlist.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class GroupMembersService {
    private final GroupMembersRepository groupMembersRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public boolean joinGroup(String groupCode, Long userId) {
        Group group = groupRepository.findByCode(groupCode).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (group != null && user != null) {
            GroupMember groupMember = new GroupMember();
            groupMember.setGroup(group);
            groupMember.setUser(user);
            groupMembersRepository.save(groupMember);
            return true;
        }
        return false;
    }

}
