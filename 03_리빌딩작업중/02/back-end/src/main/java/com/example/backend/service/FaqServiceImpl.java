package com.example.backend.service;

import com.example.backend.dao.FaqDao;
import com.example.backend.model.Faq;
import com.example.backend.paging.ProductCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService{

    @Autowired
    private FaqDao faqDao;

    @Override
    public Optional<Faq> detailFaq(Long id) {
        return faqDao.findById(id);
    }

    @Override
    public Optional<Faq> findById(long id) {
        return faqDao.findById(id);
    }



    @Override
    public List<Faq> findByTitleContaining(ProductCriteria productCriteria) {
        List<Faq> faqs = Collections.emptyList();

        Optional<String> optionalCriteria = Optional.ofNullable(productCriteria.getTitle());

        int totalCount = faqDao.selectTotalCount(optionalCriteria.orElse(""));

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        if(productCriteria.getTitle() == null){
            faqs = faqDao.findAll(productCriteria);
        }
        else{
            faqs = faqDao.findByTitleContaining(productCriteria);
        }
        return faqs;
    }

    @Override
    public List<Faq> findAll(ProductCriteria productCriteria) {
        return faqDao.findAll(productCriteria);
    }

    @Override
    public Optional<Faq> save(Faq faq) {
        long seqId = 0;

        if(faq.getId() == null ){
            faqDao.insertFaq(faq);
        }
        else{
            faqDao.updateFaq(faq);
        }

        seqId = faq.getId();

        return faqDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        long queryResult=0;
        queryResult = faqDao.deleteFaq(id);
        return queryResult >= 1;

    }

    @Override
    public boolean deleteAll() {
        long queryResult = 0;

        queryResult= faqDao.deleteAll();

        return queryResult >= 1;
    }
}
