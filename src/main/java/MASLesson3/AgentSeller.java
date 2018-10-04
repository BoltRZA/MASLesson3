package MASLesson3;


import jade.core.Agent;

public class AgentSeller extends Agent {

    @Override
    protected void setup(){
        super.setup();
        addBehaviour(new WaitingForRequest(this));
    }
}
