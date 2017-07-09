package ywcai.ls.mina.socket;

import org.apache.mina.core.session.IoSession;

public interface SocketEventListener {
	public void sessionCreated(IoSession session);
	public void sessionOpened(IoSession session);
	public void messageReceived(IoSession session, byte[] data);
	public void errorCatch(IoSession session, Throwable cause);
	public void messageSent(IoSession session, Object message);
	public void sessionClosed(IoSession session);
	public void sessionCreateStart(String ip,int port);
	public void sessionCreateEnd(IoSession session,boolean status);
}
