package fork.stun;

import fork.Config;

/*
 * This class is used to establish STUN server
 * 
 * 
 */
public class Stun {
	
	private final int PORT;

	Stun(Config config) {
		PORT = config.STUN_PORT;
	}

}
