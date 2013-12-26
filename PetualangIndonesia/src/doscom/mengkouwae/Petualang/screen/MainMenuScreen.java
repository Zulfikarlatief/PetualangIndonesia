package doscom.mengkouwae.Petualang.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import doscom.mengkouwae.Petualang.PetualangIndonesia;
import doscom.mengkouwae.Petualang.control.GameScreen;


public class MainMenuScreen extends GameScreen{
	
	Texture bg;
	Texture bgMenu;
	TextureRegion playTexture;
	TextureRegion aboutTexture;
	TextureRegion exitTexture;
	
	
	Rectangle playRectangle;
	Rectangle aboutRectangle;
	Rectangle exitRectangle;
	
	Button buttonExit;
	
	TextureAtlas atlas;
	
	Skin skin;
	
	Music bgm;
	
	Sound buttonSound;
	
	Stage stage;
	
	Boolean buttonPushMulai = false;
	Boolean buttonPushKeluar = false;
	Boolean buttonPushTentang = false;
	
	float delay=0;
	
	public MainMenuScreen(PetualangIndonesia game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
//		Gdx.input.setInputProcessor(stage);
//		
//		ButtonStyle style = new ButtonStyle();
//		style.up = skin.getDrawable("keluar");
//		style.down = skin.getDrawable("keluar2");
//		
//		buttonExit = new Button(style);
//		buttonExit.setWidth(90);
//		buttonExit.setHeight(60);
//		buttonExit.setX(Gdx.graphics.getWidth()/2 - buttonExit.getWidth()/2);
//		buttonExit.setY(Gdx.graphics.getHeight()/2 - buttonExit.getHeight()/2);
//		
//		buttonExit.addListener(new InputListener(){
//			public boolean touchDown(InputEvent event,float x,
//					float y,int pointer,int button){
//				return true;
//			}
//			
//			public void touchUp(InputEvent event,float x,
//					float y,int pointer,int button){
//				System.out.println("ok");
//			}
//		});
//		
//		stage.addActor(buttonExit);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		atlas = new TextureAtlas("ui/MenuButton.atlas");
		skin = new Skin();
		skin.addRegions(atlas);
		
		bg = new Texture("ui/peta.png");
		bgMenu = new Texture("ui/bgMenu.png");
		playTexture = skin.get("mulai",TextureRegion.class);
		aboutTexture = skin.get("tentang",TextureRegion.class);
		exitTexture = skin.get("keluar",TextureRegion.class);
		
		bgm = Gdx.audio.newMusic(Gdx.files.internal("audio/scene3.wav"));
		
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("audio/MnButton.wav"));
		
		playRectangle = new Rectangle(300,Gdx.graphics.getHeight()-200-playTexture.getRegionHeight(),playTexture.getRegionWidth(),playTexture.getRegionHeight());
		exitRectangle = new Rectangle(300,Gdx.graphics.getHeight()-60-exitTexture.getRegionHeight(),exitTexture.getRegionWidth(),exitTexture.getRegionHeight());
		aboutRectangle = new Rectangle(300,Gdx.graphics.getHeight()-130-aboutTexture.getRegionHeight(),aboutTexture.getRegionWidth(),aboutTexture.getRegionHeight());
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		bgm.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float deltatime) {
		// TODO Auto-generated method stub
//		if(!bgm.isPlaying())
//			bgm.play();
		
		if(buttonPushMulai==true){
			delay += deltatime;
			if(delay>0.5)
				getGame().setScreen(new WorldScreen(getGame()));
		}
		if(buttonPushTentang==true){
			delay += deltatime;
			if(delay>0.5)
				getGame().setScreen(new WorldScreen(getGame()));
		}
		if(buttonPushKeluar==true){
			delay += deltatime;
			if(delay>0.5)
				getGame().setScreen(new WorldScreen(getGame()));
		}

		
		if(Gdx.input.justTouched()){
			Vector3 touchpos = new Vector3
					(Gdx.input.getX(),Gdx.input.getY(),0);

			if(playRectangle.contains(touchpos.x,touchpos.y)){
				playTexture = skin.get("mulai2",TextureRegion.class);
				buttonPushMulai = true;
				buttonPushKeluar = false;
				buttonPushTentang = false;
				buttonSound.play();
			}
			
			if(exitRectangle.contains(touchpos.x,touchpos.y)){
				exitTexture = skin.get("keluar2",TextureRegion.class);
				buttonPushKeluar = true;
				buttonPushMulai = false;
				buttonPushTentang = false;
				buttonSound.play();
			}
			
			if(aboutRectangle.contains(touchpos.x,touchpos.y)){
				aboutTexture = skin.get("tentang2",TextureRegion.class);
				buttonPushTentang = true;
				buttonPushMulai = false;
				buttonPushKeluar = false;
				buttonSound.play();
			}
			
		}
	}

	@Override
	public void draw(float deltatime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	
		
		SpriteBatch batch = getGame().getBatch();
		
		batch.begin();
		
		batch.draw(bg, 0, 0);
		batch.draw(bgMenu, Gdx.graphics.getWidth() /2  - (bgMenu.getWidth()/2)
				, Gdx.graphics.getHeight() /2 - (bgMenu.getHeight()/2));
		batch.draw(playTexture,300,200);
		batch.draw(aboutTexture,300,130);
		batch.draw(exitTexture,300,60);		
		batch.end();
	}

}
