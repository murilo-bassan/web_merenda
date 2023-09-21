package merenda.com.demo.utils;

public class FileUtils {

	public boolean isImagem(String contentType) {
		if (contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/png"))
			return true;
		else
			return false;
	}
}
	
