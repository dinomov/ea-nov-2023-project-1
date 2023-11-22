<<<<<<< HEAD
package com.news.repository;

import com.news.entity.Information;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepo extends ListCrudRepository<Information, Integer> {
    @Override
    <S extends Information> S save(S entity);

    @Override
    void deleteById(Integer id);

    @Override
    Optional<Information> findById(Integer id);

    @Override
    List<Information> findAll();
=======
package com.news.repository;public class InformationRepo {
>>>>>>> origin/news
}
