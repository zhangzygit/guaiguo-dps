package top.guaiguo.springdps.thrift.client;

import lombok.Getter;
import lombok.Setter;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.guaiguo.springdps.thrift.generate.Person;
import top.guaiguo.springdps.thrift.generate.PersonService;
import top.guaiguo.springdps.thrift.generate.PersonType;

import java.util.Objects;

public class ThriftClient {

    public static void main(String[] args) throws Exception {
        //同步阻塞式IO
        syncIOClient();
        //同步非阻塞式IO
//        syncNIOClient();
        //异步构建客户端
//        aysncClient();
    }

    private static void aysncClient() throws Exception {
        TNonblockingSocket socket = new TNonblockingSocket("127.0.0.1", 8888);
        TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
        TAsyncClientManager tAsyncClientManager = new TAsyncClientManager();
        PersonService.AsyncClient asyncClient = new PersonService.AsyncClient(factory, tAsyncClientManager, socket);
        MyAsyncMethodCallback callback = new MyAsyncMethodCallback();
        asyncClient.getPerson(PersonType.man, callback);
        Object resp = callback.getResp();
        while (Objects.isNull(resp)) {
            resp = callback.getResp();
        }
        System.out.println(((PersonService.AsyncClient.getPerson_call) resp).getResult());
        System.out.println("over");
    }

    static class MyAsyncMethodCallback implements AsyncMethodCallback {
        @Getter
        @Setter
        private Object resp;

        @Override
        public void onComplete(Object response) {
            this.resp = response;
        }

        @Override
        public void onError(Exception exception) {

        }
    }

    private static void syncIOClient() throws TException {
        TTransport transport = new TSocket("127.0.0.1", 8888);
        TProtocol protocol = new TBinaryProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        transport.open();
        Person person = client.getPerson(PersonType.man);
        System.out.println(person);
    }

    private static void syncNIOClient() throws TException {
        TTransport transport = new TSocket("127.0.0.1", 8888);
        TTransport tFramedTransport = new TFramedTransport(transport, 16384000);
        TProtocol protocol = new TBinaryProtocol(tFramedTransport);
        PersonService.Client client = new PersonService.Client(protocol);
        tFramedTransport.open();
        Person person = client.getPerson(PersonType.man);
        System.out.println(person);
    }
}
