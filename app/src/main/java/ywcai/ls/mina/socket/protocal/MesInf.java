package ywcai.ls.mina.socket.protocal;

public interface MesInf {
	 byte getReqFlag();
	 byte[] getToken();
	 int getDataLength();
	 byte[] getData();
}
