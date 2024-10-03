package vn.edu.uit.IE104.services;

<<<<<<< HEAD
=======

>>>>>>> 526ff449d124dcd742917d265073bdf6c926e383
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.uit.IE104.entity.Role;
import vn.edu.uit.IE104.entity.User;
import vn.edu.uit.IE104.repository.UserRepository;

<<<<<<< HEAD
=======

>>>>>>> 526ff449d124dcd742917d265073bdf6c926e383
import java.util.Collections;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new Exception("Email đã được sử dụng");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        return userRepository.save(user);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    public User getUser(String email) {
        return  userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void verifyUser(Long userId) {
        User user = userRepository.findById(userId).get();
        user.setVerifyEmailStatus(true);
        userRepository.save(user);
    }
=======

@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;


   public User registerUser(User user) throws Exception {
       if (userRepository.existsByEmail(user.getEmail())){
           throw new Exception("Email đã được sử dụng");
       }
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRole(Role.STUDENT);
       return userRepository.save(user);
   }




   public List<User> getUsers() {
       return userRepository.findAll();
   }


   @Transactional
   public void deleteUser(String email) {
       User theUser = getUser(email);
       if (theUser != null){
           userRepository.deleteByEmail(email);
       }


   }


   public User getUser(String email) {
       return  userRepository.findByEmail(email);
   }


   public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
   }


   public void verifyUser(Long userId) {
       User user = userRepository.findById(userId).get();
       user.setVerifyEmailStatus(true);
       userRepository.save(user);
   }
>>>>>>> 526ff449d124dcd742917d265073bdf6c926e383
}
