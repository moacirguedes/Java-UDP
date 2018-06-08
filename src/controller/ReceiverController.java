package controller;

import Model.Mensagem;
import UDP.Create;
import UDP.Validate;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moaci
 */
public class ReceiverController implements Initializable {

    @FXML
    private TextArea txtArea1;
    @FXML
    private Button btnReceive;
    @FXML
    private TextField txtFieldPort;
    @FXML
    private TextArea txtArea2;

    private String mensagem;

    private int tamMsg;

    private String log;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnOnAction(ActionEvent event) throws UnknownHostException, IOException {
        receive(Integer.parseInt(txtFieldPort.getText()));
    }

    public void receive(int port) throws SocketException, UnknownHostException, IOException {

        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];

        log = "Listening on udp:" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "\n";
        txtArea2.appendText(log);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        Thread t = new Thread(() -> {
            while (true) {

                try {
                    serverSocket.receive(receivePacket);
                    Mensagem mensagem = new Mensagem();
                    mensagem = Validate.validate(receiveData);

                    if (mensagem.isValidate()) {

                        txtArea1.setText(mensagem.getMsg());
                        
                        Mensagem resposta = new Mensagem();
                        resposta = Create.create("Mensagem Recebida");

                        DatagramPacket sendPacket = new DatagramPacket(resposta.getPacket(), resposta.getMsgLen()+5, receivePacket.getAddress(), receivePacket.getPort());
                        serverSocket.send(sendPacket);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReceiverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();

    }

}
