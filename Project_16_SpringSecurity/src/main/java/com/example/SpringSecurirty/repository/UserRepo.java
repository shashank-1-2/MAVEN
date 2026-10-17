   package com.example.SpringSecurirty.repository;

   import org.springframework.data.jpa.repository.JpaRepository;

   import com.example.SpringSecurirty.model.Users;


   public interface UserRepo extends JpaRepository<Users, Integer> {
       Users findByUsername(String username);
   }