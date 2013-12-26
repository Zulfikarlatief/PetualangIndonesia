package doscom.mengkouwae.Petualang.control;

import com.badlogic.gdx.Screen;

import doscom.mengkouwae.Petualang.PetualangIndonesia;


public abstract class GameScreen implements Screen{
	
	private PetualangIndonesia game;

	public GameScreen(PetualangIndonesia game) {
		this.game = game;
	}
	
	public PetualangIndonesia getGame(){
		return game;
	}
	
	public void render(float delta){
		update(delta);
		getGame().getCamera().update();
		draw(delta);
	}
	
	public abstract void update(float deltatime);
	public abstract void draw(float deltatime);

}
