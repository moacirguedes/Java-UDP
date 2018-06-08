/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import Model.Mensagem;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author moaci
 */
public class Validate {

    public static Mensagem validate(byte[] receiveData) throws UnsupportedEncodingException {
        Mensagem mensagem = new Mensagem();

        if (receiveData[0] == (byte) 0x80) {

            int tamMsg;
            tamMsg = (int) receiveData[1];
            tamMsg <<= 8;
            tamMsg |= (int) receiveData[2];

            byte[] packet = new byte[tamMsg];

            for (int i = 3; i < tamMsg + 3; i++) {
                packet[i - 3] = receiveData[i];
            }

            String msg = new String(packet, "UTF-8");
            
            mensagem.setMsg(msg);
            mensagem.setValidate(true);
        }
        return mensagem;

    }
}
