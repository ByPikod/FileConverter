package me.pikod.main;
import java.io.File;

public class Main extends Thread {
	public static void main(String args[]) { 
		new Main().start();
		
		if(args.length < 1) {
			System.out.println("Lütfen bir konum seçin!");
			return;
		}
		if(args.length < 2) {
			System.out.println("Lütfen bir biçim seçin!");
			return;
		}
		
		boolean changeName = true;
		if(args.length == 3) {
			changeName = Boolean.parseBoolean(args[2]);
		}
		
		String format = args[1];
		String path = args[0];
		
		File file = new File(path);
		int i = 0;
		if(!file.exists()) {
			System.out.println("'"+file.getPath()+"' konumu çevirilebilecek biçimde deðil.");
			return;
		}

		File[] list = file.listFiles();
		
		for(File key : list) {
			if(changeName) {
				key.renameTo(new File(key.getParentFile().getPath()+"/"+i+"."+format));
			}else {
				String keyName = key.getName();
				if(keyName.split(".").length < 1) {
					key.renameTo(new File(key.getParentFile().getPath()+"/"+keyName+"."+format));
					i++;
					continue;
				}
				key.renameTo(new File(key.getParentFile().getPath()+"/"+key.getName().substring(0, key.getName().lastIndexOf("."))+"."+format));
			}
			i++;
		}
		System.out.println("Baþarýlý, "+i+" adet dosya "+time+" süre içerisinde "+format+" formatýna çevirildi! \n (Program sadece uzantýyý deðiþtirir. Eðer kodlama biçimi avi ise mp3 dönüþtürme özelliði yoktur.)");
	}
	static float time = 0;
	@Override
	public void run() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time += 0.001;
	}
}
