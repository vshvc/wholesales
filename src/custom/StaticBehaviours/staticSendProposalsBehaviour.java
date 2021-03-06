package custom.StaticBehaviours;

import custom.BuyerAgent;
import custom.StaticBehaviours.staticBuyerBehaviour;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Calendar;
import java.util.List;

public class staticSendProposalsBehaviour extends OneShotBehaviour {

    private BuyerAgent myBuyerAgent;

    @Override
    public void action() {
        myBuyerAgent = (BuyerAgent) myAgent;

        myBuyerAgent.listOfDynamicAgents.forEach(aid -> {
            ACLMessage startConversationMessage = new ACLMessage(ACLMessage.PROPOSE);
            startConversationMessage.addReceiver(aid);

            String message = "";
            int[] routes = myBuyerAgent.getRoutes();
            for (int i = 0; i < routes.length; i++)
            {
                message += Integer.toString(routes[i]);
                if (i < routes.length - 1)
                    message += ",";
            }

            message += " " + Integer.toString(myBuyerAgent.getIndex());

            startConversationMessage.setContent(message); // отправляем точку(точки), куда надо доставить и свой индекс

            myBuyerAgent.send(startConversationMessage);
        });
    }
}
