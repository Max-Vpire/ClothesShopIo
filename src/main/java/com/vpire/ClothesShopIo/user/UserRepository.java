package com.vpire.ClothesShopIo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %:keyword% OR u.lastname LIKE %:keyword%")
    List<User> searchByFirstnameAndLastname(@Param("keyword") String keyword);
}
