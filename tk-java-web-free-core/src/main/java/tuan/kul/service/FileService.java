package tuan.kul.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tuan.kul.common.DateUtils;

@Service
public class FileService {
    
    @Autowired
    private DateUtils dateUtils;
    
    public String saveFile(MultipartFile multipartFile) throws IOException {
        FileOutputStream outputStream = null;
        try {
            byte[] base64 = multipartFile.getBytes();
            String directoryName = "C://image/";
            String fileName = multipartFile.getOriginalFilename();
            File directory = new File(directoryName);
            if (!directory.exists()){//if not exist folder, system will create folder by directory
                directory.mkdir();
            }
            String directoryNameSub = directoryName + "news/";
            File directorySub = new File(directoryNameSub);
            if (!directorySub.exists()) {
                directorySub.mkdir();
            }
            File file = new File(directoryNameSub + dateUtils.convertDateToString(DateUtils.FORMAT_YYMMDDHHMMSS, new Date()) + "_" + fileName);
            outputStream = new FileOutputStream(file);
            outputStream.write(base64);
            outputStream.close();
            return file.toString();
        } catch (IOException e) {
            System.out.println("save file by news exception ---" + e.toString());
        }
        return null;
    }
}
