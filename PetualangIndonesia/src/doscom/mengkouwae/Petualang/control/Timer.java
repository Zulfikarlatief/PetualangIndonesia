package doscom.mengkouwae.Petualang.control;

public class Timer {
	private float delay;
	private float currenTick;
	private boolean active;
	public Timer(float delay) {
		this.delay = delay;
		active = true;
		currenTick = 0;
	}
	public float getDelay() {
		return delay;
	}
	public void setDelay(float delay) {
		this.delay = delay;
	}
	public float getCurrenTick() {
		return currenTick;
	}
	public void setCurrenTick(float currenTick) {
		this.currenTick = currenTick;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean step(float deltatime){
		if(active){
			//delta time selisih antar waktu
			currenTick += deltatime;
			if(currenTick>delay){
				currenTick = 0;
				return true;
			}
		}
		return false;
	}
}
