package tuan.kul.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SaveFileUtils {

	public boolean uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			File file = new File(this.getFolderUpload(), fileName);
			multipartFile.transferTo(file);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}

}
