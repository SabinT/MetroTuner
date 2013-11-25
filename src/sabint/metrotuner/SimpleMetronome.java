/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner;

import sabint.metrotuner.interfaces.IBPMObserver;
import sabint.metrotuner.interfaces.IMetronome;
import java.util.ArrayList;
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
public class SimpleMetronome implements IMetronome {

    final int MAX_TEMPO = 300;
    final int MIN_TEMPO = 40;
    Sequencer sequencer = null;
    Sequence sequence = null;
    Track track = null;
    int bpm = 120;
    final int PROGRAM = 192;
    final int NOTEON = 144;
    final int NOTEOFF = 128;
    int velocity = 100;
    boolean playing = false;
    
    ArrayList<IBPMObserver> bpmObservers = new ArrayList<IBPMObserver>();

    SimpleMetronome() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();

            createMusic();

        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SimpleMetronome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SimpleMetronome.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

    }

    private void createMusic() {
        createEvent(PROGRAM, 9, 1, 0);
        for (int i = 0; i < 4; i++) {
            createEvent(NOTEON, 9, 60 + (i % 4 == 0 ? 0 : 2), i * 4);
            createEvent(NOTEOFF, 9, 60 + (i % 4 == 0 ? 0 : 2), i * 4 + 3);
        }
        createEvent(PROGRAM, 9, 1, 16);
    }

    private void createEvent(int type, int chan, int num, long tick) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(type, chan, num, velocity);
            MidiEvent event = new MidiEvent(message, tick);
            track.add(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setLoopStartPoint(0);
            sequencer.setLoopEndPoint(16);

            sequencer.setMicrosecondPosition(0);
            sequencer.setTempoInBPM(bpm);
            sequencer.start();
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SimpleMetronome.class.getName()).log(Level.SEVERE, null, ex);
        }

        playing = true;
    }

    @Override
    public void stop() {
        sequencer.stop();
        playing = false;
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        notifyBPMObservers();
    }

    @Override
    public int getBPM() {
        return (int) bpm;
    }

    @Override
    public void increaseTempo(int amount) {
        if (bpm + amount <= MAX_TEMPO) {
            bpm += amount;
            if (playing) {
                start();
            }
            notifyBPMObservers();
        }
    }

    @Override
    public void decreaseTempo(int amount) {
        if (bpm - amount >= MIN_TEMPO) {
            bpm -= amount;
            if (playing) {
                start();
            }
            notifyBPMObservers();
        }
    }

    @Override
    public void addBPMObserver(IBPMObserver obs) {
        bpmObservers.add(obs);
    }

    @Override
    public void removeBPMObserver(IBPMObserver obs) {
        bpmObservers.remove(obs);
    }

    @Override
    public void notifyBPMObservers() {
        for (IBPMObserver obs : bpmObservers) {
            obs.BPMChanged(bpm);
        }
    }
}
