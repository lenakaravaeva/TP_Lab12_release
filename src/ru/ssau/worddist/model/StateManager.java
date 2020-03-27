package ru.ssau.worddist.model;

import javax.xml.bind.*;
import java.io.File;

public class StateManager {
    public static final String STATE_FILENAME = "state.xml";
    private File file;
    private JAXBContext context;

    public StateManager() throws JAXBException {
        this.file = new File(STATE_FILENAME);
        this.context = JAXBContext.newInstance(StringState.class);
    }

    public String getState() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return ((StringState) unmarshaller.unmarshal(file)).getState();
    }

    public void setState(String state) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new StringState(state), file);
    }
}