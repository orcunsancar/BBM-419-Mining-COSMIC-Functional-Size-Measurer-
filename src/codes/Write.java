package codes;

public class Write implements Observer{
	private Observable observable;
	private int writeCount;

	public int getWriteCount() {
		return writeCount;
	}

	public void setWriteCount(int writeCount) {
		this.writeCount = writeCount;
	}


    public void setObservable(Observable observable) {
        this.observable = observable;
    }
	
	@Override
	public void notify(String message, int writeCount) {
		// TODO Auto-generated method stub
		//System.out.println(message + " Write count : " + getWriteCount());
	}

}
