/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabint.metrotuner;

import javax.swing.JFrame;

/**
 *
 * @author Rae
 */
public class MetroTunerPRO {

    public static void main(String[] args) {
        MTunerPanel panel = new MTunerPanel("images/mtuner_back.jpg");

        JFrame frame = new JFrame("MetroTuner PRO 2011");
        frame.setContentPane(panel);
        //frame.getContentPane().add(new BigFatLabel("hey", 50));

        frame.setSize(500,325);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
