package client;

public enum ClientState {
	ENTER_SERVICE("ENTER"),
	ADD_IDEA("ADD"),
	QUIT("BYE");

	private String command = "";

	ClientState(String string) {
		command = string;
	}

	public static ClientState fromString(String command) {
		if (command != null){
			for (ClientState cs : ClientState.values()){
				if (cs.toString().equalsIgnoreCase(command))
					return cs;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return command;
	}
}
