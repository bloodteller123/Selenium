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
    private String discardCard;
    private int current_player_id;
    private int current_round_starter;
    private int next_player_id;
    private int next_round_starter;
    private boolean reverse;
    public Server(int port) {
        super(new InetSocketAddress(port));
        conns = new ArrayList<>();
        deck = new Deck();
        players = new ArrayList<>();
        discardCard = "";
        current_player_id = 1;
        current_round_starter = 1;
        next_player_id = 2;
        next_round_starter = 2;
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
            conn.send("id,"+id+","+current_round_starter+","+conns.size());
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
        String[] msgs = message.split(",");
        switch (msgs[0]){
            case "start":
                gameStart = true;
//                current_round_starter =
                current_player_id = Integer.parseInt(msgs[1]);
                next_player_id = current_player_id%conns.size()+1;
                broadcast("start,"+current_player_id);
                dealCard(0);
                updateDiscard(deck.drawCard().toString());
                updateStock();
                broadcast("turn,"+current_player_id+","+next_player_id);
                break;
            case "discard":
                removeCards(msgs[1], Integer.parseInt(msgs[2]));
                updateDiscard(msgs[1]);
                break;
            case "special":
                if(msgs[1].equals("Q")){
                    System.out.println(reverse);
                    if(reverse)  next_player_id = next_player_id==1? conns.size() : next_player_id-1;
                    else  next_player_id = next_player_id==conns.size()? 1 : next_player_id+1;
                    broadcast("updateN,"+next_player_id+","+(reverse?"right":"left"));
                }else if(msgs[1].equals("1")){
                    reverse = !reverse;
                    if(reverse) next_player_id = current_player_id==1?conns.size() : current_player_id-1;
                    else  next_player_id = current_player_id==conns.size()? 1 : current_player_id+1;
                    broadcast("updateN,"+next_player_id+","+(reverse?"right":"left"));
                }else{

                }
                break;
            case "pass":
                current_player_id = next_player_id;
                System.out.println(reverse);
                if(reverse)  next_player_id = next_player_id==1? conns.size() : next_player_id-1;
                else  next_player_id = next_player_id==conns.size()? 1 : next_player_id+1;
                broadcast("turn,"+current_player_id+","+next_player_id+","+(reverse?"right":"left"));
                break;
        }
    }
    public void removeCards(String discard, int id){
        this.discardCard = discard;
        if(id!=-1)players.get(id-1).removeCard(discard);
    }
    public void updateDiscard(String discard){
        // update discard card
        this.discardCard = discard;
//        if(id!=-1)players.get(id-1).removeCard(discard);
        for (WebSocket sock : conns) {
            sock.send("discard,"+this.discardCard);
        }
    }
    public void updateStock(){
        broadcast("stock,"+(this.deck.getCardsSize() - this.deck.getInd()));
    }
    public void dealCard(int id){
        if(deck.isExausted()){
            broadcast("empty,");
            // TODO
        }
        if(id==0){
            for(int i=0;i<5;i++){
                for(int j=0;j<conns.size();j++){
                    Card c = (deck.drawCard());
                    players.get(j).addCard(c);
                    conns.get(j).send("card,"+c.toString());
                }
            }
        }else{
            Card c = (deck.drawCard());
            players.get(id-1).addCard(c);
            conns.get(id-1).send("card,"+c.toString());
        }
    }
    public boolean checkWin(){
        return players.stream().anyMatch(p -> p.getScore()>=100);
    }
    public void updateScore(){
        for(Player p: players){
            p.setScore(p.calculateScore());
        }
    }
    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            conns.remove(conn);
        }
//        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8888;
        Server s = new Server(port);
        s.start();
        System.out.println("game server started on port: " + s.getPort());
    }
    public void setPlayers(Player p){
        players.add(p);
    }
    public int getNextPlayer(){
        return this.next_player_id;
    }
}
