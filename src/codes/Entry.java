package codes;


public class Entry implements Observer{
	private Observable observable;
	private int entryCount;

	public int getEntryCount() {
		return entryCount;
	}

	public void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}


    public void setObservable(Observable observable) {
        this.observable = observable;
    }
	
	@Override
	public void notify(String message, int entryCount) {
		// TODO Auto-generated method stub
		//System.out.println(message + " Entry count : " + getEntryCount());
	}

}
