package com.ea.repository;

import com.ea.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends ListCrudRepository<Student, Long> {
    @Query(value = "SELECT * FROM students s JOIN users u ON s.id=u.id WHERE (u.deleted = 0 AND (u.first_name LIKE :name% OR u.last_name LIKE :name%))",
            nativeQuery = true)
    List<Student> findAllByFirstNameOrLastNameLike(@Param("name") String name);
    @Query(value = "SELECT * FROM students s JOIN users u ON s.id=u.id WHERE (u.deleted = 0 AND u.state LIKE :state%)",
            nativeQuery = true)
    List<Student> findAllByStateLike(@Param("state") String state);
    @Query(value = "SELECT * FROM students s JOIN users u ON s.id=u.id WHERE (u.deleted = 0 AND u.city LIKE :city%)",
            nativeQuery = true)
    List<Student> findAllByCityLike(@Param("city") String city);

    List<Student> findAllByMajorContainingIgnoreCase(@Param("major") String major);
}
