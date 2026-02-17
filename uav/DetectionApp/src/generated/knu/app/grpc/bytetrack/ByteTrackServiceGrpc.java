package knu.app.grpc.bytetrack;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ========================
 * SERVICE
 * ========================
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ByteTrackServiceGrpc {

  private ByteTrackServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "bytetrack.ByteTrackService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.InitRequest,
      knu.app.grpc.bytetrack.InitResponse> getInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Init",
      requestType = knu.app.grpc.bytetrack.InitRequest.class,
      responseType = knu.app.grpc.bytetrack.InitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.InitRequest,
      knu.app.grpc.bytetrack.InitResponse> getInitMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.InitRequest, knu.app.grpc.bytetrack.InitResponse> getInitMethod;
    if ((getInitMethod = ByteTrackServiceGrpc.getInitMethod) == null) {
      synchronized (ByteTrackServiceGrpc.class) {
        if ((getInitMethod = ByteTrackServiceGrpc.getInitMethod) == null) {
          ByteTrackServiceGrpc.getInitMethod = getInitMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.bytetrack.InitRequest, knu.app.grpc.bytetrack.InitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Init"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.bytetrack.InitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.bytetrack.InitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ByteTrackServiceMethodDescriptorSupplier("Init"))
              .build();
        }
      }
    }
    return getInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.UpdateRequest,
      knu.app.grpc.bytetrack.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = knu.app.grpc.bytetrack.UpdateRequest.class,
      responseType = knu.app.grpc.bytetrack.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.UpdateRequest,
      knu.app.grpc.bytetrack.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.bytetrack.UpdateRequest, knu.app.grpc.bytetrack.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = ByteTrackServiceGrpc.getUpdateMethod) == null) {
      synchronized (ByteTrackServiceGrpc.class) {
        if ((getUpdateMethod = ByteTrackServiceGrpc.getUpdateMethod) == null) {
          ByteTrackServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.bytetrack.UpdateRequest, knu.app.grpc.bytetrack.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.bytetrack.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.bytetrack.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ByteTrackServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getCloseMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.protobuf.Empty> getCloseMethod;
    if ((getCloseMethod = ByteTrackServiceGrpc.getCloseMethod) == null) {
      synchronized (ByteTrackServiceGrpc.class) {
        if ((getCloseMethod = ByteTrackServiceGrpc.getCloseMethod) == null) {
          ByteTrackServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ByteTrackServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.bytetrack.BufferCapacityResponse> getGetBufferCapacityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBufferCapacity",
      requestType = com.google.protobuf.Empty.class,
      responseType = knu.app.grpc.bytetrack.BufferCapacityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.bytetrack.BufferCapacityResponse> getGetBufferCapacityMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, knu.app.grpc.bytetrack.BufferCapacityResponse> getGetBufferCapacityMethod;
    if ((getGetBufferCapacityMethod = ByteTrackServiceGrpc.getGetBufferCapacityMethod) == null) {
      synchronized (ByteTrackServiceGrpc.class) {
        if ((getGetBufferCapacityMethod = ByteTrackServiceGrpc.getGetBufferCapacityMethod) == null) {
          ByteTrackServiceGrpc.getGetBufferCapacityMethod = getGetBufferCapacityMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, knu.app.grpc.bytetrack.BufferCapacityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBufferCapacity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.bytetrack.BufferCapacityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ByteTrackServiceMethodDescriptorSupplier("GetBufferCapacity"))
              .build();
        }
      }
    }
    return getGetBufferCapacityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ByteTrackServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceStub>() {
        @java.lang.Override
        public ByteTrackServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ByteTrackServiceStub(channel, callOptions);
        }
      };
    return ByteTrackServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ByteTrackServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceBlockingV2Stub>() {
        @java.lang.Override
        public ByteTrackServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ByteTrackServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ByteTrackServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ByteTrackServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceBlockingStub>() {
        @java.lang.Override
        public ByteTrackServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ByteTrackServiceBlockingStub(channel, callOptions);
        }
      };
    return ByteTrackServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ByteTrackServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ByteTrackServiceFutureStub>() {
        @java.lang.Override
        public ByteTrackServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ByteTrackServiceFutureStub(channel, callOptions);
        }
      };
    return ByteTrackServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void init(knu.app.grpc.bytetrack.InitRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.InitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitMethod(), responseObserver);
    }

    /**
     */
    default void update(knu.app.grpc.bytetrack.UpdateRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void close(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    /**
     */
    default void getBufferCapacity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBufferCapacityMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ByteTrackService.
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public static abstract class ByteTrackServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ByteTrackServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ByteTrackService.
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public static final class ByteTrackServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ByteTrackServiceStub> {
    private ByteTrackServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ByteTrackServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ByteTrackServiceStub(channel, callOptions);
    }

    /**
     */
    public void init(knu.app.grpc.bytetrack.InitRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.InitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(knu.app.grpc.bytetrack.UpdateRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBufferCapacity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ByteTrackService.
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public static final class ByteTrackServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ByteTrackServiceBlockingV2Stub> {
    private ByteTrackServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ByteTrackServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ByteTrackServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public knu.app.grpc.bytetrack.InitResponse init(knu.app.grpc.bytetrack.InitRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.bytetrack.UpdateResponse update(knu.app.grpc.bytetrack.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(com.google.protobuf.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.bytetrack.BufferCapacityResponse getBufferCapacity(com.google.protobuf.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ByteTrackService.
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public static final class ByteTrackServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ByteTrackServiceBlockingStub> {
    private ByteTrackServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ByteTrackServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ByteTrackServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public knu.app.grpc.bytetrack.InitResponse init(knu.app.grpc.bytetrack.InitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.bytetrack.UpdateResponse update(knu.app.grpc.bytetrack.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.bytetrack.BufferCapacityResponse getBufferCapacity(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ByteTrackService.
   * <pre>
   * ========================
   * SERVICE
   * ========================
   * </pre>
   */
  public static final class ByteTrackServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ByteTrackServiceFutureStub> {
    private ByteTrackServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ByteTrackServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ByteTrackServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.bytetrack.InitResponse> init(
        knu.app.grpc.bytetrack.InitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.bytetrack.UpdateResponse> update(
        knu.app.grpc.bytetrack.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> close(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.bytetrack.BufferCapacityResponse> getBufferCapacity(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INIT = 0;
  private static final int METHODID_UPDATE = 1;
  private static final int METHODID_CLOSE = 2;
  private static final int METHODID_GET_BUFFER_CAPACITY = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INIT:
          serviceImpl.init((knu.app.grpc.bytetrack.InitRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.InitResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((knu.app.grpc.bytetrack.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.UpdateResponse>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_BUFFER_CAPACITY:
          serviceImpl.getBufferCapacity((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.bytetrack.BufferCapacityResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getInitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.bytetrack.InitRequest,
              knu.app.grpc.bytetrack.InitResponse>(
                service, METHODID_INIT)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.bytetrack.UpdateRequest,
              knu.app.grpc.bytetrack.UpdateResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getCloseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.google.protobuf.Empty>(
                service, METHODID_CLOSE)))
        .addMethod(
          getGetBufferCapacityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              knu.app.grpc.bytetrack.BufferCapacityResponse>(
                service, METHODID_GET_BUFFER_CAPACITY)))
        .build();
  }

  private static abstract class ByteTrackServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ByteTrackServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return knu.app.grpc.bytetrack.ByteTrackProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ByteTrackService");
    }
  }

  private static final class ByteTrackServiceFileDescriptorSupplier
      extends ByteTrackServiceBaseDescriptorSupplier {
    ByteTrackServiceFileDescriptorSupplier() {}
  }

  private static final class ByteTrackServiceMethodDescriptorSupplier
      extends ByteTrackServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ByteTrackServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ByteTrackServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ByteTrackServiceFileDescriptorSupplier())
              .addMethod(getInitMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getCloseMethod())
              .addMethod(getGetBufferCapacityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
