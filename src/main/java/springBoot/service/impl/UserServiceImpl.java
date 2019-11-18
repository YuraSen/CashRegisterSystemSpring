package springBoot.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springBoot.domain.User;
import springBoot.entity.UserEntity;
import springBoot.exception.UserExistException;
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
            throw new UserExistException("User is also exist");
        }

        String encoded = encoder.encode(user.getPassword());
        User userWithEncodedPass = new User(user, encoded);

        UserEntity entity = userRepository.save(mapper.userToUserEntity(userWithEncodedPass));

        return mapper.userEntityToUser(entity);
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = encoder.encode(password);
        Optional<UserEntity> entity = userRepository.findByEmail(email);

        if (!entity.isPresent()) {
            log.warn("There is no user with this e-mail");
            throw new EntityNotFoundException("There is no user with this e-mail");
        } else {
            if (entity.get().getPassword().equals(encodedPassword)) {
                return mapper.mapUserEntityToUser(entity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new EntityNotFoundException("Incorrect password");
            }
        }
    }

    @Override
    public List<User> findAll(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            LOGGER.error("Invalid number of current page or records per page");
            throw new InvalidPaginatingException("Invalid number of current page or records per page");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<UserEntity> result = userRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return userRepository.count();
    }
}

