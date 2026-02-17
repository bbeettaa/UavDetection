package knu.app.grpc.deepsort;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * -----------------------------
 * Сервис DeepSORT
 * -----------------------------
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class DeepSortServiceGrpc {

  private DeepSortServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "deepsort.DeepSortService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.deepsort.TrackerConfig,
      knu.app.grpc.deepsort.InitResponse> getInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Init",
      requestType = knu.app.grpc.deepsort.TrackerConfig.class,
      responseType = knu.app.grpc.deepsort.InitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.deepsort.TrackerConfig,
      knu.app.grpc.deepsort.InitResponse> getInitMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.deepsort.TrackerConfig, knu.app.grpc.deepsort.InitResponse> getInitMethod;
    if ((getInitMethod = DeepSortServiceGrpc.getInitMethod) == null) {
      synchronized (DeepSortServiceGrpc.class) {
        if ((getInitMethod = DeepSortServiceGrpc.getInitMethod) == null) {
          DeepSortServiceGrpc.getInitMethod = getInitMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.deepsort.TrackerConfig, knu.app.grpc.deepsort.InitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Init"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.TrackerConfig.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.InitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeepSortServiceMethodDescriptorSupplier("Init"))
              .build();
        }
      }
    }
    return getInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.deepsort.FrameRequest,
      knu.app.grpc.deepsort.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = knu.app.grpc.deepsort.FrameRequest.class,
      responseType = knu.app.grpc.deepsort.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.deepsort.FrameRequest,
      knu.app.grpc.deepsort.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.deepsort.FrameRequest, knu.app.grpc.deepsort.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = DeepSortServiceGrpc.getUpdateMethod) == null) {
      synchronized (DeepSortServiceGrpc.class) {
        if ((getUpdateMethod = DeepSortServiceGrpc.getUpdateMethod) == null) {
          DeepSortServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.deepsort.FrameRequest, knu.app.grpc.deepsort.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.FrameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeepSortServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = knu.app.grpc.deepsort.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest, com.google.protobuf.Empty> getCloseMethod;
    if ((getCloseMethod = DeepSortServiceGrpc.getCloseMethod) == null) {
      synchronized (DeepSortServiceGrpc.class) {
        if ((getCloseMethod = DeepSortServiceGrpc.getCloseMethod) == null) {
          DeepSortServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.deepsort.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DeepSortServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      knu.app.grpc.deepsort.BufferCapacityResponse> getGetBufferCapacityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBufferCapacity",
      requestType = knu.app.grpc.deepsort.SessionRequest.class,
      responseType = knu.app.grpc.deepsort.BufferCapacityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      knu.app.grpc.deepsort.BufferCapacityResponse> getGetBufferCapacityMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest, knu.app.grpc.deepsort.BufferCapacityResponse> getGetBufferCapacityMethod;
    if ((getGetBufferCapacityMethod = DeepSortServiceGrpc.getGetBufferCapacityMethod) == null) {
      synchronized (DeepSortServiceGrpc.class) {
        if ((getGetBufferCapacityMethod = DeepSortServiceGrpc.getGetBufferCapacityMethod) == null) {
          DeepSortServiceGrpc.getGetBufferCapacityMethod = getGetBufferCapacityMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.deepsort.SessionRequest, knu.app.grpc.deepsort.BufferCapacityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBufferCapacity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.BufferCapacityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeepSortServiceMethodDescriptorSupplier("GetBufferCapacity"))
              .build();
        }
      }
    }
    return getGetBufferCapacityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      com.google.protobuf.Empty> getClearMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Clear",
      requestType = knu.app.grpc.deepsort.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest,
      com.google.protobuf.Empty> getClearMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.deepsort.SessionRequest, com.google.protobuf.Empty> getClearMethod;
    if ((getClearMethod = DeepSortServiceGrpc.getClearMethod) == null) {
      synchronized (DeepSortServiceGrpc.class) {
        if ((getClearMethod = DeepSortServiceGrpc.getClearMethod) == null) {
          DeepSortServiceGrpc.getClearMethod = getClearMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.deepsort.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Clear"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.deepsort.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DeepSortServiceMethodDescriptorSupplier("Clear"))
              .build();
        }
      }
    }
    return getClearMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeepSortServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceStub>() {
        @java.lang.Override
        public DeepSortServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeepSortServiceStub(channel, callOptions);
        }
      };
    return DeepSortServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static DeepSortServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceBlockingV2Stub>() {
        @java.lang.Override
        public DeepSortServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeepSortServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return DeepSortServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeepSortServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceBlockingStub>() {
        @java.lang.Override
        public DeepSortServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeepSortServiceBlockingStub(channel, callOptions);
        }
      };
    return DeepSortServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeepSortServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeepSortServiceFutureStub>() {
        @java.lang.Override
        public DeepSortServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeepSortServiceFutureStub(channel, callOptions);
        }
      };
    return DeepSortServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void init(knu.app.grpc.deepsort.TrackerConfig request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.InitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitMethod(), responseObserver);
    }

    /**
     */
    default void update(knu.app.grpc.deepsort.FrameRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void close(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    /**
     */
    default void getBufferCapacity(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBufferCapacityMethod(), responseObserver);
    }

    /**
     */
    default void clear(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DeepSortService.
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public static abstract class DeepSortServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DeepSortServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DeepSortService.
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public static final class DeepSortServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DeepSortServiceStub> {
    private DeepSortServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeepSortServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeepSortServiceStub(channel, callOptions);
    }

    /**
     */
    public void init(knu.app.grpc.deepsort.TrackerConfig request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.InitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(knu.app.grpc.deepsort.FrameRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBufferCapacity(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void clear(knu.app.grpc.deepsort.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DeepSortService.
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public static final class DeepSortServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<DeepSortServiceBlockingV2Stub> {
    private DeepSortServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeepSortServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeepSortServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public knu.app.grpc.deepsort.InitResponse init(knu.app.grpc.deepsort.TrackerConfig request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.deepsort.UpdateResponse update(knu.app.grpc.deepsort.FrameRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(knu.app.grpc.deepsort.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.deepsort.BufferCapacityResponse getBufferCapacity(knu.app.grpc.deepsort.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(knu.app.grpc.deepsort.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service DeepSortService.
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public static final class DeepSortServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DeepSortServiceBlockingStub> {
    private DeepSortServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeepSortServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeepSortServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public knu.app.grpc.deepsort.InitResponse init(knu.app.grpc.deepsort.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.deepsort.UpdateResponse update(knu.app.grpc.deepsort.FrameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.deepsort.BufferCapacityResponse getBufferCapacity(knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DeepSortService.
   * <pre>
   * -----------------------------
   * Сервис DeepSORT
   * -----------------------------
   * </pre>
   */
  public static final class DeepSortServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DeepSortServiceFutureStub> {
    private DeepSortServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeepSortServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeepSortServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.deepsort.InitResponse> init(
        knu.app.grpc.deepsort.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.deepsort.UpdateResponse> update(
        knu.app.grpc.deepsort.FrameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> close(
        knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.deepsort.BufferCapacityResponse> getBufferCapacity(
        knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> clear(
        knu.app.grpc.deepsort.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INIT = 0;
  private static final int METHODID_UPDATE = 1;
  private static final int METHODID_CLOSE = 2;
  private static final int METHODID_GET_BUFFER_CAPACITY = 3;
  private static final int METHODID_CLEAR = 4;

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
          serviceImpl.init((knu.app.grpc.deepsort.TrackerConfig) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.InitResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((knu.app.grpc.deepsort.FrameRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.UpdateResponse>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((knu.app.grpc.deepsort.SessionRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_BUFFER_CAPACITY:
          serviceImpl.getBufferCapacity((knu.app.grpc.deepsort.SessionRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.deepsort.BufferCapacityResponse>) responseObserver);
          break;
        case METHODID_CLEAR:
          serviceImpl.clear((knu.app.grpc.deepsort.SessionRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
              knu.app.grpc.deepsort.TrackerConfig,
              knu.app.grpc.deepsort.InitResponse>(
                service, METHODID_INIT)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.deepsort.FrameRequest,
              knu.app.grpc.deepsort.UpdateResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getCloseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.deepsort.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLOSE)))
        .addMethod(
          getGetBufferCapacityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.deepsort.SessionRequest,
              knu.app.grpc.deepsort.BufferCapacityResponse>(
                service, METHODID_GET_BUFFER_CAPACITY)))
        .addMethod(
          getClearMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.deepsort.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLEAR)))
        .build();
  }

  private static abstract class DeepSortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeepSortServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return knu.app.grpc.deepsort.DeepSortProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeepSortService");
    }
  }

  private static final class DeepSortServiceFileDescriptorSupplier
      extends DeepSortServiceBaseDescriptorSupplier {
    DeepSortServiceFileDescriptorSupplier() {}
  }

  private static final class DeepSortServiceMethodDescriptorSupplier
      extends DeepSortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DeepSortServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DeepSortServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeepSortServiceFileDescriptorSupplier())
              .addMethod(getInitMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getCloseMethod())
              .addMethod(getGetBufferCapacityMethod())
              .addMethod(getClearMethod())
              .build();
        }
      }
    }
    return result;
  }
}
