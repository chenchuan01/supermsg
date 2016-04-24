package com.sys.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sys.SysConstants;
import com.sys.common.ConfigKeys;

/**
 *@author chenchuan
 *@date 2016年2月2日
 *ImgUtil.java
 */
public class ImgUtil {
	
	/**
	 * 照片保存，返回保存路劲
	 * @param list
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static List<String> savePhoto(List<CommonsMultipartFile> files,
			String...dirs) throws IllegalStateException, IOException{
		if(dirs==null){
			dirs=new String[0];
		}
		List<String> paths = new ArrayList<String>();
		for (CommonsMultipartFile file : files) {
			String filePath = commonSaveImg(file,file.getBytes(), dirs);
			paths.add(filePath);
		}
		return paths;
	}

	private static String commonSaveImg(CommonsMultipartFile file,byte[] bytes, String... dirs)
			throws IOException {
		/**初始化参数*/
		StringBuilder path = new StringBuilder();
		String root = ConfigUtil.getStrVal(ConfigKeys.IMG_SAVEPATH);
		
		/**生成路径*/
		gemnFilePath(file, path, root, dirs);
		
		/**保存文件*/
		saveFile(bytes, path, root);
		
		/**添加返回的相对路径*/
		String filePath = setSaveDBpath(path);
		return filePath;
	}

	private static String setSaveDBpath(StringBuilder path) {
		return path.toString();
		
	}

	private static void gemnFilePath(CommonsMultipartFile file,
			StringBuilder path, String root, String... dirs) {
		genRelativePath(path, dirs);
		
		String upFileName = genFileName(file);
		
		mkFileDirs(path, root);
		path.append(upFileName);
	}

	private static void saveFile(byte[] fileBytes, StringBuilder path,
			String root) throws IOException {
		StringBuilder filePath = replaceFileSplit(root+path.toString());
		File uploadFile = new File(filePath.toString());  
		FileCopyUtils.copy(fileBytes, uploadFile);
	}

	private static void mkFileDirs(StringBuilder path, String root) {
		StringBuilder tagDir = replaceFileSplit(root+path.toString());
		File upFileDir=new File(tagDir.toString());
		if (!upFileDir.exists()) {  
		    upFileDir.mkdirs(); 
		}
	}

	public static StringBuilder replaceFileSplit(String dirs) {
		String[] tempDirs = dirs.split(SysConstants.PATH_SPLIT);
		StringBuilder tagDir = new StringBuilder();
		int i = 0;
		for (String dir : tempDirs) {
			if(StringUtil.isNotNull(dir)){
				tagDir.append(dir);
				if(i!=tempDirs.length-1){
					tagDir.append(File.separator);
				}
			}
			i++;
		}
		return tagDir;
	}

	private static String genFileName(CommonsMultipartFile file) {
		String upFileName="";
		if(file!=null){
			upFileName = file.getOriginalFilename();
			upFileName = upFileName.substring(upFileName.indexOf(SysConstants.IMG_DOT));
		}else{
			upFileName = SysConstants.IMG_SUBFIX;
		}
		
		upFileName = System.currentTimeMillis()+upFileName;
		return upFileName;
	}

	private static void genRelativePath(StringBuilder path, String... dirs) {
		if(!path.toString().endsWith(SysConstants.PATH_SPLIT)){
			path.append(SysConstants.PATH_SPLIT);
		}
		for(int i=0;i<dirs.length;i++){
			path.append(dirs[i]);
			path.append(SysConstants.PATH_SPLIT);
		}
		path.append(DateUtil.formatDate(new Date(),DateUtil.DATEDIR));
		path.append(SysConstants.PATH_SPLIT);
	}

}
