package codes;


import java.util.ArrayList;
import java.util.List;

public class NoticeObservable implements Observable {

    private List<Observer> observerList = new ArrayList<>();
    private String message = "Notice... !";

    Entry entry = new Entry();
    Read read= new Read();
    Write write= new Write();
    
    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.notify(message,entry.getEntryCount()); 
             
            
        }
    }
   
    

}