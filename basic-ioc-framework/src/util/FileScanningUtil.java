package util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileScanningUtil {

	public static List<Class<?>> findQualifiedNameUnderPackage(String packageToScan){
		//we will get package like: com.bean or bean
		//file will be under -> bin/bean/classNames.class
		//we need to search for bin folder using IO file api
		packageToScan = "bin/" + packageToScan.replaceAll("\\.", "/");
		//directory
		File file = new File(packageToScan);
		if(!file.exists() || !file.isDirectory()) {
			throw new RuntimeException("packge provided"+packageToScan+ "is not a valid directory");
		}
		//load only .class files
		
		File[] listFiles = file.listFiles(e -> e.getName().endsWith(".class"));
		//now we can get the class names
		return Arrays.stream(listFiles)
				
			.map(File::getPath)
			.map(a -> a.replaceAll(".class", ""))
			.map(a -> a.replace("bin", ""))
			.map(a -> a.replaceAll("\\\\", "."))
			.map(a -> a.substring(1))
			.peek(System.out::println)
			
			.map(t -> {
				try {
					return Class.forName(t);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				return null;
			})
			.collect(Collectors.toList())
			;
	}
	
	public static void main(String[] args) {
		List<Class<?>> findQualifiedNameUnderPackage = FileScanningUtil.findQualifiedNameUnderPackage("com.iocframework.bean");
		findQualifiedNameUnderPackage.stream()
			.map(Class::getSimpleName)
			.forEach(System.out::println);
	}
}
