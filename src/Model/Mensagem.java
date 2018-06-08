package Model;

/**
 *
 * @author moaci
 */
public class Mensagem {
    private String msg;
    private int msgLen;
    private String ip;
    private int port;
    private byte packet[];
    private boolean validate;

    public Mensagem() {
    }

    public Mensagem(String msg, int msgLen) {
        this.msg = msg;
        this.msgLen = msgLen;
    }

    public Mensagem(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] getPacket() {
        return packet;
    }

    public void setPacket(byte[] packet) {
        this.packet = packet;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
