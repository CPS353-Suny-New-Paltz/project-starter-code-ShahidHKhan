package project.datacompute;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class DataComputeServer {

    private Server server;

    public void start(int port) throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(new DataComputeServiceImpl())
                .build()
                .start();

        System.out.println("DataComputeServer started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                DataComputeServer.this.stop();
            } catch (Exception ignored) {}
        }));
    }

    public void stop() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws Exception {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        DataComputeServer server = new DataComputeServer();
        server.start(60051);
        server.blockUntilShutdown();
    }
}
