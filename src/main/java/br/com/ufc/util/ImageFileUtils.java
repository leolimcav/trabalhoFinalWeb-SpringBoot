package br.com.ufc.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageFileUtils {
	
	public static void saveImage(String path, MultipartFile image) {
		File file = new File(path);
		
		try {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
