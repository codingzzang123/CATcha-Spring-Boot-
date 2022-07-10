package com.ib.cat.service.member;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    public String[] fileUpload(MultipartFile mfile, String path) {
        String imgo = mfile.getOriginalFilename();
        String fileExtension = imgo.substring(imgo.length()-3, imgo.length());

        UUID uuid = UUID.randomUUID();
        String[] uuids = uuid.toString().split("-");
        String imgs = uuids[0] + "."+fileExtension;

        String[] img = {imgo, imgs};

        File file = new File(path+imgs);

        try {

            mfile.transferTo(file);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public String[] nullFileUpload() {
        String imgo = "default.png";

        UUID uuid = UUID.randomUUID();
        String[] uuids = uuid.toString().split("-");
        String imgs = uuids[0] + ".png";

        String[] img = {imgo, imgs};

        return img;
    }
}
