package backend.spectrum.dguonoff.domain.user.service;

import backend.spectrum.dguonoff.DAO.User;
import backend.spectrum.dguonoff.domain.user.dto.UserInfoDTO;
import backend.spectrum.dguonoff.global.statusCode.ErrorCode;
import backend.spectrum.dguonoff.domain.user.exception.UserNotFoundException;
import backend.spectrum.dguonoff.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //유저 조회 함수
    public User findUser(String userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_EXIST_USER));

        return user;
    }

    //마스터 관리자 조회 및 권한 확인 함수
//    public void checkMasterAdmin(String userId) {
//        User user = userRepository
//                .findById(userId)
//                .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_EXIST_MASTER_ADMIN));
//        Role userRole = user.getRole();
//    }

    //관리자 권한 부여 함수
    public void changeRoleToAdmin(User targetUser) {
        userRepository.changeRoleToAdmin(targetUser.getId());
    }

    // 모든 유저 정보 얻어오는 메서드
    public List<UserInfoDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(user-> UserInfoDTO.builder()
                    .sid(user.getSid())
                    .id(user.getId())
                    .major(user.getMajor())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build())
                .collect(Collectors.toList());
    }
}
