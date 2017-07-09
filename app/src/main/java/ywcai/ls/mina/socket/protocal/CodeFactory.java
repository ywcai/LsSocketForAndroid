package ywcai.ls.mina.socket.protocal;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class CodeFactory  extends DemuxingProtocolCodecFactory {
    public CodeFactory() {
        addMessageDecoder(MesDecode.class);
        addMessageEncoder(MesInf.class, new MesEncode());
    }
}
