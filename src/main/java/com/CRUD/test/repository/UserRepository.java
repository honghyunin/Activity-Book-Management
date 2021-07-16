package com.CRUD.test.repository;

import com.CRUD.test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //퍼시스턴스 레이어나, DB나 파일같은 외부 I/O작업을 처리할 때 쓰이는 어노테이션
public interface UserRepository extends JpaRepository<User,Long> {
    public String findById(String id);
}
