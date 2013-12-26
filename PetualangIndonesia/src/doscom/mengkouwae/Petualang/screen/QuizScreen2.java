package doscom.mengkouwae.Petualang.screen;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import doscom.mengkouwae.Petualang.PetualangIndonesia;

public class QuizScreen2 implements Screen{
	
	PetualangIndonesia game;
	
	OrthographicCamera camera;
	
	Texture choicePic1;
	Texture choicePic2;
	Texture choicePic3;
	Texture choicePic4;
	Texture choicePic5;
	Texture choicePic6;
	Texture choicePic7;
	Texture choicePic8;
	
	Texture animalPic;
	Texture bgScreen;
	Texture emptySpace;
	Texture failScreen;
	Texture retryIcon;
	Texture backIcon;
	
	Sound answerSound;
	
	Sprite rectangle1;
	Sprite rectangle2;
	Sprite rectangle3;
	Sprite rectangle4;
	Sprite rectangle5;
	Sprite rectangle6;
	Sprite rectangle7;
	Sprite rectangle8;
	
	Sprite spriteInit;
	
	Rectangle ansRect1;
	Rectangle ansRect2;
	Rectangle ansRect3;
	Rectangle ansRect4;
	
	float pictSpawnTick;
	float timeCount;
	
	float facMovement= 0.1f;
	
	int picVelocity = 50;
	
	int recPos1x;
	int recPos2x;
	int recPos3x;
	int recPos4x;
	int recPos5x;
	int recPos6x;
	int recPos7x;
	int recPos8x;
	
	int recPos1y;
	int recPos2y;
	int recPos3y;
	int recPos4y;
	int recPos5y;
	int recPos6y;
	int recPos7y;
	int recPos8y;

	int random;
	Random position = new Random();
	
	boolean done=false;
	boolean colRec1=false;
	boolean colRec2=false;
	boolean colRec3=false;
	boolean colRec4=false;
	
	boolean failed = false;
	
	public QuizScreen2(PetualangIndonesia game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		SpriteBatch batch = this.game.getBatch();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		
		batch.begin();
		
		batch.draw(animalPic,250,40);
		
		//if(pictSpawnTick>=3){
			
				rectangle1.draw(batch);
			
			
				rectangle2.draw(batch);
			
			
				rectangle3.draw(batch);
			
		
				rectangle4.draw(batch);
			
			rectangle5.draw(batch);
			rectangle6.draw(batch);
			rectangle7.draw(batch);
			rectangle8.draw(batch);
			
	//	}

		
		
		batch.end();
		
		update(delta);
	}
	
	private void colisionDetect(SpriteBatch batch,float delta){
		
		
		
		
	}
	
	
	private void update(float delta) {
		
		pictSpawnTick += delta;
		
		if(pictSpawnTick>=3 && !failed){
			timeCount += delta;
			updateInput(delta);
		}
		
		
		if(Gdx.input.justTouched()){
			Vector3 touchpos = new Vector3
					(Gdx.input.getX(),Gdx.input.getY(),0);
			
			camera.unproject(touchpos);
			
			
		}
		
	}

	private void updateInput(float delta){
		
		int move = 4;
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(touchPos);
			
			spriteInit = spriteChs(touchPos,spriteInit);
			
			if(spriteInit!=null){
				float centerX = spriteInit.getX() + (choicePic1.getWidth()/2);
				float centerY = Gdx.graphics.getHeight() - spriteInit.getY() - choicePic1.getHeight()  + (choicePic1.getHeight()/2);
				
			
				//up
				if(touchPos.y > centerY )
					spriteInit.translateY(-move);
			
				//down
				if(touchPos.y < centerY )
					spriteInit.translateY(move);
			
				//left
				if(touchPos.x < centerX )
					spriteInit.translateX(-move);
			
				//right
				if(touchPos.x > centerX )
					spriteInit.translateX(move);
		
			}	
		}
		
		if(!Gdx.input.isTouched()){
			initPos();
		}
		
	}
	
	private Sprite spriteChs(Vector3 touchpos,Sprite sprite){
		float x=touchpos.x;
		float y=touchpos.y;
		
		if(rectangle1.getBoundingRectangle().contains(x, y)){
			System.out.println("1");
			return rectangle1;
		}
		else if (rectangle2.getBoundingRectangle().contains(x, y)) {
			System.out.println("2");
			return rectangle2;
		}
		else if (rectangle3.getBoundingRectangle().contains(x, y)) {
			return rectangle3;
		}
		else if (rectangle4.getBoundingRectangle().contains(x, y)) {
			return rectangle4;
		}
		else if (rectangle5.getBoundingRectangle().contains(x, y)) {
			return rectangle5;
		}
		else if (rectangle6.getBoundingRectangle().contains(x, y)) {
			return rectangle6;
		}
		else if (rectangle7.getBoundingRectangle().contains(x, y)) {
			return rectangle7;
		}
		else if (rectangle8.getBoundingRectangle().contains(x, y)) {
			return rectangle8;
		}
		else
			return sprite;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		random = position.nextInt(4);
		
		initRandom(random);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		
		animalPic = new Texture(Gdx.files.internal("textures/cendrawasih.png"));
		
		emptySpace = new Texture(Gdx.files.internal("textures/tanya.png"));
		
		choicePic1 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP1.png"));
		choicePic2 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP3.png"));
		choicePic3 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP8.png"));
		choicePic4 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP11.png"));
		choicePic5 = new Texture(Gdx.files.internal("data/pic1/rangkongP11.png"));
		choicePic6 = new Texture(Gdx.files.internal("data/pic1/rangkongP8.png"));
		choicePic7 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP12.png"));
		choicePic8 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP5.png"));

		rectangle1 = new Sprite(choicePic1);
		rectangle2 = new Sprite(choicePic2);
		rectangle3 = new Sprite(choicePic3);
		rectangle4 = new Sprite(choicePic4);
		rectangle5 = new Sprite(choicePic5);
		rectangle6 = new Sprite(choicePic6);
		rectangle7 = new Sprite(choicePic7);
		rectangle8 = new Sprite(choicePic8);
		
		rectangle1.setOrigin(-choicePic1.getWidth()/2,-choicePic1.getHeight()/2);
		rectangle2.setOrigin(-choicePic2.getWidth()/2,-choicePic2.getHeight()/2);
		rectangle3.setOrigin(-choicePic3.getWidth()/2,-choicePic3.getHeight()/2);
		rectangle4.setOrigin(-choicePic4.getWidth()/2,-choicePic4.getHeight()/2);
		rectangle5.setOrigin(-choicePic5.getWidth()/2,-choicePic5.getHeight()/2);
		rectangle6.setOrigin(-choicePic6.getWidth()/2,-choicePic6.getHeight()/2);
		rectangle7.setOrigin(-choicePic7.getWidth()/2,-choicePic7.getHeight()/2);
		rectangle8.setOrigin(-choicePic8.getWidth()/2,-choicePic8.getHeight()/2);
		
		initPos();
		
		
	}
	
	private void initRandom(int random){
		switch (random) {
		case 0:
			recPos1x = 15;recPos1y = 70;
			recPos2x = 135;recPos2y = 310;
			recPos3x = 565;recPos3y = 310;
			recPos4x = 685;recPos4y = 310;
			recPos5x = 15;recPos5y = 310;
			recPos6x = 135;recPos6y = 70;
			recPos7x = 565;recPos7y = 70;
			recPos8x = 685;recPos8y = 70;
			
			break;

		case 1:
			recPos1x = 15;recPos1y = 70;
			recPos2x = 135;recPos2y = 310;
			recPos3x = 565;recPos3y = 310;
			recPos4x = 685;recPos4y = 310;
			recPos5x = 15;recPos5y = 310;
			recPos6x = 135;recPos6y = 70;
			recPos7x = 565;recPos7y = 70;
			recPos8x = 685;recPos8y = 70;
			
			break;
			
		case 2:
			recPos1x = 15;recPos1y = 70;
			recPos2x = 135;recPos2y = 310;
			recPos3x = 565;recPos3y = 310;
			recPos4x = 685;recPos4y = 310;
			recPos5x = 15;recPos5y = 310;
			recPos6x = 135;recPos6y = 70;
			recPos7x = 565;recPos7y = 70;
			recPos8x = 685;recPos8y = 70;
			
			break;
			
		default:
			recPos1x = 15;recPos1y = 70;
			recPos2x = 135;recPos2y = 310;
			recPos3x = 565;recPos3y = 310;
			recPos4x = 685;recPos4y = 310;
			recPos5x = 15;recPos5y = 310;
			recPos6x = 135;recPos6y = 70;
			recPos7x = 565;recPos7y = 70;
			recPos8x = 685;recPos8y = 70;
			
			break;
		}
	}
	
	private void initPos(){
		
			rectangle1.setPosition(recPos1x,recPos1y);
			rectangle2.setPosition(recPos2x,recPos2y);
			rectangle3.setPosition(recPos3x,recPos3y);
			rectangle4.setPosition(recPos4x,recPos4y);
			rectangle5.setPosition(recPos5x,recPos5y);
			rectangle6.setPosition(recPos6x,recPos6y);
			rectangle7.setPosition(recPos7x,recPos7y);
			rectangle8.setPosition(recPos8x,recPos8y);
		
	}
	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
	
}
