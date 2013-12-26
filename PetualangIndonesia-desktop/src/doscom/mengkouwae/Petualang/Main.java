package doscom.mengkouwae.Petualang;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PetualangIndonesia";
		cfg.useGL20 = false;
//		cfg.width = 480;
//		cfg.height = 320;
		cfg.width = 800;
		cfg.height = 480;	
		
		new LwjglApplication(new PetualangIndonesia(), cfg);
	}
}
