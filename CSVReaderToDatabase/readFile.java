import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class readFile {
	public static ArrayList<String> arr = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		selectAllCSV("G:\\Warehouse\\eclipseWorkspace\\test\\src\\test");
	}

	public static void selectAllCSV(String folderPath) throws IOException {
		List<Path> files =  Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile).collect(Collectors.toList());
		for(int i = 0;i < files.size(); i++){
			String fullpath = files.get(i).toString();
			if(fullpath.substring(fullpath.length() - 4).equals(".csv")) {
				accessCSV(fullpath);
		}
	}
}

public static void accessCSV(String filePath) throws IOException{
	FileReader r = new FileReader(filePath); //path
	BufferedReader br = new BufferedReader(r);
	String str = br.readLine();
	do{
		System.out.println(str);
		str = br.readLine();
	}while(str!=null);
		br.close();
	}
}
