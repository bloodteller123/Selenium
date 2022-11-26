package crazyEight;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

public class Server extends WebSocketServer {

    private static List<WebSocket> conns;
    private static Deck deck;
    private static boolean f_player;
    private List<Player> players;
    private boolean gameStart;
    private int id=0;
    public Server(int port) {
        super(new InetSocketAddress(port));
        conns = new ArrayList<>();
        deck = new Deck();
        players = new ArrayList<>();
        // overall scores
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(500);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        if(!gameStart) {
            players.add(new Player(++id));
            conns.add(conn);
            System.out.println(conns.size());
            System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from client: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            conns.remove(conn);
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8888;
        Server s = new Server(port);
        s.start();
        System.out.println("game server started on port: " + s.getPort());
    }
}
