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
public class SenderController implements Initializable {

    @FXML
    private TextArea txtArea1;
    @FXML
    private TextArea txtArea2;
    @FXML
    private Button btnSend;
    @FXML
    private TextField txtFieldIp;
    @FXML
    private TextField txtFieldPort;

    private String log;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnOnAction(javafx.event.ActionEvent event) throws SocketException, IOException {
        String message = txtArea1.getText();
        String ip = txtFieldIp.getText();
        int port = Integer.parseInt(txtFieldPort.getText());

        send(message, ip, port);

    }

    public void send(String message, String ip, int port) throws UnknownHostException, SocketException, IOException {

        Thread t = new Thread(() -> {
            try {
                Mensagem mensagem = new Mensagem();

                mensagem = Create.create(message);
                mensagem.setIp(ip);
                mensagem.setPort(port);

                DatagramPacket datagramPacket = new DatagramPacket(mensagem.getPacket(), mensagem.getMsgLen() + 5, InetAddress.getByName(mensagem.getIp()), mensagem.getPort());
                DatagramSocket datagramSocket = new DatagramSocket();
                datagramSocket.send(datagramPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                datagramSocket.receive(receivePacket);

                Mensagem resposta = new Mensagem();
                resposta = Validate.validate(receiveData);

                if (resposta.isValidate()) {
                    log = "Package send to : " + ip + ":" + port + ". Status: ";

                    txtArea2.appendText(log + resposta.getMsg() + "\n");
                } else {
                    log = "Package deu ruim\n";
                    txtArea2.appendText(log);
                }

                datagramSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(SenderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();
    }
}
