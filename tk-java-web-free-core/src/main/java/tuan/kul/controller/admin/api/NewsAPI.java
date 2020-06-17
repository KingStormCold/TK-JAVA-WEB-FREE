package tuan.kul.controller.admin.api;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tuan.kul.common.JsonUtils;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.request.news.InsertUpdateNewRequest;
import tuan.kul.request.news.SearchRequest;
import tuan.kul.response.news.GetNewsInfo;
import tuan.kul.response.news.ListNewInfo;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.ResultResponse;
import tuan.kul.service.NewService;

@RestController
public class NewsAPI {

    private static final Logger log = Logger.getLogger(NewsAPI.class);

    @Autowired
    private NewService newService;

    @PostMapping(value = "/admin/news/search")
    public ListNewInfo searchNews(@RequestBody SearchRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String json = JsonUtils.convertObjectToString(request);
        log.info(userName + " search ==== " + json);
        if (StringUtils.isEmpty(request.validate())) {
            return newService.searchNews(request, userName);
        }
        return request.validate();
    }
    
    @GetMapping(value = "/admin/news/get-info")
    public ObjectInfoResponse<GetNewsInfo> getInfo(@RequestParam("id") String id) {
        try {
            return newService.getNewInfo(id);
        } catch (Exception e) {
            log.info(e.toString());
            return new ObjectInfoResponse<GetNewsInfo>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
        }
    }
    
    @PostMapping(value = "/admin/news/insert-update", consumes = "multipart/form-data")
    public ResultResponse insertOrUpdateNew(@RequestParam(name = "new_id") String newId,
            @RequestParam(name = "new_category") String newCategory,
            @RequestParam(name = "new_title") String newTitle,
            @RequestParam(name = "new_description") String newDescription,
            @RequestPart(name = "new_file") MultipartFile newFile,
            @RequestParam(name = "new_content") String newContent,
            @RequestParam(name = "new_top") String newTop) throws IOException, URISyntaxException {
        InsertUpdateNewRequest request = new InsertUpdateNewRequest(newId, newCategory, newTitle, newDescription, newFile, newContent, newTop); 
        if (StringUtils.isEmpty(request.validate())) {
            return newService.insertOrUpdateNew(request);
        }
        return request.validate();
    }
}
