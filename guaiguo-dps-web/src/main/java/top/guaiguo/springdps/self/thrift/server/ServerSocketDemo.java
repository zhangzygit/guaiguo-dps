package top.guaiguo.springdps.self.thrift.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangzy
 * @version 1.0.0
 * @createTime 2020/4/1
 * @Description
 */
public class ServerSocketDemo {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8888);
        System.out.println("server start");
        Socket accept = socket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        System.out.println("来自客户端的消息：" + bufferedReader.readLine());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bufferedWriter.write("the message is from server\n");
        bufferedWriter.flush();
    }
}
