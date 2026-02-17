package tracker.strongsort;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class StrongSortServiceGrpc {

  private StrongSortServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "tracker.strongsort.StrongSortService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.TrackerConfig,
      tracker.strongsort.StrongsortTracker.InitResponse> getInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Init",
      requestType = tracker.strongsort.StrongsortTracker.TrackerConfig.class,
      responseType = tracker.strongsort.StrongsortTracker.InitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.TrackerConfig,
      tracker.strongsort.StrongsortTracker.InitResponse> getInitMethod() {
    io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.TrackerConfig, tracker.strongsort.StrongsortTracker.InitResponse> getInitMethod;
    if ((getInitMethod = StrongSortServiceGrpc.getInitMethod) == null) {
      synchronized (StrongSortServiceGrpc.class) {
        if ((getInitMethod = StrongSortServiceGrpc.getInitMethod) == null) {
          StrongSortServiceGrpc.getInitMethod = getInitMethod =
              io.grpc.MethodDescriptor.<tracker.strongsort.StrongsortTracker.TrackerConfig, tracker.strongsort.StrongsortTracker.InitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Init"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.TrackerConfig.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.InitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StrongSortServiceMethodDescriptorSupplier("Init"))
              .build();
        }
      }
    }
    return getInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.FrameRequest,
      tracker.strongsort.StrongsortTracker.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = tracker.strongsort.StrongsortTracker.FrameRequest.class,
      responseType = tracker.strongsort.StrongsortTracker.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.FrameRequest,
      tracker.strongsort.StrongsortTracker.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.FrameRequest, tracker.strongsort.StrongsortTracker.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = StrongSortServiceGrpc.getUpdateMethod) == null) {
      synchronized (StrongSortServiceGrpc.class) {
        if ((getUpdateMethod = StrongSortServiceGrpc.getUpdateMethod) == null) {
          StrongSortServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<tracker.strongsort.StrongsortTracker.FrameRequest, tracker.strongsort.StrongsortTracker.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.FrameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StrongSortServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = tracker.strongsort.StrongsortTracker.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod() {
    io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest, com.google.protobuf.Empty> getCloseMethod;
    if ((getCloseMethod = StrongSortServiceGrpc.getCloseMethod) == null) {
      synchronized (StrongSortServiceGrpc.class) {
        if ((getCloseMethod = StrongSortServiceGrpc.getCloseMethod) == null) {
          StrongSortServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<tracker.strongsort.StrongsortTracker.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StrongSortServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      tracker.strongsort.StrongsortTracker.BufferCapacityResponse> getGetBufferCapacityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBufferCapacity",
      requestType = tracker.strongsort.StrongsortTracker.SessionRequest.class,
      responseType = tracker.strongsort.StrongsortTracker.BufferCapacityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      tracker.strongsort.StrongsortTracker.BufferCapacityResponse> getGetBufferCapacityMethod() {
    io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest, tracker.strongsort.StrongsortTracker.BufferCapacityResponse> getGetBufferCapacityMethod;
    if ((getGetBufferCapacityMethod = StrongSortServiceGrpc.getGetBufferCapacityMethod) == null) {
      synchronized (StrongSortServiceGrpc.class) {
        if ((getGetBufferCapacityMethod = StrongSortServiceGrpc.getGetBufferCapacityMethod) == null) {
          StrongSortServiceGrpc.getGetBufferCapacityMethod = getGetBufferCapacityMethod =
              io.grpc.MethodDescriptor.<tracker.strongsort.StrongsortTracker.SessionRequest, tracker.strongsort.StrongsortTracker.BufferCapacityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBufferCapacity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.BufferCapacityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StrongSortServiceMethodDescriptorSupplier("GetBufferCapacity"))
              .build();
        }
      }
    }
    return getGetBufferCapacityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      com.google.protobuf.Empty> getClearMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Clear",
      requestType = tracker.strongsort.StrongsortTracker.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest,
      com.google.protobuf.Empty> getClearMethod() {
    io.grpc.MethodDescriptor<tracker.strongsort.StrongsortTracker.SessionRequest, com.google.protobuf.Empty> getClearMethod;
    if ((getClearMethod = StrongSortServiceGrpc.getClearMethod) == null) {
      synchronized (StrongSortServiceGrpc.class) {
        if ((getClearMethod = StrongSortServiceGrpc.getClearMethod) == null) {
          StrongSortServiceGrpc.getClearMethod = getClearMethod =
              io.grpc.MethodDescriptor.<tracker.strongsort.StrongsortTracker.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Clear"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.strongsort.StrongsortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StrongSortServiceMethodDescriptorSupplier("Clear"))
              .build();
        }
      }
    }
    return getClearMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StrongSortServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceStub>() {
        @java.lang.Override
        public StrongSortServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongSortServiceStub(channel, callOptions);
        }
      };
    return StrongSortServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static StrongSortServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceBlockingV2Stub>() {
        @java.lang.Override
        public StrongSortServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongSortServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return StrongSortServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StrongSortServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceBlockingStub>() {
        @java.lang.Override
        public StrongSortServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongSortServiceBlockingStub(channel, callOptions);
        }
      };
    return StrongSortServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StrongSortServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongSortServiceFutureStub>() {
        @java.lang.Override
        public StrongSortServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongSortServiceFutureStub(channel, callOptions);
        }
      };
    return StrongSortServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void init(tracker.strongsort.StrongsortTracker.TrackerConfig request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.InitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitMethod(), responseObserver);
    }

    /**
     */
    default void update(tracker.strongsort.StrongsortTracker.FrameRequest request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void close(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    /**
     */
    default void getBufferCapacity(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBufferCapacityMethod(), responseObserver);
    }

    /**
     */
    default void clear(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StrongSortService.
   */
  public static abstract class StrongSortServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StrongSortServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StrongSortService.
   */
  public static final class StrongSortServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StrongSortServiceStub> {
    private StrongSortServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongSortServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongSortServiceStub(channel, callOptions);
    }

    /**
     */
    public void init(tracker.strongsort.StrongsortTracker.TrackerConfig request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.InitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(tracker.strongsort.StrongsortTracker.FrameRequest request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBufferCapacity(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void clear(tracker.strongsort.StrongsortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StrongSortService.
   */
  public static final class StrongSortServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<StrongSortServiceBlockingV2Stub> {
    private StrongSortServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongSortServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongSortServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.InitResponse init(tracker.strongsort.StrongsortTracker.TrackerConfig request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.UpdateResponse update(tracker.strongsort.StrongsortTracker.FrameRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(tracker.strongsort.StrongsortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.BufferCapacityResponse getBufferCapacity(tracker.strongsort.StrongsortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(tracker.strongsort.StrongsortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service StrongSortService.
   */
  public static final class StrongSortServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StrongSortServiceBlockingStub> {
    private StrongSortServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongSortServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongSortServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.InitResponse init(tracker.strongsort.StrongsortTracker.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.UpdateResponse update(tracker.strongsort.StrongsortTracker.FrameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(tracker.strongsort.StrongsortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.strongsort.StrongsortTracker.BufferCapacityResponse getBufferCapacity(tracker.strongsort.StrongsortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(tracker.strongsort.StrongsortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StrongSortService.
   */
  public static final class StrongSortServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StrongSortServiceFutureStub> {
    private StrongSortServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongSortServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongSortServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.strongsort.StrongsortTracker.InitResponse> init(
        tracker.strongsort.StrongsortTracker.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.strongsort.StrongsortTracker.UpdateResponse> update(
        tracker.strongsort.StrongsortTracker.FrameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> close(
        tracker.strongsort.StrongsortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.strongsort.StrongsortTracker.BufferCapacityResponse> getBufferCapacity(
        tracker.strongsort.StrongsortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> clear(
        tracker.strongsort.StrongsortTracker.SessionRequest request) {
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
          serviceImpl.init((tracker.strongsort.StrongsortTracker.TrackerConfig) request,
              (io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.InitResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((tracker.strongsort.StrongsortTracker.FrameRequest) request,
              (io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.UpdateResponse>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((tracker.strongsort.StrongsortTracker.SessionRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_BUFFER_CAPACITY:
          serviceImpl.getBufferCapacity((tracker.strongsort.StrongsortTracker.SessionRequest) request,
              (io.grpc.stub.StreamObserver<tracker.strongsort.StrongsortTracker.BufferCapacityResponse>) responseObserver);
          break;
        case METHODID_CLEAR:
          serviceImpl.clear((tracker.strongsort.StrongsortTracker.SessionRequest) request,
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
              tracker.strongsort.StrongsortTracker.TrackerConfig,
              tracker.strongsort.StrongsortTracker.InitResponse>(
                service, METHODID_INIT)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.strongsort.StrongsortTracker.FrameRequest,
              tracker.strongsort.StrongsortTracker.UpdateResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getCloseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.strongsort.StrongsortTracker.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLOSE)))
        .addMethod(
          getGetBufferCapacityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.strongsort.StrongsortTracker.SessionRequest,
              tracker.strongsort.StrongsortTracker.BufferCapacityResponse>(
                service, METHODID_GET_BUFFER_CAPACITY)))
        .addMethod(
          getClearMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.strongsort.StrongsortTracker.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLEAR)))
        .build();
  }

  private static abstract class StrongSortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StrongSortServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tracker.strongsort.StrongsortTracker.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StrongSortService");
    }
  }

  private static final class StrongSortServiceFileDescriptorSupplier
      extends StrongSortServiceBaseDescriptorSupplier {
    StrongSortServiceFileDescriptorSupplier() {}
  }

  private static final class StrongSortServiceMethodDescriptorSupplier
      extends StrongSortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StrongSortServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (StrongSortServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StrongSortServiceFileDescriptorSupplier())
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
