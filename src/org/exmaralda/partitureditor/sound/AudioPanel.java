/*
 * AudioPanel.java
 *
 * Created on 13. August 2004, 16:09
 */

package org.exmaralda.partitureditor.sound;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import org.exmaralda.partitureditor.praatPanel.PraatPanelListener;
import org.exmaralda.partitureditor.praatPanel.PraatPanelEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import org.exmaralda.folker.timeview.TimeSelectionEvent;
import org.exmaralda.folker.utilities.TimeStringFormatter;

/**
 *
 * @author  thomas
 */
public class AudioPanel extends javax.swing.JDialog implements PlayableListener {
    
    javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
    javax.swing.Timer timer;
    
    private Playable player;
    double totalLength = 0;
    
    double internalStartTime;
    double internalEndTime;
    
    boolean isPlaying = false;
    boolean isPausing = false;
    
    public String soundFileName;

    public String lastSnapshotFilename = System.getProperty("user.home") + System.getProperty("file.separator") + "snapshot1.png";
    public String lastAudioPartFilename = System.getProperty("user.home") + System.getProperty("file.separator") + "audiosnippet1.wav";
    public boolean snapshotWouldBeLinkeable = false;

    private boolean videoIsNorth = true;
    
    boolean stopPressed = false;
    
    // the actual dimensions of the video file
    int sourceWidth = -1;
    int sourceHeight = -1;
    
    
    /** Creates new form AudioPanel
     * @param parent
     * @param modal
     * @param player */
    public AudioPanel(java.awt.Frame parent, boolean modal, Playable player) {
        super(parent, modal);
        initComponents();
        
        // 27-05-2015: get rid of it - it is useless and causes trouble
        controlPanel.setVisible(false);
                
        pack();
        
        org.exmaralda.common.helpers.Internationalizer.internationalizeDialogToolTips(this);
        //setPlayer(makePlayer());
        setPlayer(player);
        getPlayer().addPlayableListener(this);       
        registerKeyStrokes();

        minimize(true);
        
        
    }
    
    public void addPlayableListener(PlayableListener l){
        getPlayer().addPlayableListener(l);
    }
    
    public void addPraatPanelListener(PraatPanelListener l) {
         listenerList.add(PraatPanelListener.class, l);
    }

    public void setStartTime(double t){
        //System.out.println("Setting start time");
        internalStartTime = t;
        /*if ((!isPlaying) && (syncStartCheckBox.isSelected())){
            //System.out.println("Updating start time slider");
            updateStartTimeSlider();
        }*/
    }
    
    private void updateStartTimeSlider(){
        int sliderValue = (int)Math.round(internalStartTime/totalLength * 1000000);
        /*startTimeSlider.setValue(sliderValue);        */
    }
    
    public void setEndTime(double t){
        internalEndTime = t;
        /*if ((!isPlaying) && (syncEndCheckBox.isSelected())){
            updateEndTimeSlider();
        }*/
    }
    
    private void updateEndTimeSlider(){
        int sliderValue = (int)Math.round(internalEndTime/totalLength * 1000000);
        //endTimeSlider.setValue(sliderValue);        
    }


    public boolean setSoundFile(String path) {
        return openSoundFile(path);    
    }

    public void setAvailableSoundFiles(java.util.Vector<String> paths){
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        if ((paths.isEmpty()) || (paths.elementAt(0).trim().length()==0)){
            dcbm.addElement("--- no media files available ---");
            availableFilesComboBox.setModel(dcbm);
            availableFilesComboBox.setEnabled(false);
            return;
        }

        for (String path : paths){
            dcbm.addElement(new File(path).getName());
        }
        availableFilesComboBox.setModel(dcbm);
        availableFilesComboBox.setEnabled(true);

    }
    
    public boolean reset(){
        //if (player instanceof AbstractPlayer) ((AbstractPlayer)player).reset();
        soundFileName = null;
        //soundFileNameLabel.setText("--- no file loaded ---");
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("--- no media files available ---");
        availableFilesComboBox.setModel(dcbm);
        availableFilesComboBox.setEnabled(false);

        playButton.setEnabled(false);
        pauseToggleButton.setEnabled(false);
        stopButton.setEnabled(false);
        totalLength = 0.0;
        totalLengthLabel.setText("0.0");
        /*startTimeSlider.setValue(0);
        startTimeSlider.setEnabled(false);
        endTimeSlider.setValue(1000000);*/
        endPositionLabel.setText("0.0");
        setEndTime(0);            

        /*endTimeSlider.setEnabled(false);
        positionSlider.setValue(0);
        positionSlider.setEnabled(false);*/

        grabButton.setEnabled(false);
        cutButton.setEnabled(false);

        status("No audio/video file referenced");        

        videoDisplayPanel.removeAll();
        pack();
        return true;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filesPanel = new javax.swing.JPanel();
        availableFilesComboBox = new javax.swing.JComboBox();
        controlsPanel = new javax.swing.JPanel();
        currentPositionPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        zeroPositionLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        startPositionLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        currentPositionLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        endPositionLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        totalLengthLabel = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        bufferTimeSpinner = new javax.swing.JSpinner();
        loopCheckBox = new javax.swing.JCheckBox();
        playButton = new javax.swing.JButton();
        pauseToggleButton = new javax.swing.JToggleButton();
        stopButton = new javax.swing.JButton();
        minimizeCheckBox = new javax.swing.JCheckBox();
        previousTLIButton = new javax.swing.JButton();
        nextTLIButton = new javax.swing.JButton();
        playbackModeToggleButton = new javax.swing.JToggleButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        videoPanel = new javax.swing.JPanel();
        videoDisplayPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        grabButton = new javax.swing.JButton();
        cutButton = new javax.swing.JButton();
        mediaInfoButton = new javax.swing.JButton();

        setTitle("Audio/Video panel");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                formComponentRemoved(evt);
            }
        });

        filesPanel.setLayout(new javax.swing.BoxLayout(filesPanel, javax.swing.BoxLayout.LINE_AXIS));

        availableFilesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- no media files available ---", " " }));
        availableFilesComboBox.setEnabled(false);
        availableFilesComboBox.setMaximumSize(new java.awt.Dimension(1000, 20));
        availableFilesComboBox.setPreferredSize(new java.awt.Dimension(200, 20));
        availableFilesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availableFilesComboBoxActionPerformed(evt);
            }
        });
        filesPanel.add(availableFilesComboBox);

        getContentPane().add(filesPanel, java.awt.BorderLayout.NORTH);

        controlsPanel.setLayout(new javax.swing.BoxLayout(controlsPanel, javax.swing.BoxLayout.Y_AXIS));

        currentPositionPanel.setEnabled(false);
        currentPositionPanel.setLayout(new java.awt.GridLayout(1, 5));

        zeroPositionLabel.setForeground(new java.awt.Color(102, 102, 102));
        zeroPositionLabel.setText("0.0");
        jPanel4.add(zeroPositionLabel);

        currentPositionPanel.add(jPanel4);

        jPanel1.setToolTipText("Start time");

        startPositionLabel.setForeground(new java.awt.Color(0, 204, 51));
        startPositionLabel.setText("0.0");
        startPositionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startPositionLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startPositionLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                startPositionLabelMouseReleased(evt);
            }
        });
        startPositionLabel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                startPositionLabelMouseWheelMoved(evt);
            }
        });
        jPanel1.add(startPositionLabel);

        currentPositionPanel.add(jPanel1);

        currentPositionLabel.setText("0.0");
        currentPositionLabel.setToolTipText("Current time");
        currentPositionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentPositionLabelMouseClicked(evt);
            }
        });
        jPanel2.add(currentPositionLabel);

        currentPositionPanel.add(jPanel2);

        endPositionLabel.setForeground(new java.awt.Color(255, 0, 51));
        endPositionLabel.setText("0.0");
        endPositionLabel.setToolTipText("Stop time");
        endPositionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endPositionLabelMouseClicked(evt);
            }
        });
        endPositionLabel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                endPositionLabelMouseWheelMoved(evt);
            }
        });
        jPanel3.add(endPositionLabel);

        currentPositionPanel.add(jPanel3);

        totalLengthLabel.setForeground(new java.awt.Color(102, 102, 102));
        totalLengthLabel.setText("0.0");
        totalLengthLabel.setToolTipText("Sound file length");
        jPanel5.add(totalLengthLabel);

        currentPositionPanel.add(jPanel5);

        controlsPanel.add(currentPositionPanel);

        bufferTimeSpinner.setToolTipText("Buffer time (in seconds)");
        bufferTimeSpinner.setPreferredSize(new java.awt.Dimension(40, 18));
        controlPanel.add(bufferTimeSpinner);

        loopCheckBox.setText("Loop");
        loopCheckBox.setToolTipText("Loop selection");
        loopCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        loopCheckBox.setEnabled(false);
        loopCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        controlPanel.add(loopCheckBox);

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/Start.gif"))); // NOI18N
        playButton.setToolTipText("Play (F1)");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        controlPanel.add(playButton);

        pauseToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/Pause.gif"))); // NOI18N
        pauseToggleButton.setToolTipText("Pause (F2)");
        pauseToggleButton.setEnabled(false);
        pauseToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseToggleButtonActionPerformed(evt);
            }
        });
        controlPanel.add(pauseToggleButton);

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/Stop.gif"))); // NOI18N
        stopButton.setToolTipText("Stop (F3)");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        controlPanel.add(stopButton);

        minimizeCheckBox.setText("Minimize");
        minimizeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeCheckBoxActionPerformed(evt);
            }
        });
        controlPanel.add(minimizeCheckBox);

        previousTLIButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/PreviousTLI.gif"))); // NOI18N
        previousTLIButton.setToolTipText("Select previous timeline item (F11)");
        previousTLIButton.setMaximumSize(new java.awt.Dimension(28, 24));
        previousTLIButton.setMinimumSize(new java.awt.Dimension(28, 24));
        previousTLIButton.setPreferredSize(new java.awt.Dimension(28, 24));
        previousTLIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousTLIButtonActionPerformed(evt);
            }
        });
        controlPanel.add(previousTLIButton);

        nextTLIButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/NextTLI.gif"))); // NOI18N
        nextTLIButton.setToolTipText("Select next timeline item (F12)");
        nextTLIButton.setMaximumSize(new java.awt.Dimension(28, 24));
        nextTLIButton.setMinimumSize(new java.awt.Dimension(28, 24));
        nextTLIButton.setPreferredSize(new java.awt.Dimension(28, 24));
        nextTLIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTLIButtonActionPerformed(evt);
            }
        });
        controlPanel.add(nextTLIButton);

        playbackModeToggleButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playbackModeToggleButton.setText("P");
        playbackModeToggleButton.setEnabled(false);
        playbackModeToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playbackModeToggleButtonActionPerformed(evt);
            }
        });
        controlPanel.add(playbackModeToggleButton);

        controlsPanel.add(controlPanel);

        statusPanel.setLayout(new javax.swing.BoxLayout(statusPanel, javax.swing.BoxLayout.LINE_AXIS));

        statusLabel.setText("Status");
        statusPanel.add(statusLabel);

        controlsPanel.add(statusPanel);

        getContentPane().add(controlsPanel, java.awt.BorderLayout.SOUTH);

        videoPanel.setVisible(false);
        videoPanel.setLayout(new java.awt.BorderLayout());
        videoPanel.add(videoDisplayPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(videoPanel, java.awt.BorderLayout.CENTER);

        buttonsPanel.setMinimumSize(new java.awt.Dimension(20, 20));
        buttonsPanel.setLayout(new javax.swing.BoxLayout(buttonsPanel, javax.swing.BoxLayout.Y_AXIS));

        grabButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/Grab.gif"))); // NOI18N
        grabButton.setToolTipText("Save/Link current video image");
        grabButton.setEnabled(false);
        grabButton.setMaximumSize(new java.awt.Dimension(35, 35));
        grabButton.setMinimumSize(new java.awt.Dimension(35, 35));
        grabButton.setPreferredSize(new java.awt.Dimension(35, 35));
        grabButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(grabButton);

        cutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/partitureditor/sound/Cut.gif"))); // NOI18N
        cutButton.setToolTipText("Save/Link audio snippet");
        cutButton.setEnabled(false);
        cutButton.setMaximumSize(new java.awt.Dimension(35, 35));
        cutButton.setMinimumSize(new java.awt.Dimension(35, 35));
        cutButton.setPreferredSize(new java.awt.Dimension(35, 35));
        cutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cutButton);

        mediaInfoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/exmaralda/folker/tangoicons/tango-icon-theme-0.8.1/32x32/apps/help-browser.png"))); // NOI18N
        mediaInfoButton.setToolTipText("Get media info");
        mediaInfoButton.setEnabled(false);
        mediaInfoButton.setMaximumSize(new java.awt.Dimension(35, 35));
        mediaInfoButton.setMinimumSize(new java.awt.Dimension(35, 35));
        mediaInfoButton.setPreferredSize(new java.awt.Dimension(35, 35));
        mediaInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaInfoButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(mediaInfoButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endPositionLabelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_endPositionLabelMouseWheelMoved
        int units = evt.getWheelRotation();
        if (units>0){
            decreaseStopTime(0.1);
        } else if (units<0){
            increaseStopTime(0.1);
        } else {
            return;
        }                

    }//GEN-LAST:event_endPositionLabelMouseWheelMoved

    private void startPositionLabelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_startPositionLabelMouseWheelMoved
        int units = evt.getWheelRotation();
        if (units>0){
            decreaseStartTime(0.1);
        } else if (units<0){
            increaseStartTime(0.1);
        } else {
            return;
        }                
    }//GEN-LAST:event_startPositionLabelMouseWheelMoved

    private void startPositionLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startPositionLabelMouseReleased
        // DO NOTHING
    }//GEN-LAST:event_startPositionLabelMouseReleased

    private void startPositionLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startPositionLabelMousePressed
        // DO NOTHING
    }//GEN-LAST:event_startPositionLabelMousePressed

    private void cutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutButtonActionPerformed
            /*final double startTime = (totalLength*startTimeSlider.getValue())/1000000.0;
            final double endTime = (totalLength*endTimeSlider.getValue())/1000000.0;
            SaveAudioPartDialog dialog = new SaveAudioPartDialog(null, true, lastAudioPartFilename, snapshotWouldBeLinkeable);
            String info = "[Cut audio from "
                    + TimeStringFormatter.formatMiliseconds(startTime*1000.0, 2) + " to "
                    + TimeStringFormatter.formatMiliseconds(endTime*1000.0, 2) + "]";
            dialog.setExtraInfo(info);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            if (dialog.change) {
                if ((endTime - startTime)>30.0){
                    String message = "Going to save audio snippet in background.\nThis may take a while.";
                    JOptionPane.showMessageDialog(this, message);
                }
                final String filename = dialog.getFilename();
                final boolean sendLink = dialog.sendLink();
                final AudioPanel myself = this;
                Thread backgroundThread = new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            myself.status("Saving audio snippet as " + filename + "...");
                            AudioProcessor ap = new AudioProcessor();
                            ap.writePart(startTime, endTime, soundFileName, filename);
                            myself.status("Saved audio snippet as " + filename + ".");
                            lastAudioPartFilename = filename;
                            if (snapshotWouldBeLinkeable && sendLink) {
                                fireLinkAudioSnippet();
                            }
                            String message = "Audio snippet saved successfully.";
                            JOptionPane.showMessageDialog(myself, message);
                        } catch (IOException ioe) {
                            String message = "Could not save audio snippet: " + ioe.getLocalizedMessage();
                            javax.swing.JOptionPane.showMessageDialog(myself, message);
                        }
                        
                    }                    
                });
                
                //SwingUtilities.invokeLater(backgroundThread);
                backgroundThread.start();
            }*/
        
    }//GEN-LAST:event_cutButtonActionPerformed

    private void grabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabButtonActionPerformed
        // TODO add your handling code here:        
        java.awt.Image img = null;
        if (getPlayer() instanceof JMFPlayer){
            img = ((JMFPlayer)getPlayer()).grabFrame();
        } else if (getPlayer() instanceof ELANDSPlayer){
            img = ((ELANDSPlayer)getPlayer()).grabFrame();        
        } else if (getPlayer() instanceof ELANQTPlayer){
            img = ((ELANQTPlayer)getPlayer()).grabFrame();
        } else if (getPlayer() instanceof JDSPlayer){
            img = ((JDSPlayer)getPlayer()).grabFrame();            
        } else if (getPlayer() instanceof MMFPlayer){
            img = ((MMFPlayer)getPlayer()).grabFrame();            
        } else if (getPlayer() instanceof CocoaQTPlayer){
            img = ((CocoaQTPlayer)getPlayer()).grabFrame();  
        }

        if (img==null) return;
        BufferedImage buffimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);        
        java.awt.Graphics2D g = buffimg.createGraphics();
        g.drawImage(img, null, null);
        try{
            SaveSnapshotDialog dialog = new SaveSnapshotDialog(null, true, lastSnapshotFilename, snapshotWouldBeLinkeable);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            if (dialog.change) {
                String filename = dialog.getFilename();
                javax.imageio.ImageIO.write(buffimg, "png", new java.io.File(filename));
                lastSnapshotFilename = filename;
                if (snapshotWouldBeLinkeable && dialog.sendLink()){
                    fireLinkSnapShot();
                }
            }
        } catch (java.io.IOException ioe){
            String message = "Could not save snapshot: " + ioe.getLocalizedMessage();
            javax.swing.JOptionPane.showMessageDialog(this, message);
        }
    }//GEN-LAST:event_grabButtonActionPerformed

    private void previousTLIButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousTLIButtonActionPerformed
        // TODO add your handling code here:
        fireSelectPreviousTLI();
    }//GEN-LAST:event_previousTLIButtonActionPerformed

    private void nextTLIButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTLIButtonActionPerformed
        // TODO add your handling code here:
        fireSelectNextTLI();
    }//GEN-LAST:event_nextTLIButtonActionPerformed

    private void currentPositionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentPositionLabelMouseClicked
    }//GEN-LAST:event_currentPositionLabelMouseClicked

    private void endPositionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endPositionLabelMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            decreaseStopTime(0.1);
        } else if (SwingUtilities.isRightMouseButton(evt)){
            increaseStopTime(0.1);
        } else {
            return;
        }
    }//GEN-LAST:event_endPositionLabelMouseClicked

    private void startPositionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startPositionLabelMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            decreaseStartTime(0.1);
        } else if (SwingUtilities.isRightMouseButton(evt)){
            increaseStartTime(0.1);
        } else {
            return;
        }
    }//GEN-LAST:event_startPositionLabelMouseClicked

    public void minimize(boolean mini){
        //sliderPanel.setVisible(!mini);
        //mainPanel.setVisible(!mini);
        if (minimizeCheckBox.isSelected()!=mini){
            minimizeCheckBox.setSelected(mini);
        }
        pack();
    }
    
    private void minimizeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeCheckBoxActionPerformed
        minimize(minimizeCheckBox.isSelected());
        //mainPanel.setVisible(!minimizeCheckBox.isSelected());
        //pack();
    }//GEN-LAST:event_minimizeCheckBoxActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        stopPressed = true;
        doStop();
    }//GEN-LAST:event_stopButtonActionPerformed

    public void doStop(){
        System.out.println("JMF-Player: doStop");
        getPlayer().stopPlayback();
        isPlaying = false;
        isPausing = false;        
    }
    
    private void pauseToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseToggleButtonActionPerformed
        doPause();
    }//GEN-LAST:event_pauseToggleButtonActionPerformed

    public void doPause(){
        if (pauseToggleButton.isSelected()){
            // Pause
            getPlayer().haltPlayback();
            pauseToggleButton.setToolTipText("Resume");
            isPausing = true;
            status("Playback halted");
            //enablePositionToSomethingButtons();
        } else {
            // Resume
            getPlayer().resumePlayback();
            pauseToggleButton.setToolTipText("Pause");
            isPausing = false;
            status("Playback started");
            //disablePositionToSomethingButtons();
        }                
    }
    
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        //doPlay();
    }//GEN-LAST:event_playButtonActionPerformed

    public void doPlay(){
        /*double bufferTime = ((Double)(bufferTimeSpinner.getValue()));
        getPlayer().setBufferTime(bufferTime);
        double startTime = (totalLength*startTimeSlider.getValue())/1000000.0;
        getPlayer().setStartTime(startTime);
        double endTime = (totalLength*endTimeSlider.getValue())/1000000.0;
        getPlayer().setEndTime(endTime);
        isPlaying = true;
        isPausing = false;
        getPlayer().startPlayback();                */
    }
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void playbackModeToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playbackModeToggleButtonActionPerformed
        if (playbackModeToggleButton.isSelected()){
            playbackModeToggleButton.setForeground(Color.RED);
        } else {
            playbackModeToggleButton.setForeground(Color.BLACK);            
        }
        firePlaybackModeToggled();
}//GEN-LAST:event_playbackModeToggleButtonActionPerformed

    private void availableFilesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availableFilesComboBoxActionPerformed
        fireFileSelectionChanged(availableFilesComboBox.getSelectedIndex());
    }//GEN-LAST:event_availableFilesComboBoxActionPerformed

    private void formComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentRemoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // new 28-01-2016
        if (player.getVisibleComponent()==null) return;
        int videoDisplayPanelWidth = videoDisplayPanel.getWidth();
        int videoDisplayPanelHeight = videoDisplayPanel.getHeight();
        Dimension dimensionByWidth = calculateDimensionByWidth(sourceWidth, sourceHeight, videoDisplayPanelWidth);
        Dimension dimensionByHeight = calculateDimensionByHeight(sourceWidth, sourceHeight, videoDisplayPanelHeight);
        // use the smaller of the two because it is guaranteed to fit
        if (dimensionByWidth.width < dimensionByHeight.width){
           player.getVisibleComponent().setPreferredSize(dimensionByWidth);
        } else {
           player.getVisibleComponent().setPreferredSize(dimensionByHeight);            
        }
        
        
    }//GEN-LAST:event_formComponentResized

    private void mediaInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaInfoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mediaInfoButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //ELANDSPlayer player = new ELANDSPlayer();
        JMFPlayer player = new JMFPlayer();
        //VLCPlayer player = new VLCPlayer();
        try {
            player.setSoundFile(null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AudioPanel ap = new AudioPanel(new javax.swing.JFrame(), true, player);
        ap.setSoundFile(null);
        ap.show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox availableFilesComboBox;
    private javax.swing.JSpinner bufferTimeSpinner;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JLabel currentPositionLabel;
    private javax.swing.JPanel currentPositionPanel;
    private javax.swing.JButton cutButton;
    private javax.swing.JLabel endPositionLabel;
    private javax.swing.JPanel filesPanel;
    private javax.swing.JButton grabButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JCheckBox loopCheckBox;
    private javax.swing.JButton mediaInfoButton;
    private javax.swing.JCheckBox minimizeCheckBox;
    private javax.swing.JButton nextTLIButton;
    private javax.swing.JToggleButton pauseToggleButton;
    private javax.swing.JButton playButton;
    private javax.swing.JToggleButton playbackModeToggleButton;
    private javax.swing.JButton previousTLIButton;
    private javax.swing.JLabel startPositionLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel totalLengthLabel;
    private javax.swing.JPanel videoDisplayPanel;
    private javax.swing.JPanel videoPanel;
    private javax.swing.JLabel zeroPositionLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processPlayableEvent(PlayableEvent e) {
        int type = e.getType();
        if (type==PlayableEvent.POSITION_UPDATE){
                double pos = e.getPosition();
                currentPositionLabel.setText(TimeStringFormatter.formatMiliseconds(pos*1000.0,1));
        }
        
        /*switch (type){
            case PlayableEvent.PLAYBACK_STARTED :                 
                status("Playback started.");
                playButton.setEnabled(false);
                pauseToggleButton.setEnabled(true);
                stopButton.setEnabled(true);
                startTimeSlider.setEnabled(false);
                endTimeSlider.setEnabled(false);
                positionSlider.setEnabled(true);
                isPlaying = true;
                isPausing = true;

                break;
            case PlayableEvent.PLAYBACK_STOPPED : 
                status("Playback stopped.");
                if (syncStartCheckBox.isSelected()){
                    updateStartTimeSlider();
                }
                if (syncEndCheckBox.isSelected()){
                    updateEndTimeSlider();
                }
                positionSlider.setValue(startTimeSlider.getValue());
                playButton.setEnabled(true);
                pauseToggleButton.setEnabled(false);
                pauseToggleButton.setSelected(false);
                pauseToggleButton.setToolTipText("Pause");
                stopButton.setEnabled(false);
                startTimeSlider.setEnabled(true);
                endTimeSlider.setEnabled(true);
                positionSlider.setEnabled(false);
                positionSlider.setValue(startTimeSlider.getValue());
                currentPositionLabel.setText(startPositionLabel.getText());

                isPlaying = false;
                isPausing = false;
                //disablePositionToSomethingButtons();
                if ((!stopPressed) && (loopCheckBox.isSelected())){
                    this.doPlay();
                }
                stopPressed = false;
                break;
            case PlayableEvent.POSITION_UPDATE :
                double pos = e.getPosition();
                //int position = (int)(Math.round(pos));
                positionSlider.setValue((int)Math.round(pos/totalLength*1000000));
                //currentPositionLabel.setText(Double.toString(Math.round(pos*10)/10.0));
                currentPositionLabel.setText(TimeStringFormatter.formatMiliseconds(pos*1000.0,1));
                break;
            default: break;
        } */
    }    
    
    private void status (String text){
        //System.out.println(text);
        statusLabel.setText(org.exmaralda.common.helpers.Internationalizer.getString(text));
    }

    public boolean isVideo(){
        return (player.getVisibleComponent()!=null);
    }
    
    private boolean openSoundFile(String filename){

        soundFileName = filename;
        totalLength = getPlayer().getTotalLength();
        totalLengthLabel.setText(TimeStringFormatter.formatMiliseconds(totalLength*1000.0,1));
        endPositionLabel.setText(TimeStringFormatter.formatMiliseconds(totalLength*1000.0,1));
        
        status("Audio/Video file opened successfully.");
      
        videoDisplayPanel.removeAll();
        
        // restructured 28-01-2016
        Component c = getPlayer().getVisibleComponent();
        if (c!=null){
            
            // 1. determine the actual size of the video
            // this may work differently in different players
            if (getPlayer() instanceof JDSPlayer){
                JDSPlayer jdsp = (JDSPlayer)getPlayer();
                sourceWidth = jdsp.wrappedPlayer.getSourceWidth();
                sourceHeight = jdsp.wrappedPlayer.getSourceHeight();           
            } else if (getPlayer() instanceof CocoaQTPlayer) {
                 CocoaQTPlayer cqtp = (CocoaQTPlayer)getPlayer();
                 sourceWidth = cqtp.wrappedPlayer.getSourceWidth();
                 sourceHeight = cqtp.wrappedPlayer.getSourceHeight();                         
            } else {
                // this includes JMF and the other, mor shitty like, players
                videoDisplayPanel.add(c);
                videoPanel.setVisible(true);
                sourceWidth = c.getPreferredSize().width;
                sourceHeight = c.getPreferredSize().height;                
            }
            
            
            // 2. set the initial size to a maximum of 480 width
            Dimension initialDimension = calculateInitialDimension(sourceWidth, sourceHeight);    
            
            // 3. now do the rest
            c.setPreferredSize(initialDimension);
            if (getPlayer() instanceof JMFPlayer){
                JMFPlayer jmfp = (JMFPlayer)getPlayer();                
                grabButton.setEnabled(jmfp.fgc!=null);
                cutButton.setEnabled(AudioProcessor.isCuttable(filename));                
            } else if (getPlayer() instanceof JDSPlayer || getPlayer() instanceof CocoaQTPlayer){
                videoDisplayPanel.add(c);
                videoDisplayPanel.setPreferredSize(c.getPreferredSize());
                grabButton.setEnabled(true);
                cutButton.setEnabled(AudioProcessor.isCuttable(filename));                 
                videoPanel.setVisible(true);                
            } else {
                // all the other crap players...
            }

            
        } else {
            videoPanel.setVisible(false);
        }


        pack();

        return true;

    }
    
    void decreaseStartTime(double amount){
        if (isPlaying) return;
        //double time = Double.parseDouble(startPositionLabel.getText());
        double time = internalStartTime;
        double newTime = Math.max(0, time - amount);
        setStartTime(newTime);
        updateStartTimeSlider();                
    }

    void increaseStartTime(double amount){
        if (isPlaying) return;
        //double time = Double.parseDouble(startPositionLabel.getText());
        double time = internalStartTime;
        double newTime = Math.min(totalLength, time + amount);       
        setStartTime(newTime);
        updateStartTimeSlider();                
    }

    void decreaseStopTime(double amount){
        if (isPlaying) return;
        //double time = Double.parseDouble(endPositionLabel.getText());
        double time = internalEndTime;
        double newTime = Math.max(0, time - amount);        
        setEndTime(newTime);
        updateEndTimeSlider();                
    }
    
    void increaseStopTime(double amount){
        if (isPlaying) return;
        //double time = Double.parseDouble(endPositionLabel.getText());
        double time = internalEndTime;
        double newTime = Math.min(totalLength, time + amount);       
        setEndTime(newTime);
        updateEndTimeSlider();                
    }
    
    /*private void disablePositionToSomethingButtons(){
        positionToStartButton.setEnabled(false);
        positionToStartButton.setBackground(new java.awt.Color(204,204,204));
        positionToStopButton.setEnabled(false);
        positionToStopButton.setBackground(new java.awt.Color(204,204,204));
    }*/

    /*private void enablePositionToSomethingButtons(){
        positionToStartButton.setEnabled(true);
        positionToStartButton.setBackground(new java.awt.Color(0,204,51));
        positionToStopButton.setEnabled(true);
        positionToStopButton.setBackground(new java.awt.Color(255,0,51));
    }*/
    
    /*public void enableGetButtons(boolean enabled){
        sendStartTimeButton.setEnabled(enabled);
        sendStopTimeButton.setEnabled(enabled);
        sendPositionTimeButton.setEnabled(enabled);
    }*/
    
    protected void fireEvent(PraatPanelEvent event){
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
             if (listeners[i]==PraatPanelListener.class) {
                ((PraatPanelListener)listeners[i+1]).processTime(event);             
            }
         }
    }

    protected void fireFileSelectionChanged(int indexOfFile){
        fireEvent(new PraatPanelEvent(PraatPanelEvent.SOUND_FILE_SELECTION_CHANGED, indexOfFile));         
    }

    protected void fireCursorTime(double time) {
        fireEvent(new PraatPanelEvent(time));
    }
      
    protected void fireSelectNextTLI(){
        fireEvent(new PraatPanelEvent(PraatPanelEvent.SELECT_NEXT_TLI)); 
    }
    
    protected void fireSelectPreviousTLI(){
        fireEvent(new PraatPanelEvent(PraatPanelEvent.SELECT_PREVIOUS_TLI)); 
    }
    
    protected void fireLinkSnapShot(){
        fireEvent(new PraatPanelEvent(PraatPanelEvent.LINK_SNAPSHOT));         
    }

    protected void fireLinkAudioSnippet(){
        fireEvent(new PraatPanelEvent(PraatPanelEvent.LINK_AUDIO_SNIPPET));         
    }

    protected void firePlaybackModeToggled(){
        if (playbackModeToggleButton.isSelected()){
            fireEvent(new PraatPanelEvent(PraatPanelEvent.PLAYBACK_MODE_ON));
        } else {
            fireEvent(new PraatPanelEvent(PraatPanelEvent.PLAYBACK_MODE_OFF));
        }
    }


    void registerKeyStrokes(){
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),"F1_Pressed");
        am.put("F1_Pressed", playAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),"F2_Pressed");
        am.put("F2_Pressed", pauseAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),"F3_Pressed");
        am.put("F3_Pressed", stopAction);
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0),"F5_Pressed");
        am.put("F5_Pressed", sendStartTimeAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0),"F6_Pressed");
        am.put("F6_Pressed", sendPositionTimeAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0),"F7_Pressed");
        am.put("F7_Pressed", sendStopTimeAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0),"F11_Pressed");
        am.put("F11_Pressed", previousTLIAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0),"F12_Pressed");
        am.put("F12_Pressed", nextTLIAction);
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"LeftArrow_Pressed");
        am.put("LeftArrow_Pressed", decreaseStartTimeAction);
    
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"RightArrow_Pressed");
        am.put("RightArrow_Pressed", increaseStartTimeAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 1),"Shift_LeftArrow_Pressed");
        am.put("Shift_LeftArrow_Pressed", decreaseStopTimeAction);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 1),"Shift_RightArrow_Pressed");
        am.put("Shift_RightArrow_Pressed", increaseStopTimeAction);

    }
    
    public Action playAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {playButton.doClick(); } };
    
    public Action pauseAction = 
        new AbstractAction(){ public void actionPerformed(ActionEvent e) {pauseToggleButton.doClick(); } };
    
    public Action stopAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {stopButton.doClick(); } };
    
    public Action sendStartTimeAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {/*sendStartTimeButton.doClick(); */} };
    
    public Action sendPositionTimeAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {/*sendPositionTimeButton.doClick(); */ }};
    
    public Action sendStopTimeAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {/*sendStopTimeButton.doClick(); */} };
    
    public Action previousTLIAction = 
        new AbstractAction() { public void actionPerformed(ActionEvent e) {previousTLIButton.doClick(); } };
    
    public Action nextTLIAction = 
        new AbstractAction() {public void actionPerformed(ActionEvent e) {nextTLIButton.doClick(); } };
    
    public Action decreaseStartTimeAction = 
        new AbstractAction() {public void actionPerformed(ActionEvent e) {decreaseStartTime(0.1); } };

    public Action increaseStartTimeAction = 
        new AbstractAction() {public void actionPerformed(ActionEvent e) {increaseStartTime(0.1); } };

    public Action decreaseStopTimeAction = 
        new AbstractAction() {public void actionPerformed(ActionEvent e) {decreaseStopTime(0.1); } };

    public Action increaseStopTimeAction = 
        new AbstractAction() {public void actionPerformed(ActionEvent e) {increaseStopTime(0.1); } };
        
    @Override
    public void show(){
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = this.getPreferredSize();
        this.setLocation(Math.round((screenSize.width - dialogSize.width)/2), screenSize.height - dialogSize.height - 30);
        super.show();
    }
    
    
    /*public void activateTimelineMode(){
        this.syncStartCheckBox.setSelected(true);
        this.syncEndCheckBox.setSelected(true);
    }*/
    
    
    public Playable getPlayer() {
        return player;
    }

    public void setPlayer(Playable player) {
        this.player = player;
        if (player instanceof JMFPlayer){
            setTitle(getTitle() + " [JMF]");
        } else if (player instanceof QuicktimePlayer){
            setTitle(getTitle() + " [Quicktime]");
        } else if (player instanceof ELANDSPlayer){
            setTitle(getTitle() + " [DirectShow]");
        } else if (player instanceof JDSPlayer){
            setTitle(getTitle() + " [JDS]");
        } else if (player instanceof MMFPlayer){
            setTitle(getTitle() + " [MMF]");
        } else if (player instanceof ELANQTPlayer){
            setTitle(getTitle() + " [ELAN-QT]");
        } else if (player instanceof BASAudioPlayer){
            setTitle(getTitle() + " [BAS-Audio]");
        } else if (player instanceof CocoaQTPlayer){
            setTitle(getTitle() + " [CocoaQT]");
        } /*else if (player instanceof VLCPlayer){
            setTitle(getTitle() + " [VLCPlayer]");
        }*/
    }

    int MAXIMAL_INITIAL_WIDTH = 480;
    
    private Dimension calculateInitialDimension(int sourceWidth, int sourceHeight) {
        if (sourceWidth<=MAXIMAL_INITIAL_WIDTH){
            // don't make it bigger than necessary
            return new java.awt.Dimension(sourceWidth, sourceHeight);                    
        }
        return calculateDimensionByWidth(sourceWidth, sourceHeight, MAXIMAL_INITIAL_WIDTH);
    }
    
    private Dimension calculateDimensionByWidth(int sourceWidth, int sourceHeight, int actualWidth) {
        double ratio = (double)((double)actualWidth/(double)sourceWidth);
        int calculatedHeight = (int) Math.round(ratio * sourceHeight);
        Dimension result = new Dimension(actualWidth, calculatedHeight);
        return result;
    }
    
    private Dimension calculateDimensionByHeight(int sourceWidth, int sourceHeight, int actualHeight) {
        double ratio = (double)((double)actualHeight/(double)sourceHeight);
        int calculatedWidth = (int) Math.round(ratio * sourceWidth);
        Dimension result = new Dimension(calculatedWidth, actualHeight);
        return result;
    }

    public void setTimeSelection(TimeSelectionEvent event) {
        startPositionLabel.setText(TimeStringFormatter.formatMiliseconds(event.getStartTime(), 1));
        endPositionLabel.setText(TimeStringFormatter.formatMiliseconds(event.getEndTime(), 1));
    }

    
        
}
