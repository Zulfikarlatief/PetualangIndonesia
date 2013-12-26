package doscom.mengkouwae.Petualang;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import doscom.mengkouwae.Petualang.screen.SplashScreen;

public class 
PetualangIndonesia extends Game {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 480;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private AssetManager assets;
	
	public SpriteBatch getBatch() {
		return batch;
	}


	public OrthographicCamera getCamera() {
		return camera;
	}


	public AssetManager getAssets() {
		return assets;
	}
	
	public float getViewPortWidth() {
		return this.camera.viewportWidth;
	}
	
	public float getViewPortHeight() {
		return this.camera.viewportHeight;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		assets = new AssetManager();
		
		camera.setToOrtho(false,WIDTH,HEIGHT);
		
		Texture.setAssetManager(assets);
		Texture.setEnforcePotImages(false);
		
		setScreen(new SplashScreen(this));
	} 
	
	
	
}
