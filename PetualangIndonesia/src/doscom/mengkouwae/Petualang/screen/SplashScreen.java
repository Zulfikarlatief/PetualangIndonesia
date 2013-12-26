package doscom.mengkouwae.Petualang.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import doscom.mengkouwae.Petualang.PetualangIndonesia;

import doscom.mengkouwae.Petualang.control.GameScreen;
import doscom.mengkouwae.Petualang.control.SpriteTween;


public class SplashScreen extends GameScreen{
	
	
	
	Texture logo;
	Sprite logoSprite;
	SpriteBatch batch;
	TweenManager manager;
	TextureRegion logoRegion;
	
	public SplashScreen(PetualangIndonesia game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		logo = new Texture("ui/doscom.png");
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		logoSprite = new Sprite(logo);
		logoSprite.setColor(1,1,1,0);
		
		logoSprite.setX(Gdx.graphics.getWidth() /2  - (logoSprite.getWidth()/2));
		logoSprite.setY(Gdx.graphics.getHeight() /2 - (logoSprite.getHeight()/3));
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween() );
		
		manager = new TweenManager();
		
		TweenCallback cb = new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				// TODO Auto-generated method stub
				getGame().setScreen(new MainMenuScreen(getGame()));
			}
		};
		
		Tween.to(logoSprite, SpriteTween.ALPHA, 2f)
				.target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2.5f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		if(logo!=null)
			this.logo.dispose();
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
		
	}

	@Override
	public void draw(float deltatime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(deltatime);
		SpriteBatch batch = getGame().getBatch();
		batch.begin();
		logoSprite.draw(batch);
		batch.end();
	}

}
