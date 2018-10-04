package MASLesson3;

import jade.core.Agent;

public class AgentBuyer extends Agent{
    protected void setup(){
    super.setup();
    addBehaviour(new StartOfBuying(this));
}
}
