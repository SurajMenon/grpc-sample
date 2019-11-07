package io.example;

import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.ListenableFuture;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.proto.Greeting;
import io.proto.HelloRequest;
import io.proto.HelloRequestOrBuilder;
import io.proto.HelloServiceGrpc;
import io.proto.HelloServiceGrpc.HelloServiceBlockingStub;
import io.proto.HelloServiceGrpc.HelloServiceFutureStub;


public class GrpcClient {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8000)
          .usePlaintext() //disable ssl
          .build();
 
        //Sync client
        HelloServiceBlockingStub client  = HelloServiceGrpc.newBlockingStub(channel);
        
        HelloRequest request = HelloRequest.newBuilder().setHelloMsg("hello").build();
		Greeting response = client.hello(request);
		
		System.out.println(response.getFrom());
		System.out.println(response.getGreeting());
 
        //Async client
        HelloServiceFutureStub asyncStub  = HelloServiceGrpc.newFutureStub(channel);
        ListenableFuture<Greeting> responseFuture = asyncStub.hello(request);
        
        response = responseFuture.get();
        System.out.println(response.getFrom());
		System.out.println(response.getGreeting());
        
        channel.shutdown();
    }
}