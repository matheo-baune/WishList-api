package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.repository.GroupMembersRepository;

@Service
@RequiredArgsConstructor
public class GroupMembersService {
    private final GroupMembersRepository groupMembersRepository;


}
