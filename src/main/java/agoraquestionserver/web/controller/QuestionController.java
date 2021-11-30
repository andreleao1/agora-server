package agoraquestionserver.web.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agoraquestionserver.domain.model.Answer;
import agoraquestionserver.domain.model.Question;
import agoraquestionserver.domain.service.interfaces.QuestionService;
import agoraquestionserver.web.dto.AnswerDto;
import agoraquestionserver.web.dto.QuestionDto;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private QuestionService questionService;

	@PostMapping
	public ResponseEntity<QuestionDto> save(@Valid @RequestBody Question question) {
		QuestionDto questionDto = modelMapper.map(this.questionService.save(question), QuestionDto.class);
		return ResponseEntity.ok(questionDto);
	}

	@PutMapping("/{questionId}/update-answer")
	public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid Answer answer, @PathVariable Long questionId) {
		Question question = this.questionService.find(questionId);
		
		if(Objects.nonNull(question)) {
			answer.setQuestion(question);
			question.getAnswers().add(answer);
			question = this.questionService.save(question);
		}
		QuestionDto questionDto = modelMapper.map(this.questionService.save(question), QuestionDto.class);
		return ResponseEntity.ok(questionDto);
	}

	@GetMapping
	public ResponseEntity<Page<Question>> list(Pageable pageable) {
		return ResponseEntity.ok(this.questionService.listAll(pageable));
	}

	@GetMapping("/{questionId}/details")
	public ResponseEntity<QuestionDto> find(@PathVariable Long questionId) {
		Question questionFound = this.questionService.find(questionId);
		QuestionDto questionDto = modelMapper.map(questionFound, QuestionDto.class);
		
		if(!questionFound.getAnswers().isEmpty()) {
			List<AnswerDto> answersDto = questionFound.getAnswers().stream().map(answer -> {
				return modelMapper.map(answer, AnswerDto.class);
			}).collect(Collectors.toList());
			questionDto.setAnswersDto(answersDto);
		}		

		return ResponseEntity.ok(questionDto);
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<Void> delete(@PathVariable Long questionId) {
		this.questionService.delete(questionId);
		return ResponseEntity.ok().build();
	}
}
