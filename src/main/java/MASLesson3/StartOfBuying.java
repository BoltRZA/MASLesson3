package MASLesson3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class StartOfBuying extends OneShotBehaviour {
    private Agent agent;

    public StartOfBuying(Agent agent){
        this.agent = agent;
    }

    @Override
    public void action(){
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.setContent("I Want to buy a book!");
        request.addReceiver(new AID("Seller",false));
        request.setProtocol("bookBuying");
        agent.send(request);

        WaitingForResponse behaviourToKill = new WaitingForResponse(agent);
        agent.addBehaviour(behaviourToKill);
        agent.addBehaviour(new BehaviourKiller(agent,20000, behaviourToKill));
    }
}
