package idols;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Properties {

    //propertiesファイル配置ディレクトリ
    private String _dir = "./settings";
    //propertiesファイル名(.propertiesは不要)
    private String _source = "winSettings";

    private ResourceBundle _bundle = null;
	static private Properties _instance = new Properties();
	static public Properties GetInstance() {
		return _instance;
	}
	
	private Properties() {
		if(PlatformUtils.isWindows()) {
			_source = "winSettings";
		}
		else if(PlatformUtils.isLinux()) {
			_source = "linuxSettings";
		}
		_bundle = null;
	}
	
	public ResourceBundle GetBundle()
	{
		if(_bundle == null) {
			this.Initialize();
		}
		return _bundle;
	}
	
	public void Initialize() {

		try {
	        //取得処理
	        URLClassLoader urlLoader;
			urlLoader = new URLClassLoader(new URL[]{new File(_dir).toURI().toURL()});
			_bundle = ResourceBundle.getBundle(_source, Locale.getDefault(), urlLoader);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
