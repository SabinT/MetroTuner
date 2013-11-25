/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner;

import sabint.metrotuner.interfaces.ITuner;
import sabint.metrotuner.interfaces.IBPMObserver;
import sabint.metrotuner.interfaces.IMetronome;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;

/**
 *
 * @author Rae
 */
public class MTunerPanel extends ImagePanel implements IBPMObserver {

    IMetronome metronome;
    ITuner tuner;
    
    ImageButton start;
    ImageButton stop;
    ImageButton increase;
    ImageButton decrease;
    ImageButton string_E;
    ImageButton string_A;
    ImageButton string_D;
    ImageButton string_G;
    ImageButton string_B;
    ImageButton string_lowerE;
    ImageButton mute;
    BigFatLabel tempo;

    MTunerPanel(String string) {
        super(string);

        initComponents();
        bindEvents();
    }

    private void initComponents() {
        metronome = new SimpleMetronome();
        metronome.addBPMObserver(this);
        
        tuner = new SimpleTuner();
        
        start = new ImageButton("start", 0, 54);
        stop = new ImageButton("stop", 0, 162);
        increase = new ImageButton("increase", 90, 36);
        decrease = new ImageButton("decrease", 90, 207);

        string_E = new ImageButton("EString", 198, 54);
        string_A = new ImageButton("AString", 198, 90);
        string_D = new ImageButton("DString", 198, 126);
        string_G = new ImageButton("GString", 198, 162);
        string_B = new ImageButton("BString", 198, 198);
        string_lowerE = new ImageButton("lowerEString", 198, 233);

        mute = new ImageButton("mute", 306, 0);

        tempo = new BigFatLabel("120", -40);
        //tempo = new BigFatLabel(Integer.toString(metronome.getBPM()), -40);
        tempo.setLocation(96, 128);
        tempo.setSize(200, 200);
        tempo.setLeftShadow(1, 1, new Color(0xc7ccd0));
        tempo.setRightShadow(1, 1, new Color(0x2c2e31));
        tempo.setForeground(new Color(0x8c8fd7));
        tempo.setFont(getFont().deriveFont(40f));

        add(start);
        add(stop);
        add(increase);
        add(decrease);

        add(string_E);
        add(string_A);
        add(string_D);
        add(string_G);
        add(string_B);
        add(string_lowerE);

        add(mute);

        add(tempo);
        add(new JLabel("hey"));
    }

    public void startClicked() {
        metronome.start();
    }

    public void stopClicked() {
        metronome.stop();
    }

    public void increaseClicked() {
        metronome.increaseTempo(5);
    }

    public void decreaseClicked() {
        metronome.decreaseTempo(5);
    }

    public void muteClicked() {
        tuner.stop();
    }

    public void EClicked() {
        tuner.playE();
    }

    public void AClicked() {
        tuner.playA();
    }

    public void DClicked() {
        tuner.playD();
    }

    public void GClicked() {
        tuner.playG();
    }

    public void BClicked() {
        tuner.playB();
    }

    public void lowerEClicked() {
        tuner.playe();
    }

    private void bindEvents() {
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startClicked();
            }
        });

        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopClicked();
            }
        });

        increase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                increaseClicked();
            }
        });

        decrease.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseClicked();
            }
        });

        mute.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                muteClicked();
            }
        });

        string_E.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EClicked();
            }
        });

        string_A.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AClicked();
            }
        });

        string_D.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DClicked();
            }
        });

        string_G.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GClicked();
            }
        });

        string_B.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BClicked();
            }
        });

        string_lowerE.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lowerEClicked();
            }
        });

    }

    @Override
    public void BPMChanged(int bpm) {
        NumberFormat format = new DecimalFormat("000");
        tempo.setText(format.format(bpm));
        tempo.repaint();
    }
}
