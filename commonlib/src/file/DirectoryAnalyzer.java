package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DirectoryAnalyzer {
	private static ArrayList<String> files = new ArrayList<String>();
	public static boolean isListDir = false;
	
	private static void recursiveDir(String root){
		File f = new File(root);
//		System.out.println(root);
		if(!isListDir){
			if(f.isFile())
				files.add(root);
		}else{
			files.add(root);
		}			
		if(f.isFile()) return;
		List<File> fis = Arrays.asList(f.listFiles());
		Collections.sort(fis, new Comparator<File>(){
		    @Override
		    public int compare(File o1, File o2) {
			if(o1.isDirectory() && o2.isFile())
			    return -1;
			if(o1.isFile() && o2.isDirectory())
		    	    return 1;
			return o1.getName().compareTo(o2.getName());
		    }
		});//升序排列
		for(File fi:fis){
			recursiveDir(root+File.separator+fi.getName());
		}
	}
	
	public static void setListDir(boolean is){
		isListDir = is;
	}
	
	public static ArrayList<String> analyze(String rootPath){
		if(!files.isEmpty())
			files.clear();
		recursiveDir(rootPath);
		return files;		
	}
	
	public static ArrayList<String> analyzeDir(String dirPath){
		return null;
	}
	
	public static List<File> rank(String path){
		List<File> files = Arrays.asList(new File(path).listFiles());
		Collections.sort(files, new Comparator<File>(){
		    @Override
		    public int compare(File o1, File o2) {
			if(o1.isDirectory() && o2.isFile())
			    return -1;
			if(o1.isFile() && o2.isDirectory())
		    	    return 1;
			return o1.getName().compareTo(o2.getName());
		    }
		});//升序排列

		for(File f : files)
		    System.out.println(f.getName());
		return files;
	}
	
	public static void main(String[] args){
		//recursiveDir("D:\\test");
		String path = "D:\\ebank\\perbank\\designFiles\\mvcs\\perbank";
		int size = 0;
		ArrayList<String> pres = new  ArrayList<String>();
		
		ArrayList<String> fiss = analyze(path);
		int start = 0,end = 0, end2 = 0;
		for(String f:fiss){
			if(f.indexOf(".svn") >= 0)
				continue;
			if(f.indexOf(".mvc") >= 0){
				size ++;
				System.out.println(f.substring(path.length()+1));
				f = f.substring(path.length()+1);
				start = f.lastIndexOf("\\") + 1;
				end = f.lastIndexOf(".mvc");
				end2 = f.indexOf("\\");
				if(end2 == -1) 
					pres.add(f.substring(0,end));
				else
					pres.add(f.substring(0, end2)+"\\"+f.substring(start, end));
			}	
		}
		for(String ss:pres)
			System.out.println(ss);
		System.out.println("size:"+size+",pres size:"+pres.size());
		
		String destPath = "D:\\ebank\\perbank\\WebContent\\WEB-INF\\mvcs\\perbank\\actions";
		ArrayList<String> destFile = new ArrayList<String>();
		ArrayList<String> notFound = new ArrayList<String>();
		int match = 0;
		File file = new File(destPath);
		
		for(File f:file.listFiles()){
			if(f.isFile())
				destFile.add(f.getName());			
		}
		System.out.println("destFile num:"+destFile.size());
		String tmp = null;
		boolean mark = false;
		for(String df:destFile){		
			mark = false;
			for(String f:pres){
				tmp = f.substring(f.lastIndexOf("\\")+1)+".xml";
				if(tmp.equals(df)){
					mark = true;
					match ++;
				}
			}
			if(!mark){
				notFound.add(df);
			}
		}
		for(String nf:notFound){
			System.out.println(nf);
		}
		System.out.println("match:"+match);
		
		
		//rank(path);
	}

}
