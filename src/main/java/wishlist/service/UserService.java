package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wishlist.Utils;
import wishlist.dto.GiftDTO;
import wishlist.dto.GroupDTO;
import wishlist.dto.UserDTO;
import wishlist.entity.Gift;
import wishlist.entity.Group;
import wishlist.entity.Reservation;
import wishlist.entity.User;
import wishlist.mapper.GiftMapper;
import wishlist.mapper.GroupMapper;
import wishlist.mapper.UserMapper;
import wishlist.repository.GiftRepository;
import wishlist.repository.GroupMembersRepository;
import wishlist.repository.ReservationsRepository;
import wishlist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;
    private final GroupMembersRepository groupMembersRepository;
    private final UserRepository userRepository;
    private final ReservationsRepository reservationsRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username : " + username);
        return new User(
                user.getUsername(),
                user.getPassword()
        );
    }

    public UserDTO createUser(User user) {
        User createdUser = userRepository.save(user);
        return userMapper.toDTO(createdUser);
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

    public List<GiftDTO> getAllGiftOfUserForGroup(Long id,Long groupID) {
        List<Gift> giftDTOList = giftRepository.findGiftsByUserIdAndGroupId(id, groupID);
        return giftDTOList.stream()
                .map(giftMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Utils.updateEntityFromDTO(userDTO, user);

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public boolean reserveGift(Long userId, Long giftId) {
        if (userRepository.existsById(userId) && giftRepository.existsById(giftId)) {
            Reservation reservation = new Reservation();
            reservation.setReserved_by(userId);
            reservation.setGiftId(giftId);
            reservationsRepository.save(reservation);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return !userRepository.existsById(id);
        }
        return false;
    }
}
