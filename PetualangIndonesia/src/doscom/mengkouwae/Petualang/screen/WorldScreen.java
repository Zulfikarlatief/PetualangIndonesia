package doscom.mengkouwae.Petualang.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

import doscom.mengkouwae.Petualang.PetualangIndonesia;
import doscom.mengkouwae.Petualang.control.GameScreen;

public class WorldScreen implements Screen{
	
	PetualangIndonesia game;
	
	Texture bg;
	Texture dot;
	
	Circle dotCircleSul;
	Circle dotCirclePap;
	Circle dotCircleBal;
	Circle dotCircleFlo;
	
	public WorldScreen(PetualangIndonesia game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch batch = this.game.getBatch();
		
		batch.begin();
		batch.draw(bg,0,0);
		batch.draw(dot,440-dot.getWidth()/2,Gdx.graphics.getHeight()-245-dot.getHeight()/2);
		batch.draw(dot,750-dot.getWidth()/2,Gdx.graphics.getHeight()-260-dot.getHeight()/2);
		batch.draw(dot,360-dot.getWidth()/2,Gdx.graphics.getHeight()-330-dot.getHeight()/2);
		batch.draw(dot,440-dot.getWidth()/2,Gdx.graphics.getHeight()-335-dot.getHeight()/2);
		
		batch.end();
		
		update(delta);
	}
	
	private void update(float delta) {
		if(Gdx.input.justTouched()){
			Vector3 touchpos = new Vector3
					(Gdx.input.getX(),Gdx.input.getY(),0);
			System.out.println(touchpos.x+"   "+touchpos.y);
			if(dotCircleSul.contains(touchpos.x,touchpos.y)){
				this.game.setScreen(new QuizScreen(this.game,"tes"));
			}
			else if(dotCirclePap.contains(touchpos.x,touchpos.y)){
				this.game.setScreen(new QuizScreen2(this.game));	
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		bg = new Texture("ui/peta.png");
		dot = new Texture("textures/rdot.png");
		
		dotCircleSul = new Circle(440,245, dot.getWidth());
		dotCirclePap = new Circle(750,260, dot.getWidth());
		dotCircleBal = new Circle(360,330, dot.getWidth());
		dotCircleFlo = new Circle(440,335, dot.getWidth());
		
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
