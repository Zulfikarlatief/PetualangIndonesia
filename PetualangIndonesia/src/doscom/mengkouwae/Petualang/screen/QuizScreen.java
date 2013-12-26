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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import doscom.mengkouwae.Petualang.PetualangIndonesia;

public class QuizScreen implements Screen{
	
	PetualangIndonesia game;
	
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
	
	String daerah;
	
	OrthographicCamera camera;
	
	
	public QuizScreen(PetualangIndonesia game,String daerah) {
		this.game = game;
		this.daerah =daerah;
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
//		batch.draw(bgScreen,0,0);
		
		batch.draw(animalPic,120,Gdx.graphics.getHeight()-40-animalPic.getHeight());
		
		colisionDetect(batch, delta);
		
		if(pictSpawnTick>=3){
			if(!colRec1)
				rectangle1.draw(batch);
			
			if(!colRec2)
				rectangle2.draw(batch);
			
			if(!colRec3)
				rectangle3.draw(batch);
			
			if(!colRec4)
				rectangle4.draw(batch);
			
			rectangle5.draw(batch);
			rectangle6.draw(batch);
			rectangle7.draw(batch);
			rectangle8.draw(batch);
			
		}
		
//		if(failed){
//			batch.draw(failScreen,0,0);
//			
//		}
		
		batch.end();
		
		update(delta);
	}
	
	private void colisionDetect(SpriteBatch batch,float delta){
		
		if(rectangle1.getBoundingRectangle().overlaps(ansRect1)||colRec1){
			colRec1 = true;
		}else{
			batch.draw(emptySpace,220,Gdx.graphics.getHeight()-40-emptySpace.getHeight());
		}
		
		if(rectangle2.getBoundingRectangle().overlaps(ansRect2)||colRec2){
			colRec2 = true;
		}else{
			batch.draw(emptySpace,320,Gdx.graphics.getHeight()-240-emptySpace.getHeight());
		}
		
		if(rectangle3.getBoundingRectangle().overlaps(ansRect3)||colRec3){
			colRec3 = true;
		}else{
			batch.draw(emptySpace,120,Gdx.graphics.getHeight()-340-emptySpace.getHeight());
		}
		
		if(rectangle4.getBoundingRectangle().overlaps(ansRect4)||colRec4){
			colRec4 = true;
		}else{
			batch.draw(emptySpace,220,Gdx.graphics.getHeight()-140-emptySpace.getHeight());
		}
		
		
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
			
//			System.out.println(touchpos.x+"   "+touchpos.y);
//			System.out.println(rectangle1.getX()+"   "+rectangle1.getY());
		}
		
		if(timeCount>=30){
			failed = true;
			
		}
		
		
	}

	private void updateInput(float delta){
	//	int move =  picVelocity * (delta/ facMovement);
		int move = 4;
		//hero move
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			
			
			// To bounding / can't move if finger outside sprite
			spriteInit = spriteChs(touchPos,spriteInit);
//			if(rectangle1.getBoundingRectangle().contains(touchPos.x,touchPos.y-80+rectangle1.getY())){
//				
//			}
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
				
				System.out.println(touchPos.x+"   "+touchPos.y);
			}
		}
		
		
		if(!Gdx.input.isTouched()){
			initPos();
		}
	}
	
	private Sprite spriteChs(Vector3 touchpos,Sprite sprite){
		float x=touchpos.x;
		float y=touchpos.y;
		
		if(rectangle1.getBoundingRectangle().contains(x, y-recPos1y+rectangle1.getY())){
			System.out.println("1");
			return rectangle1;
		}
		else if (rectangle2.getBoundingRectangle().contains(x, y-recPos2y+rectangle2.getY())) {
			System.out.println("2");
			return rectangle2;
		}
		else if (rectangle3.getBoundingRectangle().contains(x, y-recPos3y+rectangle3.getY())) {
			return rectangle3;
		}
		else if (rectangle4.getBoundingRectangle().contains(x, y-recPos4y+rectangle4.getY())) {
			return rectangle4;
		}
		else if (rectangle5.getBoundingRectangle().contains(x, y-recPos5y+rectangle5.getY())) {
			return rectangle5;
		}
		else if (rectangle6.getBoundingRectangle().contains(x, y-recPos6y+rectangle6.getY())) {
			return rectangle6;
		}
		else if (rectangle7.getBoundingRectangle().contains(x, y-recPos7y+rectangle7.getY())) {
			return rectangle7;
		}
		else if (rectangle8.getBoundingRectangle().contains(x, y-recPos8y+rectangle8.getY())) {
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
		
//		bgScreen = new Texture(Gdx.files.internal("ui/peta.png"));
		animalPic = new Texture(Gdx.files.internal("textures/rangkong.png"));
		
		emptySpace = new Texture(Gdx.files.internal("textures/tanya.png"));
		
		choicePic1 = new Texture(Gdx.files.internal("data/pic1/rangkongP2.png"));
		choicePic2 = new Texture(Gdx.files.internal("data/pic1/rangkongP9.png"));
		choicePic3 = new Texture(Gdx.files.internal("data/pic1/rangkongP10.png"));
		choicePic4 = new Texture(Gdx.files.internal("data/pic1/rangkongP5.png"));
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
		
		ansRect1 = new Rectangle(245,Gdx.graphics.getHeight()-65-emptySpace.getHeight()/2,emptySpace.getWidth()/2,emptySpace.getHeight()/2);
		ansRect2 = new Rectangle(345,Gdx.graphics.getHeight()-265-emptySpace.getHeight()/2,emptySpace.getWidth()/2,emptySpace.getHeight()/2);
		ansRect3 = new Rectangle(145,Gdx.graphics.getHeight()-365-emptySpace.getHeight()/2,emptySpace.getWidth()/2,emptySpace.getHeight()/2);
		ansRect4 = new Rectangle(245,Gdx.graphics.getHeight()-165-emptySpace.getHeight()/2,emptySpace.getWidth()/2,emptySpace.getHeight()/2);
		
//		rectangle1 = new Rectangle(95,300,choicePic1.getWidth(),choicePic1.getHeight());
//		rectangle2 = new Rectangle(325,300,choicePic2.getWidth(),choicePic2.getHeight());
//		rectangle3 = new Rectangle(555,300,choicePic3.getWidth(),choicePic3.getHeight());
//	
//		AnswerText = new Texture(Gdx.files.internal("textures/ship.png"));

		answerSound = new Gdx().audio.newSound(Gdx.files.internal("audio/MnButton.wav"));
		
	}
	
	private void initPict(String daerah){
		if(daerah=="papua"){
			
		}
		else if (daerah == "") {
			
			animalPic = new Texture(Gdx.files.internal("textures/rangkong.png"));
			
			choicePic1 = new Texture(Gdx.files.internal("data/pic1/rangkongP2.png"));
			choicePic2 = new Texture(Gdx.files.internal("data/pic1/rangkongP9.png"));
			choicePic3 = new Texture(Gdx.files.internal("data/pic1/rangkongP10.png"));
			choicePic4 = new Texture(Gdx.files.internal("data/pic1/rangkongP5.png"));
			choicePic5 = new Texture(Gdx.files.internal("data/pic1/rangkongP11.png"));
			choicePic6 = new Texture(Gdx.files.internal("data/pic1/rangkongP8.png"));
			choicePic7 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP12.png"));
			choicePic8 = new Texture(Gdx.files.internal("data/pic2/cendrawasihP5.png"));
		}
		
	}
	
	private void initRandom(int random){
		switch (random) {
		case 0:
			recPos1x = 690;recPos1y = 10;
			recPos2x = 570;recPos2y = 10;
			recPos3x = 570;recPos3y = 370;
			recPos4x = 690;recPos4y = 250;
			recPos5x = 570;recPos5y = 130;
			recPos6x = 570;recPos6y = 250;
			recPos7x = 690;recPos7y = 130;
			recPos8x = 690;recPos8y = 370;
			
			break;

		case 1:
			recPos1x = 690;recPos1y = 370;
			recPos2x = 690;recPos2y = 130;
			recPos3x = 570;recPos3y = 250;
			recPos4x = 570;recPos4y = 130;
			recPos5x = 690;recPos5y = 250;
			recPos6x = 570;recPos6y = 370;
			recPos7x = 570;recPos7y = 10;
			recPos8x = 690;recPos8y = 10;
			
			break;
			
		case 2:
			recPos1x = 570;recPos1y = 370;
			recPos2x = 690;recPos2y = 130;
			recPos3x = 690;recPos3y = 10;
			recPos4x = 690;recPos4y = 370;
			recPos5x = 570;recPos5y = 250;
			recPos6x = 570;recPos6y = 130;
			recPos7x = 570;recPos7y = 10;
			recPos8x = 690;recPos8y = 250;
			
			break;
			
		default:
			recPos1x = 690;recPos1y = 130;
			recPos2x = 570;recPos2y = 370;
			recPos3x = 570;recPos3y = 10;
			recPos4x = 690;recPos4y = 250;
			recPos5x = 690;recPos5y = 370;
			recPos6x = 570;recPos6y = 250;
			recPos7x = 690;recPos7y = 10;
			recPos8x = 570;recPos8y = 130;
			
			break;
		}
	}
	
	private void initPos(){
		
			rectangle1.setPosition(recPos1x,Gdx.graphics.getHeight()-recPos1y-choicePic1.getHeight());
			rectangle2.setPosition(recPos2x,Gdx.graphics.getHeight()-recPos2y-choicePic2.getHeight());
			rectangle3.setPosition(recPos3x,Gdx.graphics.getHeight()-recPos3y-choicePic3.getHeight());
			rectangle4.setPosition(recPos4x,Gdx.graphics.getHeight()-recPos4y-choicePic4.getHeight());
			rectangle5.setPosition(recPos5x,Gdx.graphics.getHeight()-recPos5y-choicePic5.getHeight());
			rectangle6.setPosition(recPos6x,Gdx.graphics.getHeight()-recPos6y-choicePic6.getHeight());
			rectangle7.setPosition(recPos7x,Gdx.graphics.getHeight()-recPos7y-choicePic7.getHeight());
			rectangle8.setPosition(recPos8x,Gdx.graphics.getHeight()-recPos8y-choicePic8.getHeight());
		
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
