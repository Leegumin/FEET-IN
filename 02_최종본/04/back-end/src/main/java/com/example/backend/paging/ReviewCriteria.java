package com.example.backend.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewCriteria {

	/** 현재 페이지 번호 */
	private Integer page;

	/** 페이지당 출력할 데이터 개수 */
	private Integer size;

//	Todo : 아래 2개 추가
//	테이블 총 건수
	private Integer totalItems;

//	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
	private Integer totalPages;

	private Long id;

	private String searchTitle;

//	기본 테이지 정보 세팅
	public ReviewCriteria() {
		this.page = 0;
		this.size = 3;
	}
}
