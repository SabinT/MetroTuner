/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner.interfaces;

/**
 *
 * @author Rae
 */
public interface IMetronome {

    void start();

    void stop();

    void setBPM(int bpm);

    int getBPM();

    void increaseTempo(int amount);

    void decreaseTempo(int amount);

    void addBPMObserver(IBPMObserver obs);

    void removeBPMObserver(IBPMObserver obs);

    void notifyBPMObservers();
}