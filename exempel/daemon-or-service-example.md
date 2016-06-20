# Att stänga av en server

I följande kodsnutt instantieras en server av någon typ. Sedan skapas
en tråd som kommer att aktiveras om servicen eller daemonen stängs av,
till exemepl för att värdsystemet startas om. Efter att
avslutningstråden skapats startas servern.


final Server server = new Server();
/*
 * We create a thread that will close the server when the JVM
 * terminates. This is useful if the server runs as a system
 * service.
 */
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	try {
		server.close();
	} catch (Exception e) {
		logger.log(Level.WARNING, e.getMessage());
		throw new RuntimeException(e.getMessage(), e);
	}
}));
server.run();
