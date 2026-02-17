package tracker.sort;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class SortServiceGrpc {

  private SortServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "tracker.sort.SortService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tracker.sort.SortTracker.TrackerConfig,
      tracker.sort.SortTracker.InitResponse> getInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Init",
      requestType = tracker.sort.SortTracker.TrackerConfig.class,
      responseType = tracker.sort.SortTracker.InitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.sort.SortTracker.TrackerConfig,
      tracker.sort.SortTracker.InitResponse> getInitMethod() {
    io.grpc.MethodDescriptor<tracker.sort.SortTracker.TrackerConfig, tracker.sort.SortTracker.InitResponse> getInitMethod;
    if ((getInitMethod = SortServiceGrpc.getInitMethod) == null) {
      synchronized (SortServiceGrpc.class) {
        if ((getInitMethod = SortServiceGrpc.getInitMethod) == null) {
          SortServiceGrpc.getInitMethod = getInitMethod =
              io.grpc.MethodDescriptor.<tracker.sort.SortTracker.TrackerConfig, tracker.sort.SortTracker.InitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Init"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.TrackerConfig.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.InitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SortServiceMethodDescriptorSupplier("Init"))
              .build();
        }
      }
    }
    return getInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.sort.SortTracker.FrameRequest,
      tracker.sort.SortTracker.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = tracker.sort.SortTracker.FrameRequest.class,
      responseType = tracker.sort.SortTracker.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.sort.SortTracker.FrameRequest,
      tracker.sort.SortTracker.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<tracker.sort.SortTracker.FrameRequest, tracker.sort.SortTracker.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = SortServiceGrpc.getUpdateMethod) == null) {
      synchronized (SortServiceGrpc.class) {
        if ((getUpdateMethod = SortServiceGrpc.getUpdateMethod) == null) {
          SortServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<tracker.sort.SortTracker.FrameRequest, tracker.sort.SortTracker.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.FrameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SortServiceMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = tracker.sort.SortTracker.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      com.google.protobuf.Empty> getCloseMethod() {
    io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest, com.google.protobuf.Empty> getCloseMethod;
    if ((getCloseMethod = SortServiceGrpc.getCloseMethod) == null) {
      synchronized (SortServiceGrpc.class) {
        if ((getCloseMethod = SortServiceGrpc.getCloseMethod) == null) {
          SortServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<tracker.sort.SortTracker.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SortServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      tracker.sort.SortTracker.BufferCapacityResponse> getGetBufferCapacityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBufferCapacity",
      requestType = tracker.sort.SortTracker.SessionRequest.class,
      responseType = tracker.sort.SortTracker.BufferCapacityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      tracker.sort.SortTracker.BufferCapacityResponse> getGetBufferCapacityMethod() {
    io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest, tracker.sort.SortTracker.BufferCapacityResponse> getGetBufferCapacityMethod;
    if ((getGetBufferCapacityMethod = SortServiceGrpc.getGetBufferCapacityMethod) == null) {
      synchronized (SortServiceGrpc.class) {
        if ((getGetBufferCapacityMethod = SortServiceGrpc.getGetBufferCapacityMethod) == null) {
          SortServiceGrpc.getGetBufferCapacityMethod = getGetBufferCapacityMethod =
              io.grpc.MethodDescriptor.<tracker.sort.SortTracker.SessionRequest, tracker.sort.SortTracker.BufferCapacityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBufferCapacity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.BufferCapacityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SortServiceMethodDescriptorSupplier("GetBufferCapacity"))
              .build();
        }
      }
    }
    return getGetBufferCapacityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      com.google.protobuf.Empty> getClearMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Clear",
      requestType = tracker.sort.SortTracker.SessionRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest,
      com.google.protobuf.Empty> getClearMethod() {
    io.grpc.MethodDescriptor<tracker.sort.SortTracker.SessionRequest, com.google.protobuf.Empty> getClearMethod;
    if ((getClearMethod = SortServiceGrpc.getClearMethod) == null) {
      synchronized (SortServiceGrpc.class) {
        if ((getClearMethod = SortServiceGrpc.getClearMethod) == null) {
          SortServiceGrpc.getClearMethod = getClearMethod =
              io.grpc.MethodDescriptor.<tracker.sort.SortTracker.SessionRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Clear"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.sort.SortTracker.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SortServiceMethodDescriptorSupplier("Clear"))
              .build();
        }
      }
    }
    return getClearMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SortServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SortServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SortServiceStub>() {
        @java.lang.Override
        public SortServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SortServiceStub(channel, callOptions);
        }
      };
    return SortServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SortServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SortServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SortServiceBlockingV2Stub>() {
        @java.lang.Override
        public SortServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SortServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return SortServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SortServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SortServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SortServiceBlockingStub>() {
        @java.lang.Override
        public SortServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SortServiceBlockingStub(channel, callOptions);
        }
      };
    return SortServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SortServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SortServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SortServiceFutureStub>() {
        @java.lang.Override
        public SortServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SortServiceFutureStub(channel, callOptions);
        }
      };
    return SortServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void init(tracker.sort.SortTracker.TrackerConfig request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.InitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitMethod(), responseObserver);
    }

    /**
     */
    default void update(tracker.sort.SortTracker.FrameRequest request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void close(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    /**
     */
    default void getBufferCapacity(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBufferCapacityMethod(), responseObserver);
    }

    /**
     */
    default void clear(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SortService.
   */
  public static abstract class SortServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SortServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SortService.
   */
  public static final class SortServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SortServiceStub> {
    private SortServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SortServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SortServiceStub(channel, callOptions);
    }

    /**
     */
    public void init(tracker.sort.SortTracker.TrackerConfig request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.InitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(tracker.sort.SortTracker.FrameRequest request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBufferCapacity(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<tracker.sort.SortTracker.BufferCapacityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void clear(tracker.sort.SortTracker.SessionRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SortService.
   */
  public static final class SortServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SortServiceBlockingV2Stub> {
    private SortServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SortServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SortServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public tracker.sort.SortTracker.InitResponse init(tracker.sort.SortTracker.TrackerConfig request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.sort.SortTracker.UpdateResponse update(tracker.sort.SortTracker.FrameRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(tracker.sort.SortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.sort.SortTracker.BufferCapacityResponse getBufferCapacity(tracker.sort.SortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(tracker.sort.SortTracker.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service SortService.
   */
  public static final class SortServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SortServiceBlockingStub> {
    private SortServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SortServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SortServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public tracker.sort.SortTracker.InitResponse init(tracker.sort.SortTracker.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.sort.SortTracker.UpdateResponse update(tracker.sort.SortTracker.FrameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty close(tracker.sort.SortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.sort.SortTracker.BufferCapacityResponse getBufferCapacity(tracker.sort.SortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBufferCapacityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty clear(tracker.sort.SortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SortService.
   */
  public static final class SortServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SortServiceFutureStub> {
    private SortServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SortServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SortServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.sort.SortTracker.InitResponse> init(
        tracker.sort.SortTracker.TrackerConfig request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.sort.SortTracker.UpdateResponse> update(
        tracker.sort.SortTracker.FrameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> close(
        tracker.sort.SortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.sort.SortTracker.BufferCapacityResponse> getBufferCapacity(
        tracker.sort.SortTracker.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBufferCapacityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> clear(
        tracker.sort.SortTracker.SessionRequest request) {
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
          serviceImpl.init((tracker.sort.SortTracker.TrackerConfig) request,
              (io.grpc.stub.StreamObserver<tracker.sort.SortTracker.InitResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((tracker.sort.SortTracker.FrameRequest) request,
              (io.grpc.stub.StreamObserver<tracker.sort.SortTracker.UpdateResponse>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((tracker.sort.SortTracker.SessionRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_BUFFER_CAPACITY:
          serviceImpl.getBufferCapacity((tracker.sort.SortTracker.SessionRequest) request,
              (io.grpc.stub.StreamObserver<tracker.sort.SortTracker.BufferCapacityResponse>) responseObserver);
          break;
        case METHODID_CLEAR:
          serviceImpl.clear((tracker.sort.SortTracker.SessionRequest) request,
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
              tracker.sort.SortTracker.TrackerConfig,
              tracker.sort.SortTracker.InitResponse>(
                service, METHODID_INIT)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.sort.SortTracker.FrameRequest,
              tracker.sort.SortTracker.UpdateResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getCloseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.sort.SortTracker.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLOSE)))
        .addMethod(
          getGetBufferCapacityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.sort.SortTracker.SessionRequest,
              tracker.sort.SortTracker.BufferCapacityResponse>(
                service, METHODID_GET_BUFFER_CAPACITY)))
        .addMethod(
          getClearMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.sort.SortTracker.SessionRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CLEAR)))
        .build();
  }

  private static abstract class SortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SortServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tracker.sort.SortTracker.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SortService");
    }
  }

  private static final class SortServiceFileDescriptorSupplier
      extends SortServiceBaseDescriptorSupplier {
    SortServiceFileDescriptorSupplier() {}
  }

  private static final class SortServiceMethodDescriptorSupplier
      extends SortServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SortServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SortServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SortServiceFileDescriptorSupplier())
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
