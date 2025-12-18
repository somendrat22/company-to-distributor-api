package com.supplynext.company_api.services;

import com.supplynext.company_api.exceptions.UploadFileException;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
public class ImageKitService {

    @Value("${image-kit.url}")
    private String imageKitPublicUrl;
    @Value("${image-kit-public-key}")
    private String imageKitPublicKey;
    @Value("${image-kit-private-key}")
    private String imageKitPrivateKey;

    public ImageKit getImageKitObject(){
        ImageKit imageKit = ImageKit.getInstance();
        Configuration configuration = new Configuration(imageKitPublicKey, imageKitPrivateKey, imageKitPublicUrl);
        imageKit.setConfig(configuration);
        return imageKit;
    }

    public Result uploadDocument(MultipartFile file,
                               String fileName,
                               String folder) throws ForbiddenException, TooManyRequestsException, InternalServerException, UnauthorizedException, BadRequestException, UnknownException, IOException {
        try{
            long fileSizeInMB = file.getSize()/(1024*1024);
            if(fileSizeInMB > 25){
                throw new UploadFileException("File size is grater then 25 mb");
            }
        }catch (Exception e){
            log.warn(e.getMessage());
        }
        // Convert to bytes
        byte[] fileBytes = file.getBytes();
        // Convert file to base64
        String base64 = Base64.getEncoder().encodeToString(fileBytes);
        // FileCreateRequest
        FileCreateRequest fileCreateRequest = new FileCreateRequest(
                base64,
                fileName
        );
        // Setting folder
        fileCreateRequest.setFolder(folder);
        // Image
        ImageKit imageKit = getImageKitObject();
        // Upload
        Result result = imageKit.upload(fileCreateRequest);
        return result;
    }
}
