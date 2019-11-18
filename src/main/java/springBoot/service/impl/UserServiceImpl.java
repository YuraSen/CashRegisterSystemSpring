package springBoot.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springBoot.domain.User;
import springBoot.entity.UserEntity;
import springBoot.exception.EntityNotFoundRuntimeException;
import springBoot.exception.InvalidDataForPaginationRuntimeException;
import springBoot.exception.UserExistRuntimeException;
import springBoot.repository.UserRepository;
import springBoot.service.UserService;
import springBoot.service.mapper.UserMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder, UserMapper mapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.warn("User is also exist");
            throw new UserExistRuntimeException("User is also exist");
        }

        String encoded = encoder.encode(user.getPassword());
        User userWithEncodedPass = new User(user, encoded);

        UserEntity entity = userRepository.save(mapper.userToUserEntity(userWithEncodedPass));

        return mapper.userEntityToUser(entity);
    }

    @Override
    public User login(String username, String password) {
        String encodedPassword = encoder.encode(password);
        Optional<UserEntity> entity = userRepository.findByUsername(username);

        UserEntity userEntity = entity.
                orElseThrow(() -> {
                    log.warn("User don't find by name");
                    throw new EntityNotFoundRuntimeException("User don't find by name");
                });

        if (userEntity.getPassword().equals(encodedPassword)) {
            return mapper.userEntityToUser(entity.get());
        } else {
            log.warn("Uncorrected password");
            throw new EntityNotFoundException("Uncorrected password");
        }
    }

    @Override
    public List<User> findAll(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            log.error("Invalid number of user pagination");
            throw new InvalidDataForPaginationRuntimeException("Invalid number of user pagination");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<UserEntity> result = userRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::userEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return userRepository.count();
    }
}
