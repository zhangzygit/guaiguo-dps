package top.guaiguo.springdps.self.thrift.server;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import top.guaiguo.springdps.self.thrift.generate.PersonService;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-apachethrift/index.html
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
//        nonblockServer();
        //多线程阻塞式服务
        threadPoolServer();

    }

    private static void threadPoolServer() throws TTransportException {
        TServerTransport transport = new TServerSocket(8888);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport);
        args.protocolFactory(new TBinaryProtocol.Factory());
        args.processorFactory(new TProcessorFactory(new PersonService.Processor<PersonServiceImpl>(new PersonServiceImpl())));
        TThreadPoolServer server = new TThreadPoolServer(args);
        server.serve();
    }

    private static void nonblockServer() throws TTransportException {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8888);
        TNonblockingServer.Args args1 = new TNonblockingServer.Args(serverSocket);
        args1.protocolFactory(new TBinaryProtocol.Factory());
        args1.processorFactory(new TProcessorFactory(new PersonService.Processor<PersonServiceImpl>(new PersonServiceImpl())));
        TNonblockingServer server = new TNonblockingServer(args1);
        server.serve();
    }

}
