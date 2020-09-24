package com.wantchu.wantchu_server2.image;

import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@NoArgsConstructor
@Component
@Controller
public class ImageController {

    private String getImageName(@NotNull HttpServletRequest request) {
        return request.getParameter("image_name");
    }

    @GetMapping(value = "/ImageEvent.do", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getEventImage(@NotNull HttpServletRequest request) throws IOException {
        String image_name = getImageName(request);
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("/images/events/" + image_name).getInputStream();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            inputStream = new ClassPathResource("/images/events/default.png").getInputStream();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/ImageStore.do", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getStoreImage(@NotNull HttpServletRequest request) throws IOException {
        String image_name = getImageName(request);
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("/images/stores/" + image_name).getInputStream();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            inputStream = new ClassPathResource("/images/stores/default.png").getInputStream();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/ImageType.do", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getTypeImage(@NotNull HttpServletRequest request) throws IOException {
        String image_name = getImageName(request);
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("/images/types/" + image_name).getInputStream();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            inputStream = new ClassPathResource("/images/types/default.png").getInputStream();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/ImageMenu.do", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getMenuImage(@NotNull HttpServletRequest request) throws IOException {
        String image_name = getImageName(request);
        InputStream inputStream = null;
        try{
            inputStream = new ClassPathResource("/images/menus/" + image_name).getInputStream();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            inputStream = new ClassPathResource("/images/menus/default.png").getInputStream();
        }
        return IOUtils.toByteArray(inputStream);
    }
}