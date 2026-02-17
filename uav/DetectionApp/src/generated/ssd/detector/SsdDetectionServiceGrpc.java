package ssd.detector;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class SsdDetectionServiceGrpc {

  private SsdDetectionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ssd.detector.SsdDetectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SsdConfig,
      com.google.protobuf.Empty> getSetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetConfig",
      requestType = ssd.detector.SsdDetector.SsdConfig.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SsdConfig,
      com.google.protobuf.Empty> getSetConfigMethod() {
    io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SsdConfig, com.google.protobuf.Empty> getSetConfigMethod;
    if ((getSetConfigMethod = SsdDetectionServiceGrpc.getSetConfigMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getSetConfigMethod = SsdDetectionServiceGrpc.getSetConfigMethod) == null) {
          SsdDetectionServiceGrpc.getSetConfigMethod = getSetConfigMethod =
              io.grpc.MethodDescriptor.<ssd.detector.SsdDetector.SsdConfig, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.SsdConfig.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("SetConfig"))
              .build();
        }
      }
    }
    return getSetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      ssd.detector.SsdDetector.GetConfigResponse> getGetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConfig",
      requestType = com.google.protobuf.Empty.class,
      responseType = ssd.detector.SsdDetector.GetConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      ssd.detector.SsdDetector.GetConfigResponse> getGetConfigMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, ssd.detector.SsdDetector.GetConfigResponse> getGetConfigMethod;
    if ((getGetConfigMethod = SsdDetectionServiceGrpc.getGetConfigMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getGetConfigMethod = SsdDetectionServiceGrpc.getGetConfigMethod) == null) {
          SsdDetectionServiceGrpc.getGetConfigMethod = getGetConfigMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, ssd.detector.SsdDetector.GetConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.GetConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("GetConfig"))
              .build();
        }
      }
    }
    return getGetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getStartStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartStreaming",
      requestType = ssd.detector.SsdDetector.SessionRequest.class,
      responseType = ssd.detector.SsdDetector.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getStartStreamingMethod() {
    io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus> getStartStreamingMethod;
    if ((getStartStreamingMethod = SsdDetectionServiceGrpc.getStartStreamingMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getStartStreamingMethod = SsdDetectionServiceGrpc.getStartStreamingMethod) == null) {
          SsdDetectionServiceGrpc.getStartStreamingMethod = getStartStreamingMethod =
              io.grpc.MethodDescriptor.<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("StartStreaming"))
              .build();
        }
      }
    }
    return getStartStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getStopStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StopStreaming",
      requestType = ssd.detector.SsdDetector.SessionRequest.class,
      responseType = ssd.detector.SsdDetector.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getStopStreamingMethod() {
    io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus> getStopStreamingMethod;
    if ((getStopStreamingMethod = SsdDetectionServiceGrpc.getStopStreamingMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getStopStreamingMethod = SsdDetectionServiceGrpc.getStopStreamingMethod) == null) {
          SsdDetectionServiceGrpc.getStopStreamingMethod = getStopStreamingMethod =
              io.grpc.MethodDescriptor.<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StopStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("StopStreaming"))
              .build();
        }
      }
    }
    return getStopStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getRestartConnectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RestartConnection",
      requestType = ssd.detector.SsdDetector.SessionRequest.class,
      responseType = ssd.detector.SsdDetector.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest,
      ssd.detector.SsdDetector.StreamStatus> getRestartConnectionMethod() {
    io.grpc.MethodDescriptor<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus> getRestartConnectionMethod;
    if ((getRestartConnectionMethod = SsdDetectionServiceGrpc.getRestartConnectionMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getRestartConnectionMethod = SsdDetectionServiceGrpc.getRestartConnectionMethod) == null) {
          SsdDetectionServiceGrpc.getRestartConnectionMethod = getRestartConnectionMethod =
              io.grpc.MethodDescriptor.<ssd.detector.SsdDetector.SessionRequest, ssd.detector.SsdDetector.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RestartConnection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.SessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("RestartConnection"))
              .build();
        }
      }
    }
    return getRestartConnectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ssd.detector.SsdDetector.FrameRequest,
      ssd.detector.SsdDetector.TrackingResult> getStreamDetectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamDetect",
      requestType = ssd.detector.SsdDetector.FrameRequest.class,
      responseType = ssd.detector.SsdDetector.TrackingResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ssd.detector.SsdDetector.FrameRequest,
      ssd.detector.SsdDetector.TrackingResult> getStreamDetectMethod() {
    io.grpc.MethodDescriptor<ssd.detector.SsdDetector.FrameRequest, ssd.detector.SsdDetector.TrackingResult> getStreamDetectMethod;
    if ((getStreamDetectMethod = SsdDetectionServiceGrpc.getStreamDetectMethod) == null) {
      synchronized (SsdDetectionServiceGrpc.class) {
        if ((getStreamDetectMethod = SsdDetectionServiceGrpc.getStreamDetectMethod) == null) {
          SsdDetectionServiceGrpc.getStreamDetectMethod = getStreamDetectMethod =
              io.grpc.MethodDescriptor.<ssd.detector.SsdDetector.FrameRequest, ssd.detector.SsdDetector.TrackingResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamDetect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.FrameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ssd.detector.SsdDetector.TrackingResult.getDefaultInstance()))
              .setSchemaDescriptor(new SsdDetectionServiceMethodDescriptorSupplier("StreamDetect"))
              .build();
        }
      }
    }
    return getStreamDetectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SsdDetectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceStub>() {
        @java.lang.Override
        public SsdDetectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SsdDetectionServiceStub(channel, callOptions);
        }
      };
    return SsdDetectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SsdDetectionServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceBlockingV2Stub>() {
        @java.lang.Override
        public SsdDetectionServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SsdDetectionServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return SsdDetectionServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SsdDetectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceBlockingStub>() {
        @java.lang.Override
        public SsdDetectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SsdDetectionServiceBlockingStub(channel, callOptions);
        }
      };
    return SsdDetectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SsdDetectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SsdDetectionServiceFutureStub>() {
        @java.lang.Override
        public SsdDetectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SsdDetectionServiceFutureStub(channel, callOptions);
        }
      };
    return SsdDetectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void setConfig(ssd.detector.SsdDetector.SsdConfig request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetConfigMethod(), responseObserver);
    }

    /**
     */
    default void getConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.GetConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigMethod(), responseObserver);
    }

    /**
     */
    default void startStreaming(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartStreamingMethod(), responseObserver);
    }

    /**
     */
    default void stopStreaming(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStopStreamingMethod(), responseObserver);
    }

    /**
     */
    default void restartConnection(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRestartConnectionMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.FrameRequest> streamDetect(
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.TrackingResult> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamDetectMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SsdDetectionService.
   */
  public static abstract class SsdDetectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SsdDetectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SsdDetectionService.
   */
  public static final class SsdDetectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SsdDetectionServiceStub> {
    private SsdDetectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SsdDetectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SsdDetectionServiceStub(channel, callOptions);
    }

    /**
     */
    public void setConfig(ssd.detector.SsdDetector.SsdConfig request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.GetConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startStreaming(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopStreaming(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStopStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void restartConnection(ssd.detector.SsdDetector.SessionRequest request,
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRestartConnectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.FrameRequest> streamDetect(
        io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.TrackingResult> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getStreamDetectMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SsdDetectionService.
   */
  public static final class SsdDetectionServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SsdDetectionServiceBlockingV2Stub> {
    private SsdDetectionServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SsdDetectionServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SsdDetectionServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty setConfig(ssd.detector.SsdDetector.SsdConfig request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.GetConfigResponse getConfig(com.google.protobuf.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus startStreaming(ssd.detector.SsdDetector.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getStartStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus stopStreaming(ssd.detector.SsdDetector.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getStopStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus restartConnection(ssd.detector.SsdDetector.SessionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getRestartConnectionMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<ssd.detector.SsdDetector.FrameRequest, ssd.detector.SsdDetector.TrackingResult>
        streamDetect() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getStreamDetectMethod(), getCallOptions());
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service SsdDetectionService.
   */
  public static final class SsdDetectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SsdDetectionServiceBlockingStub> {
    private SsdDetectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SsdDetectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SsdDetectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty setConfig(ssd.detector.SsdDetector.SsdConfig request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.GetConfigResponse getConfig(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus startStreaming(ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus stopStreaming(ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public ssd.detector.SsdDetector.StreamStatus restartConnection(ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRestartConnectionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SsdDetectionService.
   */
  public static final class SsdDetectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SsdDetectionServiceFutureStub> {
    private SsdDetectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SsdDetectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SsdDetectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setConfig(
        ssd.detector.SsdDetector.SsdConfig request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ssd.detector.SsdDetector.GetConfigResponse> getConfig(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ssd.detector.SsdDetector.StreamStatus> startStreaming(
        ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartStreamingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ssd.detector.SsdDetector.StreamStatus> stopStreaming(
        ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStopStreamingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ssd.detector.SsdDetector.StreamStatus> restartConnection(
        ssd.detector.SsdDetector.SessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRestartConnectionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_CONFIG = 0;
  private static final int METHODID_GET_CONFIG = 1;
  private static final int METHODID_START_STREAMING = 2;
  private static final int METHODID_STOP_STREAMING = 3;
  private static final int METHODID_RESTART_CONNECTION = 4;
  private static final int METHODID_STREAM_DETECT = 5;

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
        case METHODID_SET_CONFIG:
          serviceImpl.setConfig((ssd.detector.SsdDetector.SsdConfig) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_CONFIG:
          serviceImpl.getConfig((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.GetConfigResponse>) responseObserver);
          break;
        case METHODID_START_STREAMING:
          serviceImpl.startStreaming((ssd.detector.SsdDetector.SessionRequest) request,
              (io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus>) responseObserver);
          break;
        case METHODID_STOP_STREAMING:
          serviceImpl.stopStreaming((ssd.detector.SsdDetector.SessionRequest) request,
              (io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus>) responseObserver);
          break;
        case METHODID_RESTART_CONNECTION:
          serviceImpl.restartConnection((ssd.detector.SsdDetector.SessionRequest) request,
              (io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.StreamStatus>) responseObserver);
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
        case METHODID_STREAM_DETECT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamDetect(
              (io.grpc.stub.StreamObserver<ssd.detector.SsdDetector.TrackingResult>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSetConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ssd.detector.SsdDetector.SsdConfig,
              com.google.protobuf.Empty>(
                service, METHODID_SET_CONFIG)))
        .addMethod(
          getGetConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              ssd.detector.SsdDetector.GetConfigResponse>(
                service, METHODID_GET_CONFIG)))
        .addMethod(
          getStartStreamingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ssd.detector.SsdDetector.SessionRequest,
              ssd.detector.SsdDetector.StreamStatus>(
                service, METHODID_START_STREAMING)))
        .addMethod(
          getStopStreamingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ssd.detector.SsdDetector.SessionRequest,
              ssd.detector.SsdDetector.StreamStatus>(
                service, METHODID_STOP_STREAMING)))
        .addMethod(
          getRestartConnectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ssd.detector.SsdDetector.SessionRequest,
              ssd.detector.SsdDetector.StreamStatus>(
                service, METHODID_RESTART_CONNECTION)))
        .addMethod(
          getStreamDetectMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              ssd.detector.SsdDetector.FrameRequest,
              ssd.detector.SsdDetector.TrackingResult>(
                service, METHODID_STREAM_DETECT)))
        .build();
  }

  private static abstract class SsdDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SsdDetectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ssd.detector.SsdDetector.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SsdDetectionService");
    }
  }

  private static final class SsdDetectionServiceFileDescriptorSupplier
      extends SsdDetectionServiceBaseDescriptorSupplier {
    SsdDetectionServiceFileDescriptorSupplier() {}
  }

  private static final class SsdDetectionServiceMethodDescriptorSupplier
      extends SsdDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SsdDetectionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SsdDetectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SsdDetectionServiceFileDescriptorSupplier())
              .addMethod(getSetConfigMethod())
              .addMethod(getGetConfigMethod())
              .addMethod(getStartStreamingMethod())
              .addMethod(getStopStreamingMethod())
              .addMethod(getRestartConnectionMethod())
              .addMethod(getStreamDetectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
