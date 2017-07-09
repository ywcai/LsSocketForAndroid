package ywcai.ls.mina.socket;

/**
 * Created by zmy_11 on 2016/9/3.
 */
public class MyConfig {
    public static final  int INT_SOCKET_HEAD_LEN=21;
    public static final  int INT_PROTOCOL_HEAD_FLAG=0x01;
    public static final  int INT_SOCKET_TOKEN_POS=1;
    public static final  int INT_SOCKET_LENTH_POS=17;
    public static final  byte[] BYTES_PROTOCOL_HEAD_TOKEN={0x3a,0x3b,0x3c,0x3d,0x4a,0x4b,0x4c,0x4d,0x5a,0x5b,0x5c,0x5d,0x6a,0x6b,0x6c,0x6d};
}
