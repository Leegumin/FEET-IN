package com.example.backend.service;

import com.example.backend.dao.FileDBDao;
import com.example.backend.model.FileDB;
import com.example.backend.paging.ReviewCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileDBService {

  @Autowired
  private FileDBDao fileDBDao;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  public int store(Long id, String title, String content, String writer, long starNum, MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    todo: filedb 수정 (title, content)
    FileDB FileDB = new FileDB(id, fileName, title, content, writer,starNum, file.getContentType(), file.getBytes());

    logger.info(" id {} " , id);
    logger.info(" fileName {} " , fileName);
    logger.info(" title {} " , title);
    logger.info(" content {} " , content);
    logger.info(" writer {} " , writer);
    logger.info(" starNum {} " , starNum);

    return fileDBDao.save(FileDB);
  }

  public FileDB getFile(Long reviewId) {
    return fileDBDao.findById(reviewId);
  }

//  todo:
  public Stream<FileDB> getAllFiles(ReviewCriteria criteria) {

//    todo:
    int totalCount = fileDBDao.selectTotalCount();

//		Tutorials 총 건수
    criteria.setTotalItems(totalCount);
    //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
    criteria.setTotalPages(totalCount/criteria.getSize());
    //    list -> stream 객체로 변환
    Stream<FileDB> resFileDB = fileDBDao.findAll(criteria).stream();

    return resFileDB;
  }

// TODO: 타이틀 검색 기능 추가
public Stream<FileDB> findByTitleContaining(ReviewCriteria criteria) {
  logger.info("criteria title {} ", criteria.getSearchTitle());

  Optional<String> optionalCriteria
          = Optional.ofNullable(criteria.getSearchTitle());
//    todo:
//    int totalCount = fileDBDao.selectTotalCount();

  int totalCount = fileDBDao.selectTotalCount(optionalCriteria.orElse(""));

//		Tutorials 총 건수
  criteria.setTotalItems(totalCount);
  //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
  criteria.setTotalPages(totalCount/criteria.getSize());
  //    list -> stream 객체로 변환

  if(criteria.getSearchTitle() == null) {
    return fileDBDao.findAll(criteria).stream();
  }else
    return fileDBDao.findByTitleContaining(criteria).stream();
}

  public Stream<FileDB> findByProduct(ReviewCriteria criteria) {
//    logger.info("criteria {} ", criteria.getId());

    int totalCount = fileDBDao.productReviewTotalCount(criteria.getId());

//		Tutorials 총 건수
    criteria.setTotalItems(totalCount);
    //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
    criteria.setTotalPages(totalCount/criteria.getSize());
    //    list -> stream 객체로 변환

    return fileDBDao.findByProduct(criteria).stream();
  }

  public boolean deleteById(Long reviewId) {
    int queryResult = 0;

    queryResult = fileDBDao.deleteBoard(reviewId);

    return (queryResult >=1) ? true : false;
  }


  public boolean deleteAll() {
    int queryResult = 0;

    queryResult = fileDBDao.deleteAll();

    return (queryResult >=1) ? true : false;
  }
}
