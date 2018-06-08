/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import Model.Mensagem;

/**
 *
 * @author moaci
 */
public class Create {

    public static Mensagem create(String message) {
        
        Mensagem mensagem = new Mensagem(message, message.length());
        
        byte[] packet = new byte[mensagem.getMsgLen() + 5];

        packet[0] = (byte) 0x80;
        packet[2] = (byte) mensagem.getMsgLen();
        packet[1] = (byte) (mensagem.getMsgLen() >> 8);

        byte[] temp = mensagem.getMsg().getBytes();

        System.arraycopy(temp, 0, packet, 3, mensagem.getMsgLen());

        packet[packet.length - 2] = (byte) 0;
        packet[packet.length - 1] = (byte) 0;

        mensagem.setPacket(packet);
        return mensagem;
    }

}
