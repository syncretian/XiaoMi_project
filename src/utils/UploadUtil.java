package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import entity.Commodity;

public class UploadUtil {

	@SuppressWarnings("unchecked")
	public static List<FileItem> parseFileItem(HttpServletRequest request) {
		DiskFileItemFactory disk =  new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(disk);
		
		upload.setHeaderEncoding("UTF-8");
		upload.setFileSizeMax(1024*1024);
		
		if(ServletFileUpload.isMultipartContent(request)) {

			try {
				
				return upload.parseRequest(request);
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
		}
		return null;
		
	}

/*	public static Commodity dealFormField(FileItem item, Commodity co) {
		try {
			String name = item.getFieldName();
			String  str= item.getString();
			String value;
			if(str != null) {					
				
				value = new String(str.getBytes("ISO-8859-1"),"UTF-8");
					
				
				switch(name) {
					case "gid": 
						try {
							Integer gid = new Integer(value);
							co.setGid(gid);
						}catch(NumberFormatException e) {
							System.out.println("GID类型转换异常");
							e.printStackTrace();
						}
						
						break;
					case "name": 
						co.setName(value);
						break;
					case "color": 
						co.setColor(value);
						break;
					case "size": 
						co.setSize(value);
						break;
					case "price": 
						try {
							Double p = Double.parseDouble(value);
							co.setPrice(p);
						}catch(NumberFormatException e) {
							System.out.println("price类型转换异常");
							e.printStackTrace();
						}
						break;
					case "description": 
						co.setDescription(value);
						break;
					case "full_description":
						co.setFull_description(value);
						break;
					case "state": 
						try {
							Integer s = new Integer(value);
							co.setState(s);
						}catch(NumberFormatException e) {
							System.out.println("state类型转换异常");
							e.printStackTrace();
						}
						break;
					case "version": 
						co.setVersion(value);
						break;
					case "product_date": 
						co.setProduct_date(DealData.parseDate(value));
						break;
				}
				return co;		
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return co;

		
		
	}*/
	@SuppressWarnings("resource")
	public static String dealImgField( FileItem item,File f)  {
		
		try {
			String inputname = item.getFieldName();  //表单项name
			String fileName =item.getName();//上传的文件名
			System.out.println("inputname:  "+inputname+" fileName: "+fileName);
			
			if(fileName.isEmpty()) {
				System.out.println("文件名为空");
				return null;
			}
			
			String imgName = getImgName(fileName);
			System.out.println("图片信息： "+inputname+"  "+fileName+"  "+imgName);
			
			if("pic".equals(inputname)) {
				String path = f.getAbsolutePath()+"/"+imgName;
				
				FileOutputStream fo = null;
			
				fo = new FileOutputStream(path);
				
				java.io.InputStream in = item.getInputStream();
				
				byte[] buf = new byte[1024];
				int len =0;
				while(( len = in.read(buf) )!=-1) {
					fo.write(buf);
				}
			}
			
			return imgName;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Commodity dealFormField2(FileItem item, Commodity co) {
		try {
			String name = item.getFieldName();
			String  str= item.getString();
			String value;
			if(str != null) {					
				
				value = new String(str.getBytes("ISO-8859-1"),"UTF-8");
					
				
				switch(name) {
				case "cid": 
					try {
						Integer cid = new Integer(value);
						co.setCid(cid);
					}catch(NumberFormatException e) {
						System.out.println("GID类型转换异常");
						e.printStackTrace();
					}
					
					break;
					case "gid": 
						try {
							Integer gid = new Integer(value);
							co.setGid(gid);
						}catch(NumberFormatException e) {
							System.out.println("GID类型转换异常");
							e.printStackTrace();
						}
						
						break;
					case "name": 
						co.setName(value);
						break;
					case "color": 
						co.setColor(value);
						break;
					case "size": 
						co.setSize(value);
						break;
					case "price": 
						try {
							Double p = Double.parseDouble(value);
							co.setPrice(p);
						}catch(NumberFormatException e) {
							System.out.println("price类型转换异常");
							e.printStackTrace();
						}
						break;
					case "description": 
						co.setDescription(value);
						break;
					case "full_description":
						co.setFull_description(value);
						break;
					case "state": 
						try {
							Integer s = new Integer(value);
							co.setState(s);
						}catch(NumberFormatException e) {
							System.out.println("state类型转换异常");
							e.printStackTrace();
						}
						break;
					case "version": 
						co.setVersion(value);
						break;
					case "product_date": 
						co.setProduct_date(DealData.parseDate(value));
						break;
					case "picc": 
						co.setPicc(value);;
						break;
				}
				return co;		
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return co;

		
		
	}
	public static String getImgName(String fileName) {
		int index = fileName.lastIndexOf(".");
		System.out.println("index:  "+index);
		//获取文件的类型
		String suffix=null;
		if(index>-1) {
			suffix = fileName.substring(index);
		}else {
			suffix = ".jpg";
		}
		

		String prefix = UUID.randomUUID().toString();
		
		System.out.println(prefix+suffix);
		return prefix+suffix;
	}

	public static void deleteFile(String pic) {
		File f = new File(pic);
		
		if(!f.isFile() || !f.exists()) {
			System.out.println("文件不存在");
		}else {
			if(f.delete()) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		}
		
	}


}
