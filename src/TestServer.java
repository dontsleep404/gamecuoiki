import custom.CServer;

public class TestServer extends CServer{
	public TestServer() {
		super(25565);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.google.gson.Gson");
		new TestServer();
	}
}
