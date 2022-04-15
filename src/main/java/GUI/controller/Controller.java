package GUI.controller;

import BusinessLogic.SimulationManager;
import GUI.view.SimulationFrame;
import model.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{

    private SimulationFrame simulationFrame;
    private SimulationManager simulationManager;

    private int arrivalMin;
    private int arrivalMax;
    private int serviceMin;
    private int serviceMax;
    private int noQ;
    private int noC=0;
    private int simDuration;

    public boolean isReady=false;

    public void start() {
        simulationFrame=new SimulationFrame();
        simulationFrame.setVisible(true);
        initializeSimulation();
    }

    public boolean checkInputData() {
        String arrivalMin = simulationFrame.getTimeMinF().getText();
        String arrivalMax = simulationFrame.getTimeMaxF().getText();
        String serviceMin = simulationFrame.getServiceMinF().getText();
        String serviceMax = simulationFrame.getServiceMaxF().getText();
        String noQ = simulationFrame.getNoQF().getText();
        String noC = simulationFrame.getNoCF().getText();
        String simulationTime = simulationFrame.getSimulationF().getText();

        int arrMin,arrMax,serMin,serMax,no,simTime,nc;
        try {
            arrMin = Integer.parseInt(arrivalMin);
            arrMax = Integer.parseInt(arrivalMax);
            serMin = Integer.parseInt(serviceMin);
            serMax = Integer.parseInt(serviceMax);
            no = Integer.parseInt(noQ);
            nc = Integer.parseInt(noC);
            simTime = Integer.parseInt(simulationTime);

            if (arrMin > 0 && arrMax > 0 && serMax > 0 && serMin > 0 && no > 0  && simTime > 0
                    && nc > 0) {
                if (arrMin < arrMax && serMin < serMax) {
                    setData(arrMin, arrMax, serMin, serMax, no, nc ,simTime);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input data!");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input data!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input data!");
            return false;
        }
    }

    public void setData(int arrMin,int arrMax, int serMin, int serMax, int noQ, int noC, int simTime) {
        this.arrivalMin = arrMin;
        this.arrivalMax = arrMax;
        this.serviceMax = serMax;
        this.serviceMin = serMin;
        this.noQ = noQ;
        this.noC = noC;
        this.simDuration = simTime;

    }

    public void initializeSimulation() {
        simulationFrame.addStartButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInputData()) {
                    System.out.println("Good input data!");
                    System.out.println(arrivalMin);
                    System.out.println(arrivalMax);
                    System.out.println(serviceMin);
                    System.out.println(serviceMax);
                    System.out.println(noQ);
                    System.out.println(noC);
                    System.out.println(simDuration);
                    isReady = true;
                    simulationManager = new SimulationManager(simDuration,serviceMax,serviceMin,arrivalMin,arrivalMax,noQ, noC,simulationFrame);
                    Thread t = new Thread(simulationManager);
                    t.start();
                }
            }
        });

    }
}
