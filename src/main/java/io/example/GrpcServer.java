package io.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.proto.HelloServiceGrpc;

/**\
 * Sample GRPC Server
 * @author smenon
 *
 */
public class GrpcServer {

	public static void main(String args[]) throws Exception {

		final Server server = ServerBuilder.forPort(8000)
				.addService(new HelloServiceImpl())
				.build();

		server.start();
		System.out.println("Server started");

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("Closing server");
				server.shutdown();
			}
		}));
		server.awaitTermination();
	}

}
