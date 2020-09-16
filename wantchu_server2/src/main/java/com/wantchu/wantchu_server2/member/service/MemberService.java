package com.wantchu.wantchu_server2.member.service;

import autovalue.shaded.com.google$.auto.service.$AutoService;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.MemberDao;
import com.wantchu.wantchu_server2.member.dto.MemberEmailUpdateRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberLoginRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberPassUpdateRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.member.exception.*;
import com.wantchu.wantchu_server2.vo.AlertVo;
import com.wantchu.wantchu_server2.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Component
@Service
public class MemberService {

    private final MemberDao memberDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject login(MemberLoginRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            MemberVo memberVo = memberDao.isValidAccount(requestDto.getPhone(), requestDto.getPass());
            System.out.println(requestDto.getDevice_token());
            memberDao.updateDeviceToken(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "Login success.");
            jsonObject.put("phone", memberVo.getPhone());
            jsonObject.put("nick", memberVo.getNick());
            jsonObject.put("email", memberVo.getEmail());
            jsonObject.put("created_date", DateConverter.convertDateWithoutTime(memberVo.getCreated_date()));
        } catch(MemberLoginException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject isPhoneInUse(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if(!memberDao.isPhoneInUse(phone)) {
                jsonObject.put("result", true);
                jsonObject.put("message", "가입 가능한 전화번호 입니다.");
            }
            else {
                throw new MemberPhoneInUseException();
            }
        } catch(MemberPhoneInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject register(MemberRegisterRequestDto registerRequestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if(!memberDao.isEmailInUse(registerRequestDto.getEmail())) {
                if (!memberDao.isPhoneInUse(registerRequestDto.getPhone())) {
                    memberDao.register(registerRequestDto);
                    jsonObject.put("result", true);
                    jsonObject.put("message", "회원 가입에 성공했습니다.");
                }
                else {
                    throw new MemberPhoneInUseException();
                }
            }
            else {
                throw new MemberEmailInUseException();
            }
        } catch(MemberEmailInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            jsonObject.put("errno", e.getErrno());
        } catch(MemberPhoneInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            jsonObject.put("errno", e.getErrno());
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject updatePassword(MemberPassUpdateRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            memberDao.updatePassword(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "비밀번호가 정상적으로 변경되었습니다.");
        } catch(MemberPassUpdateException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject updateEmail(MemberEmailUpdateRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if (!memberDao.isEmailInUse(requestDto.getEmail())) {
                memberDao.updateEmail(requestDto);
                jsonObject.put("result", true);
                jsonObject.put("message", "이메일이 정상적으로 변경되었습니다.");
            }
            else {
                throw new MemberEmailInUseException();
            }
        } catch(MemberEmailInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }


}
