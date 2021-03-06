package custom.DynamicBehaviours;

import custom.Agree;
import custom.BuyerAgent;
import jade.core.Agent;
import jade.core.behaviours.ReceiverBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class dynamicReceiveAgreeBehaviour extends ReceiverBehaviour {

    private BuyerAgent myBuyerAgent;

    public dynamicReceiveAgreeBehaviour(Agent a, int millis) {
        super(a, millis, MessageTemplate.MatchPerformative(ACLMessage.AGREE));
    }

    public void onStart() {
        myBuyerAgent = (BuyerAgent) myAgent;
    }

    public void handle(ACLMessage m) {

        if (m == null) {
            //System.out.println(myBuyerAgent.getLocalName() + " received a null message");
        } else {
            myBuyerAgent.agreeOffers.add(new Agree(m));
            System.out.println(myBuyerAgent.getLocalName() + " received an agree message from " + m.getSender().getLocalName());
        }
    }
}
