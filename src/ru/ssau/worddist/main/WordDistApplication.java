package ru.ssau.worddist.main;

import ru.ssau.worddist.model.StateManager;
import ru.ssau.worddist.view.DistUI;

import javax.xml.bind.JAXBException;

public class WordDistApplication {
    private StateManager stateManager;
    private DistUI ui;

    public WordDistApplication() {
        try {
            this.stateManager = new StateManager();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        this.ui = new DistUI(this);
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void run() {
        ui.run();
    }
}