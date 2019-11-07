package io.example;

import io.grpc.stub.StreamObserver;
import io.proto.Greeting;
import io.proto.HelloRequest;
import io.proto.HelloServiceGrpc.HelloServiceImplBase;


/**
 * 
 * This class was created manually. This is the main service.
 * HelloServiceImplBase was auto generated
 */
public class HelloServiceImpl extends HelloServiceImplBase {

	//Overriding the method defined in proto file
	@Override
	public void hello(HelloRequest request, StreamObserver<Greeting> responseObserver) {

		Greeting response = Greeting.newBuilder().setGreeting(request.getHelloMsg()).setFrom(request.getHelloMsg())
				.build();

		responseObserver.onNext(response);//sends response
		responseObserver.onCompleted();//closes the GPRC call
	}
}