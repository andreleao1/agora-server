package agoraquestionserver.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import agoraquestionserver.domain.model.Question;
import agoraquestionserver.domain.repository.QuestionRepository;
import agoraquestionserver.domain.service.interfaces.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question save(Question question) {
		if(question.getId() > 0) {
			Question questionFound = find(question.getId());
			BeanUtils.copyProperties(question, questionFound, "id");
			return this.questionRepository.save(questionFound);
		}
		return this.questionRepository.save(question);
	}

	@Override
	public Question find(Long id) {
		return this.questionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public Page<Question> listAll(Pageable pageable) {
		return this.questionRepository.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		find(id);
		this.questionRepository.deleteById(id);		
	}
	
	
}
