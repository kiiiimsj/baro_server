package com.wantchu.wantchu_server2.inquiry.service;

import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.InquiryDao;
import com.wantchu.wantchu_server2.inquiry.dto.InquirySaveRequestDto;
import com.wantchu.wantchu_server2.inquiry.exception.InquiryNotFoundByEmailException;
import com.wantchu.wantchu_server2.inquiry.exception.InquiryNotFoundByIdException;
import com.wantchu.wantchu_server2.vo.InquiryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class InquiryService {

    private final InquiryDao inquiryDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findInquiryListByEmail(String email) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<InquiryVo> list = inquiryDao.findInquiryListByEmail(email);
            jsonObject.put("result", true);
            jsonObject.put("message", "email로 문의 내역 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(InquiryVo inquiry : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(inquiry.convertToListMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("inquiry", jsonArray);
        } catch(InquiryNotFoundByEmailException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findInquiryById(int inquiry_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            InquiryVo inquiryVo = inquiryDao.findInquiryById(inquiry_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "inquiry_id로 문의 내역 정보 가져오기 성공");
            jsonObject.put("inquiry_id", inquiryVo.getInquiry_id());
            jsonObject.put("email", inquiryVo.getEmail());
            jsonObject.put("title", inquiryVo.getTitle());
            jsonObject.put("content", inquiryVo.getContent());
            jsonObject.put("inquiry_date", DateConverter.convertDateWithoutTime(inquiryVo.getInquiry_date()));
            jsonObject.put("is_replied", inquiryVo.getIs_replied());
        } catch(InquiryNotFoundByIdException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject save(InquirySaveRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            inquiryDao.save(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "1:1 문의가 정상적으로 등록되었습니다.");
        } catch(Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}
