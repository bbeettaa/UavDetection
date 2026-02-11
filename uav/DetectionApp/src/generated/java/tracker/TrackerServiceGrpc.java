package tracker;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class TrackerServiceGrpc {

  private TrackerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "tracker.TrackerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.TrackerParams,
      tracker.BytetrackObjecttracker.InitResponse> getInitTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InitTracker",
      requestType = tracker.BytetrackObjecttracker.TrackerParams.class,
      responseType = tracker.BytetrackObjecttracker.InitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.TrackerParams,
      tracker.BytetrackObjecttracker.InitResponse> getInitTrackerMethod() {
    io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.TrackerParams, tracker.BytetrackObjecttracker.InitResponse> getInitTrackerMethod;
    if ((getInitTrackerMethod = TrackerServiceGrpc.getInitTrackerMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getInitTrackerMethod = TrackerServiceGrpc.getInitTrackerMethod) == null) {
          TrackerServiceGrpc.getInitTrackerMethod = getInitTrackerMethod =
              io.grpc.MethodDescriptor.<tracker.BytetrackObjecttracker.TrackerParams, tracker.BytetrackObjecttracker.InitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InitTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.BytetrackObjecttracker.TrackerParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.BytetrackObjecttracker.InitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("InitTracker"))
              .build();
        }
      }
    }
    return getInitTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.FrameDetections,
      tracker.BytetrackObjecttracker.TracksResponse> getUpdateTracksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTracks",
      requestType = tracker.BytetrackObjecttracker.FrameDetections.class,
      responseType = tracker.BytetrackObjecttracker.TracksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.FrameDetections,
      tracker.BytetrackObjecttracker.TracksResponse> getUpdateTracksMethod() {
    io.grpc.MethodDescriptor<tracker.BytetrackObjecttracker.FrameDetections, tracker.BytetrackObjecttracker.TracksResponse> getUpdateTracksMethod;
    if ((getUpdateTracksMethod = TrackerServiceGrpc.getUpdateTracksMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getUpdateTracksMethod = TrackerServiceGrpc.getUpdateTracksMethod) == null) {
          TrackerServiceGrpc.getUpdateTracksMethod = getUpdateTracksMethod =
              io.grpc.MethodDescriptor.<tracker.BytetrackObjecttracker.FrameDetections, tracker.BytetrackObjecttracker.TracksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTracks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.BytetrackObjecttracker.FrameDetections.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tracker.BytetrackObjecttracker.TracksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("UpdateTracks"))
              .build();
        }
      }
    }
    return getUpdateTracksMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceStub>() {
        @java.lang.Override
        public TrackerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceStub(channel, callOptions);
        }
      };
    return TrackerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static TrackerServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingV2Stub>() {
        @java.lang.Override
        public TrackerServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return TrackerServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingStub>() {
        @java.lang.Override
        public TrackerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceBlockingStub(channel, callOptions);
        }
      };
    return TrackerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceFutureStub>() {
        @java.lang.Override
        public TrackerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceFutureStub(channel, callOptions);
        }
      };
    return TrackerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * новый метод
     * </pre>
     */
    default void initTracker(tracker.BytetrackObjecttracker.TrackerParams request,
        io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.InitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitTrackerMethod(), responseObserver);
    }

    /**
     */
    default void updateTracks(tracker.BytetrackObjecttracker.FrameDetections request,
        io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.TracksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateTracksMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TrackerService.
   */
  public static abstract class TrackerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TrackerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TrackerService.
   */
  public static final class TrackerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TrackerServiceStub> {
    private TrackerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * новый метод
     * </pre>
     */
    public void initTracker(tracker.BytetrackObjecttracker.TrackerParams request,
        io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.InitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTracks(tracker.BytetrackObjecttracker.FrameDetections request,
        io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.TracksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateTracksMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TrackerService.
   */
  public static final class TrackerServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<TrackerServiceBlockingV2Stub> {
    private TrackerServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * новый метод
     * </pre>
     */
    public tracker.BytetrackObjecttracker.InitResponse initTracker(tracker.BytetrackObjecttracker.TrackerParams request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getInitTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.BytetrackObjecttracker.TracksResponse updateTracks(tracker.BytetrackObjecttracker.FrameDetections request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateTracksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service TrackerService.
   */
  public static final class TrackerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TrackerServiceBlockingStub> {
    private TrackerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * новый метод
     * </pre>
     */
    public tracker.BytetrackObjecttracker.InitResponse initTracker(tracker.BytetrackObjecttracker.TrackerParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public tracker.BytetrackObjecttracker.TracksResponse updateTracks(tracker.BytetrackObjecttracker.FrameDetections request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateTracksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TrackerService.
   */
  public static final class TrackerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TrackerServiceFutureStub> {
    private TrackerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * новый метод
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.BytetrackObjecttracker.InitResponse> initTracker(
        tracker.BytetrackObjecttracker.TrackerParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitTrackerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tracker.BytetrackObjecttracker.TracksResponse> updateTracks(
        tracker.BytetrackObjecttracker.FrameDetections request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateTracksMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INIT_TRACKER = 0;
  private static final int METHODID_UPDATE_TRACKS = 1;

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
        case METHODID_INIT_TRACKER:
          serviceImpl.initTracker((tracker.BytetrackObjecttracker.TrackerParams) request,
              (io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.InitResponse>) responseObserver);
          break;
        case METHODID_UPDATE_TRACKS:
          serviceImpl.updateTracks((tracker.BytetrackObjecttracker.FrameDetections) request,
              (io.grpc.stub.StreamObserver<tracker.BytetrackObjecttracker.TracksResponse>) responseObserver);
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
          getInitTrackerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.BytetrackObjecttracker.TrackerParams,
              tracker.BytetrackObjecttracker.InitResponse>(
                service, METHODID_INIT_TRACKER)))
        .addMethod(
          getUpdateTracksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tracker.BytetrackObjecttracker.FrameDetections,
              tracker.BytetrackObjecttracker.TracksResponse>(
                service, METHODID_UPDATE_TRACKS)))
        .build();
  }

  private static abstract class TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrackerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tracker.BytetrackObjecttracker.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TrackerService");
    }
  }

  private static final class TrackerServiceFileDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier {
    TrackerServiceFileDescriptorSupplier() {}
  }

  private static final class TrackerServiceMethodDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TrackerServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TrackerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackerServiceFileDescriptorSupplier())
              .addMethod(getInitTrackerMethod())
              .addMethod(getUpdateTracksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
