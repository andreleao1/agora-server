package agoraquestionserver.web.dto;

import java.util.List;

public class QuestionDto {

	private Long id;

	private String title;

	private String questionText;

	private List<AnswerDto> answersDto;

	public QuestionDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<AnswerDto> getAnswersDto() {
		return answersDto;
	}

	public void setAnswersDto(List<AnswerDto> answersDto) {
		this.answersDto = answersDto;
	}	
}
