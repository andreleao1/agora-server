package agoraquestionserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agoraquestionserver.domain.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
