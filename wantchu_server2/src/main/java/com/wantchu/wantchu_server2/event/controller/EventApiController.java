package com.wantchu.wantchu_server2.event.controller;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class EventApiController {

    private final EventService eventService;

    @GetMapping("/EventFindAll.do")
    public void EventFindAll(@NotNull HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = eventService.findAll();
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/EventDetail.do")
    public void EventDetail(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        org.json.simple.JSONObject jsonObject = eventService.findDetail(event_id);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/EventFindAdvertising.do")
    public void EventAdvertising(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = eventService.findAdvertising();
        WriteToServer.send(response, jsonObject);
    }
}
