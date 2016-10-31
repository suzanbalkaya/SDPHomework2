package com.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {//bütün clientlr burda
	public static List<ConnectionThread> allConnections = new ArrayList<ConnectionThread>();

	public static void addClient(Socket socket) {
		ConnectionThread connectionThread = new ConnectionThread(socket);
		allConnections.add(connectionThread);
		connectionThread.start();
		

	}

	public static void removClient(ConnectionThread connectionThread) {
		allConnections.remove(connectionThread);

	}
}
