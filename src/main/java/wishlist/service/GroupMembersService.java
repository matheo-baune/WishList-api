package wishlist.service;

import org.springframework.stereotype.Service;
import wishlist.repository.GroupMembersRepository;

@Service
public class GroupMembersService {
    private final GroupMembersRepository groupMembersRepository;

    public GroupMembersService(GroupMembersRepository groupMembersRepository) {
        this.groupMembersRepository = groupMembersRepository;
    }
}
