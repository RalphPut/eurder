package com.example.eurder.repository;

import com.example.eurder.domain.customer.Address;
import com.example.eurder.domain.customer.Customer;
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

    private final Map<String, Customer> userDatabase;
    public final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        this.userDatabase = new ConcurrentHashMap<>();

        //Creating an admin
        Customer ralph = new Customer("Ralph", "Put", new Address("Vogelzangstraat", 16
                , "3520", "Zonhoven"), "0474897294", UserRole.ADMIN);
        userDatabase.put(ralph.getUuid(), ralph);
        logger.warn("Admin ralph ID = "+ralph.getUuid());


        Customer regular = new Customer("John", "Doe", new Address("Zuidstraat", 61,
                "3581","Beverlo"), "+3255544478",UserRole.CUSTOMER);
        userDatabase.put(regular.getUuid(),regular);
        logger.warn("Regular customer ID = "+regular.getUuid());

        Customer regular2 = new Customer("Hanne","Stevens",new Address("Voorstraat",22,
                "3582","Beringen"),"+12545478",UserRole.CUSTOMER);
    }

    public boolean existsInDatabase(String uuid) {
        return userDatabase.containsKey(uuid);
    }

    public Customer findByid(String uuid) {
        var foundUser = this.userDatabase.get(uuid);
        if (foundUser == null) {
            logger.warn("This user does not exist!");
            throw new IllegalArgumentException(String.format("User with id %s does not exist!", uuid));
        }
        return foundUser;
    }

    public List<Customer> findAllCustomers() {
        return userDatabase.values().stream()
                .filter(user -> user.getUserRole() == UserRole.CUSTOMER)
                .collect(Collectors.toList());
    }

    public void registerCustomer(Customer user) {
        if (existsInDatabase(user.getUuid())) {
           throw new IllegalArgumentException();
        }
        userDatabase.put(user.getUuid(), user);
    }
}
