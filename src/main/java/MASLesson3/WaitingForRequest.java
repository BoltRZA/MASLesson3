package MASLesson3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Random;

public class WaitingForRequest extends Behaviour {

    private Agent agent;

    public  WaitingForRequest(Agent agent){
        this.agent =agent;
    }

    @Override
    public void action(){
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchProtocol("bookBuying"));
                ACLMessage request = agent.receive(mt);

                if (request != null){
                    Random r = new Random();
                    int action = r.nextInt(3);
                    System.out.println("Random = " + action);
                    ACLMessage response = request.createReply();
                    if (action == 0) {
                        response.setPerformative(ACLMessage.CONFIRM);
                        response.setContent("= 0");
                        agent.send(response);
                        System.out.println("I-ve sent a confirm message!");
                    }
                    else if (action == 1 ) {
                        response.setPerformative(ACLMessage.DISCONFIRM);
                        agent.send(response);
                        response.setContent("= 1");
                        System.out.println("I-ve sent a disconfirm message!");
                    }
                    else {
                        System.out.println("I-ve ignored the buver!");

                    }
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
