package com.kshrd.derphsar_api.rest.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.repository.dto.FileInfo;
import com.kshrd.derphsar_api.repository.dto.ImageDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.image.response.ImageResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopResponseModel;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.FilesStorageService;
import com.kshrd.derphsar_api.service.implement.FilesStorageServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.kshrd.derphsar_api.rest.message.ResponseMessage;

import java.awt.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("http://34.66.220.125:1500")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilesRestController {
    @Value(value = "${file.upload.server.path}")
    private String serverPath;

    @Value("${file.base.url}")
    private String imageUrl;

    @Autowired
    FilesStorageServiceImp storageService;

    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }



    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    //@ResponseBody
    @ApiOperation(value = "upload images")
    public ResponseEntity<Map<String,Object>> uploadFile(@RequestParam("files") MultipartFile[] files) {

        ModelMapper mapper = new ModelMapper();
        BaseApiNoPaginationResponse<List<ImageResponse>> response = new BaseApiNoPaginationResponse<>();

        List<String> ImageList = new ArrayList<>();
        Map<String, Object> res = new HashMap<>();

        int i=0;
        try {
            for(MultipartFile file : files)
            {
                i++;
                String fileName = storageService.save(file);
//                imageResponses.add(mapper.map(file, ImageResponse.class));
                if(i==1){
                    res.put("message",message.inserted("Images"));
                    res.put("status",HttpStatus.OK);
                    res.put("BeseUrl",imageUrl);
                }
                ImageList.add(fileName);
                //res.put("image"+i,(imageUrl+fileName));
            }
            res.put("Images",ImageList);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("message","Could not upload the file:");
            res.put("status",false);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(res);
        }
    }




//    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "upload images")
//    public ResponseEntity<BaseApiNoPaginationResponse<List<ImageResponse>>> uploadFile(@RequestParam("files") MultipartFile[] files) {
//
//        ModelMapper mapper = new ModelMapper();
//        BaseApiNoPaginationResponse<List<ImageResponse>> response = new BaseApiNoPaginationResponse<>();
//        List<ImageResponse> imageResponses = new ArrayList<>();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            for(MultipartFile file : files)
//            {
////                ImageDto imageDto = new ImageDto();
////                Object images = ObjectMapper.readValue(imageDto.getImageUrl(), Object.class);
////                imageDto.setImages(images);
//
//
//                Object a = storageService.save(file);
//                imageResponses.add(mapper.map(a, ImageResponse.class));
//                response.setMessage(message.inserted("Images"));
//                response.setStatus(HttpStatus.OK);
//                response.setData(imageResponses);
//                System.out.println(imageResponses);
//                response.setTime(new Timestamp(System.currentTimeMillis()));
//            }
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.setMessage(message.insertError("Images"));
//            response.setStatus(HttpStatus.BAD_REQUEST);
//            response.setTime(new Timestamp(System.currentTimeMillis()));
//            return ResponseEntity.ok(response);
//        }
//    }


    @GetMapping("/files")
    @ApiOperation(value = "get all images")
    public ResponseEntity<BaseApiResponse<ArrayList<String>>> getAllFiles() {
        try{
            BaseApiResponse<ArrayList<String>> baseApiResponse = new BaseApiResponse<>();
//            Set<String> fileNames = storageService.listFilesUsingJavaIO("D:\\meeting31\\API\\derphsar-api-v6\\derphsar-api-v1\\src\\main\\resources\\files");
            Set<String> fileNames = storageService.listFilesUsingJavaIO(serverPath);

            ArrayList<String> nameWithAddress= new ArrayList<>();
            for(String string : fileNames){
                nameWithAddress.add(imageUrl+string);
            }
            baseApiResponse.setData(nameWithAddress);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
            baseApiResponse.setStatus(HttpStatus.OK);
            baseApiResponse.setMessage("You have selected all the images");
            return ResponseEntity.ok(baseApiResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/files/{filename:.+}")
    @ApiOperation(value = "get a image by name")
    public ResponseEntity<BaseApiResponse<String>> getFileByName(@PathVariable String filename) {
        try{
            BaseApiResponse<String> baseApiResponse = new BaseApiResponse<>();
            //Set<String> fileNames = storageService.listFilesUsingJavaIO("D:\\meeting31\\API\\derphsar-api-v6\\derphsar-api-v1\\src\\main\\resources\\files");

            Set<String> fileNames = storageService.listFilesUsingJavaIO(serverPath);
            String nameWithAddress = "";
            for(String string : fileNames){
                if(string.equals(filename))
                    nameWithAddress = imageUrl+string;
            }
            baseApiResponse.setData(nameWithAddress);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
            baseApiResponse.setStatus(HttpStatus.OK);
            baseApiResponse.setMessage("You have selected the images");
            return ResponseEntity.ok(baseApiResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
