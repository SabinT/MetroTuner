/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner;

import sabint.metrotuner.interfaces.ITuner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 *
 * @author Rae
 */
public class SimpleTuner implements ITuner {

    Sequencer sequencer;
    Sequence[] sequences = new Sequence[6];
    int notes[] = {64, 59, 55, 50, 45, 40};
    final int PROGRAM = 192;
    final int INSTURMENT = 1;
    final int NOTEON = 144;
    final int NOTEOFF = 128;
    int velocity = 100;
    final int e = 0;
    final int B = 1;
    final int G = 2;
    final int D = 3;
    final int A = 4;
    final int E = 5;

    SimpleTuner() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            createSequences();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SimpleTuner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void playe() {
        playString(e);
    }

    @Override
    public void playB() {
        playString(B);
    }

    @Override
    public void playG() {
        playString(G);
    }

    @Override
    public void playD() {
        playString(D);
    }

    @Override
    public void playA() {
        playString(A);
    }

    @Override
    public void playE() {
        playString(E);
    }

    @Override
    public void stop() {
        sequencer.stop();
    }

    private void playString(int index) {
        try {
            sequencer.setSequence(sequences[index]);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setLoopStartPoint(0);
            sequencer.setLoopEndPoint(16);

            sequencer.setTickPosition(0);
            sequencer.start();
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SimpleTuner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createSequences() {
        for (int i = 0; i < 6; i++) {
            sequences[i] = createSequence(notes[i]);
        }
    }

    private Sequence createSequence(int note) {
        Sequence sequence = null;
        try {
            sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            createEvent(track, PROGRAM, 1, INSTURMENT, 0);
            createEvent(track, NOTEON, 1, note, 0);
            createEvent(track, NOTEOFF, 1, note, 15);
            createEvent(track, PROGRAM, 1, INSTURMENT, 16);

        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SimpleTuner.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sequence;
    }

    private void createEvent(Track track, int type, int chan, int num, long tick) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(type, chan, num, velocity);
            MidiEvent event = new MidiEvent(message, tick);
            track.add(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
