package codes;

public class Exit implements Observer{
	private Observable observable;
	private int exitCount;

	public int getExitCount() {
		return exitCount;
	}

	public void setExitCount(int exitCount) {
		this.exitCount = exitCount;
	}


    public void setObservable(Observable observable) {
        this.observable = observable;
    }
	
	@Override
	public void notify(String message, int exitCount) {
		// TODO Auto-generated method stub
		//System.out.println(message + " Exit count : " + getExitCount());
	}

}