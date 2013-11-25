/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner.interfaces;

/**
 *
 * @author Rae
 */
public interface ITuner {

    /**
     * Play the lower "e" string, hence the lowercase e
     */
    void playe();

    void playB();

    void playG();

    void playD();

    void playA();

    /**
     * Play the higher (bass) E string
     */
    void playE();

    void stop();
}
