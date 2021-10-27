package com.example.eurder.repository;

import com.example.eurder.domain.customer.Address;
import com.example.eurder.domain.customer.User;
import com.example.eurder.domain.customer.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final Map<String, User> userDatabase;
    public final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        this.userDatabase = new ConcurrentHashMap<>();
        User user = new User("Ralph","Put",new Address("Vogelzangstraat",16
        ,"3520","Zonhoven"),"0474897294", UserRole.CUSTOMER);
        userDatabase.put(user.getUuid(),user);

    }

    public boolean existsInDatabase(String uuid){
        return userDatabase.containsKey(uuid);
    }

    public User findByid(String uuid){
        var foundUser = this.userDatabase.get(uuid);
        if (foundUser==null){
            throw new IllegalArgumentException(String.format("User with id %s does not exist!",uuid));
        }
        return foundUser;
    }

    public List<User> findAllCustomers(){
        return userDatabase.values().stream()
                .filter(user -> user.getUserRole()==UserRole.CUSTOMER)
                .collect(Collectors.toList());
    }

    public void registerCustomer(User user){
        if (!existsInDatabase(user.getUuid())){
            userDatabase.put(user.getUuid(),user);
        }
    }
}
