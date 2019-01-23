package codes;


public interface Observable {
    void addObserver(Observer observer);
    void notifyObserver();
}