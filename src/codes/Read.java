package codes;

public class Read implements Observer{
	private Observable observable;
	private int readCount;

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


    public void setObservable(Observable observable) {
        this.observable = observable;
    }
	
	@Override
	public void notify(String message, int readCount) {
		// TODO Auto-generated method stub
		//System.out.println(message + " Read count : " + getReadCount());
	}

}
