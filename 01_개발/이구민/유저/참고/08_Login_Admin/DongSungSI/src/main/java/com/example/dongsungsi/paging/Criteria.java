package com.example.dongsungsi.paging;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Criteria {

	/** 현재 페이지 번호 */
	private Integer page;

	/** 페이지당 출력할 데이터 개수 */
	private Integer size;

//	Todo : 아래 2개 추가
//	테이블 총 건수
	private Integer totalItems;

//	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
	private Integer totalPages;

//	검색 : 제목
	private String title;

//	기본 테이지 정보 세팅
	public Criteria() {
		this.page = 0;
		this.size = 3;
	}
}
