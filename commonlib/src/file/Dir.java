package file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Dir {
	
	 public static List<String> list(File dir){
		 List<String> total = new ArrayList<String>();
		 for(File f : listFile(dir)){
			 total.add(f.getAbsolutePath());
		 }
		 return total;
	 }
	 
	 public static List<String> list(File dir, FilenameFilter filter){
		 List<String> total = new ArrayList<String>();
		 for(File f : listFile(dir, filter)){
			 total.add(f.getAbsolutePath());
		 }
		 return total;
	 }
	 
	 public static List<String> list(String dirPath){return list(new File(dirPath));}
	 
	 public static List<String> list(String dirPath, FilenameFilter filter){return list(new File(dirPath), filter);}
	 
	 public static List<String> listAll(File dir){
		 List<String> total = new ArrayList<String>();
		 for(File f : listAllFile(dir)){
			 total.add(f.getAbsolutePath());
		 }
		 return total;
	 }
	 
	 public static List<String> listAll(String dirPath){return listAll(new File(dirPath));}
	 
	 
	 public static List<File> listFile(File dir){return list(dir, false);}
	 
	 public static List<File> listFile(String dirPath){return list(new File(dirPath), false);}
	 
	 public static List<File> listAllFile(File dir){return list(dir, true);}
	 
	 public static List<File> listAllFile(String dirPath){return list(new File(dirPath), true);}
	 
	 public static List<File> listFile(File dir, FilenameFilter filter){return list(dir, false, filter);}
	 
	 public static List<File> listFile(String dirPath, FilenameFilter filter){return list(new File(dirPath), false, filter);}
	 
	 public static List<File> listAllFile(File dir, FilenameFilter filter){return list(dir, true, filter);}
	 
	 public static List<File> listAllFile(String dirPath, FilenameFilter filter){return list(new File(dirPath), true, filter);}
	
	 private static void recursiveDir(File file, List<File> total, boolean listDir){
		 if(file.isFile()){
			 total.add(file);
			 return;
		 }else if(file.isDirectory()){
			 if(listDir)
				 total.add(file);
			 for(File f : file.listFiles())
				 recursiveDir(f, total, listDir);
		 }
	 }
	 
	 private static void recursiveDir(File file, List<File> total,
			 boolean listDir, FilenameFilter filter){
		 if(file.isFile()){
			 total.add(file);
			 return;
		 }else if(file.isDirectory()){
			 if(listDir)
				 total.add(file);
			 for(File f : file.listFiles(filter))
				 recursiveDir(f, total, listDir, filter);
		 }
	 }
	 
	 private static List<File> list(File dir, boolean listDir){
		 ArrayList<File> totals = new ArrayList<File>();
		 recursiveDir(dir, totals, listDir);
		 return totals;
	 }
	 
	 private static List<File> list(File dir, boolean listDir, FilenameFilter filter){
		 ArrayList<File> totals = new ArrayList<File>();
		 recursiveDir(dir, totals, listDir, filter);
		 return totals;
	 }
 
	 private static <T> void recursiveHandleDir(File file, Collection<T> results, FileHandler handler){
		 if(file.isFile()){
			 handler.handle(file, results);
			 return;
		 }else if(file.isDirectory()){
			 for(File f : file.listFiles())
				 recursiveHandleDir(f, results, handler);
		 }
	 }
	 
	 public static <T> void handleFile(String path, Collection<T> results,FileHandler handler){
		 recursiveHandleDir(new File(path), results, handler);
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
