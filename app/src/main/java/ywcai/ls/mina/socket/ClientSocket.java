package ywcai.ls.mina.socket;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import ywcai.ls.mina.socket.protocal.CodeFactory;
import ywcai.ls.mina.socket.protocal.MesInf;
import ywcai.ls.mina.socket.protocal.ProtocolRequest;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


public class ClientSocket extends IoHandlerAdapter {
    private List<SocketEventListener> listeners = new ArrayList<SocketEventListener>();
    private IoSession ioSession = null;
    private ProtocolRequest protocolRequest = new ProtocolRequest();

    public void CreateSession(String address, int port) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).sessionCreateStart(address, port);
        }
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast
                ("codec", new ProtocolCodecFilter(new CodeFactory()));
        connector.setHandler(this);
        ConnectFuture cf = connector.connect(new InetSocketAddress(address, port));
        cf.awaitUninterruptibly();
        ioSession = cf.isConnected() ? cf.getSession() : null;
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).sessionCreateEnd(null, cf.isConnected());
        }
    }

    public WriteFuture sentMes(byte[] data) {
        protocolRequest.setPayLoadData(data);
        return ioSession.write(protocolRequest);
    }

    public void setConnToken(byte[] token) {
        protocolRequest.setToken(token);
    }

    public boolean getSessionStatus() {
        if (ioSession == null) {
            return false;
        }
        return ioSession.isConnected() && !ioSession.isClosing();
    }

    public IoSession getIoSession() {
        return ioSession;
    }

    public void CloseSession() {
        if (getSessionStatus()) {
            ioSession.close(true);
        }
        ioSession=null;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        super.exceptionCaught(session, cause);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).errorCatch(session, cause);
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // TODO Auto-generated method stub
        super.messageReceived(session, message);
        byte[] data = ((MesInf) message).getData();
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).messageReceived(session, data);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        super.messageSent(session, message);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).messageSent(session, message);
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).sessionClosed(session);
        }
        super.sessionClosed(session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionCreated(session);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).sessionCreated(session);
        }
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionOpened(session);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).sessionOpened(session);
        }
    }

    public void addListener(SocketEventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(SocketEventListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void removeAllListener() {
        listeners.clear();
    }


}
