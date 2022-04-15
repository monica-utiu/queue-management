package GUI.view;

import model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class SimulationFrame extends JFrame{
    private JLabel currTimeL;
    private JTextArea currTimeF;

    private JLabel timeL;
    private JLabel timeLMin;
    private JLabel timeLMax;
    private JTextField timeMinF;
    private JTextField timeMaxF;

    private JLabel serviceL;
    private JLabel serviceLMin;
    private JLabel serviceLMax;
    private JTextField serviceMinF;
    private JTextField serviceMaxF;

    private JLabel simulation;
    private JTextField simulationF;

    private JLabel noQ;
    private JTextField noQF;

    private JLabel noC;
    private JTextField noCF;

    private JButton start;

    private JTextArea logF;
    private JLabel logL;
    private JScrollPane logScroll;

    private JTextArea minOutF;
    private JLabel minOutL;
    private JScrollPane minOutScrollP;

    private JLabel q1L;
    private JLabel q2L;
    private JLabel q3L;
    private JLabel q4L;
    private JLabel q5L;

    private JTextArea q1F;
    private JTextArea q2F;
    private JTextArea q3F;
    private JTextArea q4F;
    private JTextArea q5F;

    public SimulationFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 200, 1300, 700);
        this.getContentPane().setLayout(null);

        Font bigFont = new Font("SansSerif", Font.PLAIN, 18);
        Font biggerFont = new Font("SansSerif", Font.PLAIN, 32);

        timeL = new JLabel("Interval arrival times");
        timeL.setFont(bigFont);
        timeL.setBounds(50, 20, 300, 30);
        getContentPane().add(timeL);

        timeLMin = new JLabel("Min: ");
        timeLMin.setFont(bigFont);
        timeLMin.setBounds(250, 20, 50, 30);
        getContentPane().add(timeLMin);

        timeMinF = new JTextField();
        timeMinF.setBounds(300, 20, 30, 30);
        getContentPane().add(timeMinF);

        timeLMax = new JLabel("Max: ");
        timeLMax.setFont(bigFont);
        timeLMax.setBounds(335, 20, 50, 30);
        getContentPane().add(timeLMax);

        timeMaxF = new JTextField();
        timeMaxF.setBounds(385, 20, 30, 30);
        getContentPane().add(timeMaxF);

        serviceL = new JLabel("Service duration");
        serviceL.setFont(bigFont);
        serviceL.setBounds(50, 60, 150, 30);
        getContentPane().add(serviceL);

        serviceLMin = new JLabel("Min:");
        serviceLMin.setFont(bigFont);
        serviceLMin.setBounds(250, 60, 50, 30);
        getContentPane().add(serviceLMin);

        serviceMinF = new JTextField();
        serviceMinF.setBounds(300, 60, 30, 30);
        getContentPane().add(serviceMinF);

        serviceLMax = new JLabel("Max:");
        serviceLMax.setFont(bigFont);
        serviceLMax.setBounds(335, 60, 50, 30);
        getContentPane().add(serviceLMax);

        serviceMaxF = new JTextField();
        serviceMaxF.setBounds(385, 60, 30, 30);
        getContentPane().add(serviceMaxF);

        noQ = new JLabel("Number of active queues: ");
        noQ.setFont(bigFont);
        noQ.setBounds(50, 100, 300, 30);
        getContentPane().add(noQ);

        noQF = new JTextField();
        noQF.setBounds(300, 100, 30, 30);
        getContentPane().add(noQF);

        noC = new JLabel("Number of clients: ");
        noC.setFont(bigFont);
        noC.setBounds(50, 135, 300, 30);
        getContentPane().add(noC);

        noCF = new JTextField();
        noCF.setBounds(300, 135, 30, 30);
        getContentPane().add(noCF);

        simulation = new JLabel("Simulation interval: ");
        simulation.setFont(bigFont);
        simulation.setBounds(50, 170, 200, 30);
        getContentPane().add(simulation);

        simulationF = new JTextField();
        simulationF.setBounds(300, 170, 30, 30);
        getContentPane().add(simulationF);

        // buttons

        start = new JButton("Start simulation");
        start.setBounds(50, 200, 150, 50);
        getContentPane().add(start);

        // logs

        logScroll = new JScrollPane();
        logScroll.setBounds(970, 50, 250, 300);
        logF = new JTextArea();
        logF.setEditable(false);
        logScroll.setViewportView(logF);
        getContentPane().add(logScroll);
        logF.setText("");
        logL = new JLabel("Logs: ");
        logL.setBounds(970, 20, 100, 30);
        getContentPane().add(logL);

        // min output

        minOutScrollP = new JScrollPane();
        minOutScrollP.setBounds(500, 50, 400, 150);
        minOutF = new JTextArea();
        minOutF.setEditable(false);
        minOutScrollP.setViewportView(minOutF);
        getContentPane().add(minOutScrollP);
        minOutF.setText("");
        minOutL = new JLabel("Min output required: ");
        minOutL.setBounds(500, 20, 400, 30);
        getContentPane().add(minOutL);

        // queues

        q1L = new JLabel("Queue 1");
        q1L.setBounds(100, 250, 100, 30);
        getContentPane().add(q1L);

        q1F = new JTextArea();
        q1F.setEditable(false);
        q1F.setBounds(100, 300, 50, 300);
        q1F.setFont(bigFont);
        getContentPane().add(q1F);

        q2L = new JLabel("Queue 2");
        q2L.setBounds(250, 250, 100, 30);
        getContentPane().add(q2L);

        q2F = new JTextArea();
        q2F.setEditable(false);
        q2F.setBounds(250, 300, 50, 300);
        q2F.setFont(bigFont);
        getContentPane().add(q2F);

        q3L = new JLabel("Queue 3");
        q3L.setBounds(400, 250, 100, 30);
        getContentPane().add(q3L);

        q3F = new JTextArea();
        q3F.setEditable(false);
        q3F.setBounds(400, 300, 50, 300);
        q3F.setFont(bigFont);
        getContentPane().add(q3F);

        q4L = new JLabel("Queue 4");
        q4L.setBounds(550, 250, 100, 30);
        getContentPane().add(q4L);

        q4F = new JTextArea();
        q4F.setEditable(false);
        q4F.setBounds(550, 300, 50, 300);
        q4F.setFont(bigFont);
        getContentPane().add(q4F);

        q5L = new JLabel("Queue 5");
        q5L.setBounds(700, 250, 100, 30);
        getContentPane().add(q5L);

        q5F = new JTextArea();
        q5F.setEditable(false);
        q5F.setBounds(700, 300, 50, 300);
        q5F.setFont(bigFont);
        getContentPane().add(q5F);

        // current time

        currTimeL = new JLabel("Current time: ");
        currTimeL.setFont(biggerFont);
        currTimeL.setBounds(800, 400, 200, 30);
        getContentPane().add(currTimeL);

        currTimeF = new JTextArea();
        currTimeF.setEditable(false);
        currTimeF.setFont(biggerFont);
        currTimeF.setBounds(1000, 400, 100, 50);
        getContentPane().add(currTimeF);


    }

    public void updateQueue(Server server) {
        int noQ = server.getQueueNo();
        if(noQ==1) {
            q1F.setText("");
            if(server.getCurrentTask()!=null) {
                q1F.append(String.valueOf(server.getCurrentTask().getID())+ '\n');
            }
            server.getTasks().forEach(task -> q1F.append(String.valueOf(task.getID()) + '\n'));
        }
        if(noQ==2) {
            q2F.setText("");
            if(server.getCurrentTask()!=null) {
                q2F.append(String.valueOf(server.getCurrentTask().getID())+ '\n');
            }
            server.getTasks().forEach(task -> q2F.append(String.valueOf(task.getID()) + '\n'));
        }
        if(noQ==3) {
            q3F.setText("");
            if(server.getCurrentTask()!=null) {
                q3F.append(String.valueOf(server.getCurrentTask().getID())+ '\n');
            }
            server.getTasks().forEach(task -> q3F.append(String.valueOf(task.getID()) + '\n'));
        }
        if(noQ==4) {
            q4F.setText("");
            if(server.getCurrentTask()!=null) {
                q4F.append(String.valueOf(server.getCurrentTask().getID())+ '\n');
            }
            server.getTasks().forEach(task -> q4F.append(String.valueOf(task.getID()) + '\n'));
        }
        if(noQ==5) {
            q5F.setText("");
            if(server.getCurrentTask()!=null) {
                q5F.append(String.valueOf(server.getCurrentTask().getID())+ '\n');
            }
            server.getTasks().forEach(task -> q5F.append(String.valueOf(task.getID()) + '\n'));
        }

    }

    public void updateLogs() {
        FileReader out;
        LineNumberReader lnr;
        try {
            out = new FileReader("out.txt");
            lnr = new LineNumberReader(out);
            String line;
            while((line = lnr.readLine()) != null) {
                logF.append(line);
                logF.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollBar myScrollBar=logScroll.getVerticalScrollBar();
        myScrollBar.setValue(myScrollBar.getMaximum());
    }

    public void updateMinOut(String text) {
        minOutF.append(text+"\n");
        JScrollBar myScroll = minOutScrollP.getVerticalScrollBar();
        myScroll.setValue(myScroll.getMaximum());
    }

    public void updateCurrentTime(String text) {
        currTimeF.setText(text);
    }

    public JTextField getTimeMinF() {
        return timeMinF;
    }

    public void setTimeMinF(JTextField timeMinF) {
        this.timeMinF = timeMinF;
    }

    public JTextField getTimeMaxF() {
        return timeMaxF;
    }

    public void setTimeMaxF(JTextField timeMaxF) {
        this.timeMaxF = timeMaxF;
    }

    public JTextField getServiceMinF() {
        return serviceMinF;
    }

    public void setServiceMinF(JTextField serviceMinF) {
        this.serviceMinF = serviceMinF;
    }

    public JTextField getServiceMaxF() {
        return serviceMaxF;
    }

    public void setServiceMaxF(JTextField serviceMaxF) {
        this.serviceMaxF = serviceMaxF;
    }

    public JTextField getSimulationF() {
        return simulationF;
    }

    public JTextField getNoQF() {
        return noQF;
    }

    public JTextField getNoCF() {
        return noCF;
    }

    public void addStartButton(final ActionListener actionListener) {
        start.addActionListener(actionListener);
    }
}
