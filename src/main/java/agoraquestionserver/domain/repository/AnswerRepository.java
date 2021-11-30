package agoraquestionserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agoraquestionserver.domain.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
