package com.wantchu.wantchu_server2.notice.service;

import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.NoticeDao;
import com.wantchu.wantchu_server2.notice.dto.NoticeSaveRequestDto;
import com.wantchu.wantchu_server2.notice.exception.NoticeIdNotFoundException;
import com.wantchu.wantchu_server2.notice.exception.NoticeNotFoundByCodeException;
import com.wantchu.wantchu_server2.notice.exception.NoticeNotFoundException;
import com.wantchu.wantchu_server2.vo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeDao noticeDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findAll() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<NoticeVo> list = noticeDao.findAll();
            jsonObject.put("result", true);
            jsonObject.put("message", "notice 가져오기 성공.");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(NoticeVo notice : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(notice.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("notice", jsonArray);
        } catch(NoticeNotFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject save(NoticeSaveRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            noticeDao.save(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "notice 등록 성공");
        } catch(Exception exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByNoticeId(int notice_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            NoticeVo noticeVo = noticeDao.findByNoticeId(notice_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 id로 공지 가져오기 성공");
            jsonObject.put("notice_id", noticeVo.getNotice_id());
            jsonObject.put("title", noticeVo.getTitle());
            jsonObject.put("content", noticeVo.getContent());
            jsonObject.put("notice_code", noticeVo.getNotice_code());
            jsonObject.put("notice_date", DateConverter.convertDateWithoutTime(noticeVo.getNotice_date()));
        } catch(NoticeIdNotFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByCode(String notice_code) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<NoticeVo> list = noticeDao.findByCode(notice_code);
            jsonObject.put("result", true);
            jsonObject.put("message", "notice_code 별로 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(NoticeVo notice : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(notice.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("notice", jsonArray);
        } catch(NoticeNotFoundByCodeException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
}
