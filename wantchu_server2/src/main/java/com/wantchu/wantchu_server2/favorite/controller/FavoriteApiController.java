package com.wantchu.wantchu_server2.favorite.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.favorite.dto.FavoriteExistRequestDto;
import com.wantchu.wantchu_server2.favorite.dto.FavoriteListDto;
import com.wantchu.wantchu_server2.favorite.service.FavoriteService;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class FavoriteApiController {

    private final FavoriteService favoriteService;

    @PostMapping("/FavoriteList.do")
    public void findFavInfoByPhone(@RequestBody @NotNull FavoriteListDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = favoriteService.findFavInfoByPhone(request);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/FavoriteSave.do")
    public void saveFavorite(@RequestBody @NotNull FavoriteVo favoriteVo, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = favoriteService.saveFavorite(favoriteVo);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/FavoriteDelete.do")
    public void deleteFavorite(@RequestBody @NotNull FavoriteVo favoriteVo, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = favoriteService.deleteFavorite(favoriteVo);
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/FavoriteExist.do")
    public void existFavorite(@RequestBody @NotNull FavoriteExistRequestDto favoriteExistRequestDto, @NotNull HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = favoriteService.existFavorite(favoriteExistRequestDto);
        WriteToServer.send(response, jsonObject);
    }
}
