package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wishlist.dto.GroupDTO;
import wishlist.dto.UserDTO;
import wishlist.entity.Group;
import wishlist.entity.User;
import wishlist.mapper.GroupMapper;
import wishlist.mapper.UserMapper;
import wishlist.repository.GroupMembersRepository;
import wishlist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final GroupMembersRepository groupMembersRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username : " + username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    public List<GroupDTO> getAllGroupOfUser(Long id) {
        List<Group> userGroups = groupMembersRepository.findGroupsByUserId(id);
        return userGroups.stream()
                .map(groupMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return !userRepository.existsById(id);
        }
        return false;
    }
}
