package tuan.kul.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import tuan.kul.common.DateUtils;

@Service
public class FileService {
    public String uploadFile(String file, String name, String folder) throws IOException {
        try {
//            String root = "D:\\FreeJava\\TK-JAVA-WEB-FREE\\tk-java-web-free-web\\src\\main\\webapp" + File.separator + "fileupload";
            String root = "D:\\TuanKul\\PROJECT_DONE\\TK-JAVA-WEB-FREE\\tk-java-web-free-web\\src\\main\\webapp" + File.separator + "fileupload";
            File locationRoot = new File(root);
            if (!locationRoot.exists()){//if not exist folder, system will create folder by directory
            	locationRoot.mkdir();
            }
            
            String sub = root + File.separator + folder;
            File locationSub = new File(sub);
            if (!locationSub.exists()){//if not exist folder, system will create folder by directory
            	locationSub.mkdir();
            }
            
            String date = DateUtils.convertDateToString(DateUtils.FORMAT_YYMMDD, new Date(System.currentTimeMillis()));
            String sub1 = sub + File.separator + date;
            File locationSub1 = new File(sub1);
            if (!locationSub1.exists()){//if not exist folder, system will create folder by directory
            	locationSub1.mkdir();
            }
            
        	String location = sub1 + File.separator + name;
            File directory = new File(location);
            if (directory.exists()){//if not exist folder, system will create folder by directory
            	directory.delete();
            }
            FileOutputStream outputStream = new FileOutputStream(location);
            byte[] base64 = Base64.decodeBase64(file.getBytes());
            outputStream.write(base64);
            outputStream.close();
            return "fileupload" + File.separator + folder + File.separator + date + File.separator + name;
        } catch (IOException e) {
            System.out.println("save file by news exception ---" + e.toString());
            return null;
        }
    }
}
