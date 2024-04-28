package fork.stun;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.ice4j.StackProperties;
import org.ice4j.Transport;
import org.ice4j.TransportAddress;
import org.ice4j.ice.Agent;
import org.ice4j.ice.IceMediaStream;
import org.ice4j.ice.harvest.StunCandidateHarvester;

import fork.Config;

/*
 * This class is used to access STUN server
 * 
 * 
 */
public class Stun {
	/*
	 * agent
	 */
	Agent agent = new Agent();
	final int MEDIA_STREAM_PORT = 5000;

	/*
	 * STUN_HOSTNAMES is the map with hostnames of public STUN servers.
	 * this map has hostnames as key and port number as value.
	 */
	final Map<String, Integer> STUN_HOSTNAMES = new HashMap<>() {
		{
			put("stun01.sipphone.com", 3478);
			put("stun.ekiga.net", 3478);
			put("stun.fwdnet.net", 3478);
			put("stun.ideasip.com", 3478);
			put("stun.iptel.org", 3478);
			put("stun.rixtelecom.se", 3478);
			put("stun.schlund.de", 3478);
			put("stunserver.org", 3478);
			put("stun.softjoys.com", 3478);
			put("stun.voiparound.com", 3478);
			put("stun.voipbuster.com", 3478);
			put("stun.voipstunt.com", 3478);
			put("stun.voxgratia.org", 3478);
			put("stun.xten.com", 3478);
			put("stun.l.google.com", 19302);
			put("stun1.l.google.com", 19302);
			put("stun2.l.google.com", 19302);
			put("stun3.l.google.com", 19302);
			put("stun4.l.google.com", 19302);
		}
	};

	/*
	 * runSTUN() method runs STUN process
	 */
	void runSTUN(){
		//Setup STUN servers
		for(Map.Entry<String, Integer> entry : STUN_HOSTNAMES.entrySet()) {
			String hostname = entry.getKey();
			int port = entry.getValue();
			try {
				InetAddress ipAddress = InetAddress.getByName(hostname);
				TransportAddress tpAddress = new TransportAddress(ipAddress, port, Transport.UDP);
				agent.addCandidateHarvester(new StunCandidateHarvester(tpAddress));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * Setup streams on the Agent to open a flow of information on a specific port.
		 * Now the port number is defined by MEDIA_STREAM_PORT
		 */
		IceMediaStream stream = agent.createMediaStream("audio");
		try {
			agent.createComponent(stream, MEDIA_STREAM_PORT, MEDIA_STREAM_PORT, MEDIA_STREAM_PORT + 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Re-construct information
		 * 
		 * 
		 */
		String toSend = Sdp
		
	}


	public static void main(String[] args) {
		/* Setup the STUN servers */
		Stun stun = new Stun();
		stun.runSTUN();
	}
}
/*
 * Now we have our port and we have our stream to allow for information to flow. 
 * The issue is that once we have all the information we need each computer to get the remote computer's information. 
 * Of course how do you get that information if you can't connect? There might be a few ways, 
 * but the easiest with just ICE4J is to POST the information to your public sever and retrieve the information. 
 * I even use a simple PHP server I wrote to store and spit out information. 
 */