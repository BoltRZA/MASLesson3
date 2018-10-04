package MASLesson3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitingForResponse extends Behaviour {
    private Agent agent;
    private boolean responseReceived;
    public WaitingForResponse(Agent agent){
        this.agent = agent;
    }

    @Override
    public void action(){
//        Another variant
//        MessageTemplate mt1 = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
//        MessageTemplate mt2 = MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM)
//        ACLMessage confirm = agent.receive(mt1);
//        ACLMessage disconfirm = agent.receive(mt2);
        MessageTemplate mt3 = MessageTemplate.or(
                MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
                MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM));
        ACLMessage response = agent.receive(mt3);

        if (response != null) {
            if (response.getPerformative() == ACLMessage.CONFIRM) {
                System.out.println("I-ve received confirm! " + response.getContent());
            } else {
                System.out.println("I-ve received disconfirm! " + response.getContent());
            }
            responseReceived = true;
        }
        else{
                block();
            }
        }

    @Override
    public boolean done(){
        return false;
    }
}
