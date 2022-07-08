package com.example.backend.service;

import com.example.backend.dao.SendEmailDao;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * packageName : com.example.backend.service
 * fileName : SendEmailServiceImp
 * author : gumin
 * date : 2022-07-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
@Service
public class SendEmailServiceImp implements SendEmailService {
    @Autowired
    SendEmailDao sendEmailDao;

    // 사용자가 입력한 이름 및 이메일이 같이 매칭되는게 있는지 쿼리문으로 뒤져봄
    // 데이터가 있으면 ID, 참값 반환
    @Override
    public User findID(String name, String email) {
        return sendEmailDao.findID(name, email);
    }

    @Override
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random       rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) (rnd.nextInt(26) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }
}
