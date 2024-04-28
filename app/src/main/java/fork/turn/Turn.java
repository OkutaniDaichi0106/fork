package fork.turn;

import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.ice4j.ice.*;
import org.ice4j.ice.Agent;
import org.ice4j.ice.harvest.TurnCandidateHarvest;
import org.ice4j.ice.CandidateTcpType;
import org.ice4j.message.Message;

import java.net.DatagramPacket;

public class Turn {
    final String TURN_IP = "";
    final int TURN_SERVER_PORT = 3478;
    final int TURN_PEER_PORT = 3480;


    TransportAddress serverAddress = new TransportAddress(TURN_IP, TURN_SERVER_PORT, Transport.UDP);
    TransportAddress peerAddress = new TransportAddress(TURN_IP, TURN_PEER_PORT, Transport.UDP);

    public static void main(String[] args) {
        Agent agent = new Agent();//ICE Agent

        //Setup the STUN server
        Turn turn = new Turn();
        agent.addCandidateHarvester(TurnCandidateHarvest());
    }
}
