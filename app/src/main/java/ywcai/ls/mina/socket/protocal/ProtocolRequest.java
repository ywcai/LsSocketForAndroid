package ywcai.ls.mina.socket.protocal;


import ywcai.ls.mina.socket.MyConfig;

public class ProtocolRequest implements MesInf {
	private int dataLenth;
	private byte[] data=null,token=null;
	private byte reqFlag;

	public ProtocolRequest() {
		// TODO Auto-generated constructor stub
		this.reqFlag= MyConfig.INT_PROTOCOL_HEAD_FLAG;
		this.token=MyConfig.BYTES_PROTOCOL_HEAD_TOKEN;
	}
	public void setToken(byte[] _token)
	{
		this.token=_token;
	}
	public void setPayLoadData(byte[] _data)
	{
		this.dataLenth=_data.length;
		this.data=_data;
	}
	@Override
	public int getDataLength() {
		// TODO Auto-generated method stub
		return dataLenth;
	}

	@Override
	public byte getReqFlag() {
		return reqFlag;
	}

	@Override
	public byte[] getToken() {
		return token;
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return data;
	}
}
