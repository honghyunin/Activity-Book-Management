package com.crud.test.model.user.repository;

import com.crud.test.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //퍼시스턴스 레이어나, DB나 파일같은 외부 I/O작업을 처리할 때 쓰이는 어노테이션
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(String Id);
    Optional<User> findById(Long idx);
    void delete(User user);
}
