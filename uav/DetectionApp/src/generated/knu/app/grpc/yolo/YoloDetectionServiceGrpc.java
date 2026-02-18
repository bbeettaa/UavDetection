package knu.app.grpc.yolo;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class YoloDetectionServiceGrpc {

  private YoloDetectionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "yolo.YoloDetectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.SetConfigRequest,
      com.google.protobuf.Empty> getSetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetConfig",
      requestType = knu.app.grpc.yolo.SetConfigRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.SetConfigRequest,
      com.google.protobuf.Empty> getSetConfigMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.SetConfigRequest, com.google.protobuf.Empty> getSetConfigMethod;
    if ((getSetConfigMethod = YoloDetectionServiceGrpc.getSetConfigMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getSetConfigMethod = YoloDetectionServiceGrpc.getSetConfigMethod) == null) {
          YoloDetectionServiceGrpc.getSetConfigMethod = getSetConfigMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.SetConfigRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.SetConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("SetConfig"))
              .build();
        }
      }
    }
    return getSetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.yolo.GetConfigResponse> getGetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConfig",
      requestType = com.google.protobuf.Empty.class,
      responseType = knu.app.grpc.yolo.GetConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.yolo.GetConfigResponse> getGetConfigMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, knu.app.grpc.yolo.GetConfigResponse> getGetConfigMethod;
    if ((getGetConfigMethod = YoloDetectionServiceGrpc.getGetConfigMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getGetConfigMethod = YoloDetectionServiceGrpc.getGetConfigMethod) == null) {
          YoloDetectionServiceGrpc.getGetConfigMethod = getGetConfigMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, knu.app.grpc.yolo.GetConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.GetConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("GetConfig"))
              .build();
        }
      }
    }
    return getGetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.StartStreamingRequest,
      knu.app.grpc.yolo.StreamStatus> getStartStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartStreaming",
      requestType = knu.app.grpc.yolo.StartStreamingRequest.class,
      responseType = knu.app.grpc.yolo.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.StartStreamingRequest,
      knu.app.grpc.yolo.StreamStatus> getStartStreamingMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.StartStreamingRequest, knu.app.grpc.yolo.StreamStatus> getStartStreamingMethod;
    if ((getStartStreamingMethod = YoloDetectionServiceGrpc.getStartStreamingMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getStartStreamingMethod = YoloDetectionServiceGrpc.getStartStreamingMethod) == null) {
          YoloDetectionServiceGrpc.getStartStreamingMethod = getStartStreamingMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.StartStreamingRequest, knu.app.grpc.yolo.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.StartStreamingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("StartStreaming"))
              .build();
        }
      }
    }
    return getStartStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.StopStreamingRequest,
      knu.app.grpc.yolo.StreamStatus> getStopStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StopStreaming",
      requestType = knu.app.grpc.yolo.StopStreamingRequest.class,
      responseType = knu.app.grpc.yolo.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.StopStreamingRequest,
      knu.app.grpc.yolo.StreamStatus> getStopStreamingMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.StopStreamingRequest, knu.app.grpc.yolo.StreamStatus> getStopStreamingMethod;
    if ((getStopStreamingMethod = YoloDetectionServiceGrpc.getStopStreamingMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getStopStreamingMethod = YoloDetectionServiceGrpc.getStopStreamingMethod) == null) {
          YoloDetectionServiceGrpc.getStopStreamingMethod = getStopStreamingMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.StopStreamingRequest, knu.app.grpc.yolo.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StopStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.StopStreamingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("StopStreaming"))
              .build();
        }
      }
    }
    return getStopStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.RestartConnectionRequest,
      knu.app.grpc.yolo.StreamStatus> getRestartConnectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RestartConnection",
      requestType = knu.app.grpc.yolo.RestartConnectionRequest.class,
      responseType = knu.app.grpc.yolo.StreamStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.RestartConnectionRequest,
      knu.app.grpc.yolo.StreamStatus> getRestartConnectionMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.RestartConnectionRequest, knu.app.grpc.yolo.StreamStatus> getRestartConnectionMethod;
    if ((getRestartConnectionMethod = YoloDetectionServiceGrpc.getRestartConnectionMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getRestartConnectionMethod = YoloDetectionServiceGrpc.getRestartConnectionMethod) == null) {
          YoloDetectionServiceGrpc.getRestartConnectionMethod = getRestartConnectionMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.RestartConnectionRequest, knu.app.grpc.yolo.StreamStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RestartConnection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.RestartConnectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.StreamStatus.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("RestartConnection"))
              .build();
        }
      }
    }
    return getRestartConnectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame,
      knu.app.grpc.yolo.TrackingResult> getStreamTrackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamTrack",
      requestType = knu.app.grpc.yolo.ImageFrame.class,
      responseType = knu.app.grpc.yolo.TrackingResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame,
      knu.app.grpc.yolo.TrackingResult> getStreamTrackMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame, knu.app.grpc.yolo.TrackingResult> getStreamTrackMethod;
    if ((getStreamTrackMethod = YoloDetectionServiceGrpc.getStreamTrackMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getStreamTrackMethod = YoloDetectionServiceGrpc.getStreamTrackMethod) == null) {
          YoloDetectionServiceGrpc.getStreamTrackMethod = getStreamTrackMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.ImageFrame, knu.app.grpc.yolo.TrackingResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamTrack"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.ImageFrame.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.TrackingResult.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("StreamTrack"))
              .build();
        }
      }
    }
    return getStreamTrackMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame,
      knu.app.grpc.yolo.TrackingResult> getDetectSingleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DetectSingle",
      requestType = knu.app.grpc.yolo.ImageFrame.class,
      responseType = knu.app.grpc.yolo.TrackingResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame,
      knu.app.grpc.yolo.TrackingResult> getDetectSingleMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.yolo.ImageFrame, knu.app.grpc.yolo.TrackingResult> getDetectSingleMethod;
    if ((getDetectSingleMethod = YoloDetectionServiceGrpc.getDetectSingleMethod) == null) {
      synchronized (YoloDetectionServiceGrpc.class) {
        if ((getDetectSingleMethod = YoloDetectionServiceGrpc.getDetectSingleMethod) == null) {
          YoloDetectionServiceGrpc.getDetectSingleMethod = getDetectSingleMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.yolo.ImageFrame, knu.app.grpc.yolo.TrackingResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DetectSingle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.ImageFrame.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.yolo.TrackingResult.getDefaultInstance()))
              .setSchemaDescriptor(new YoloDetectionServiceMethodDescriptorSupplier("DetectSingle"))
              .build();
        }
      }
    }
    return getDetectSingleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static YoloDetectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceStub>() {
        @java.lang.Override
        public YoloDetectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloDetectionServiceStub(channel, callOptions);
        }
      };
    return YoloDetectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static YoloDetectionServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceBlockingV2Stub>() {
        @java.lang.Override
        public YoloDetectionServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloDetectionServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return YoloDetectionServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static YoloDetectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceBlockingStub>() {
        @java.lang.Override
        public YoloDetectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloDetectionServiceBlockingStub(channel, callOptions);
        }
      };
    return YoloDetectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static YoloDetectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloDetectionServiceFutureStub>() {
        @java.lang.Override
        public YoloDetectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloDetectionServiceFutureStub(channel, callOptions);
        }
      };
    return YoloDetectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * --- CONFIG MANAGEMENT ---
     * </pre>
     */
    default void setConfig(knu.app.grpc.yolo.SetConfigRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetConfigMethod(), responseObserver);
    }

    /**
     */
    default void getConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.GetConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * --- STREAM CONTROL ---
     * </pre>
     */
    default void startStreaming(knu.app.grpc.yolo.StartStreamingRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartStreamingMethod(), responseObserver);
    }

    /**
     */
    default void stopStreaming(knu.app.grpc.yolo.StopStreamingRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStopStreamingMethod(), responseObserver);
    }

    /**
     */
    default void restartConnection(knu.app.grpc.yolo.RestartConnectionRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRestartConnectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * --- IMAGE STREAM ---
     * </pre>
     */
    default io.grpc.stub.StreamObserver<knu.app.grpc.yolo.ImageFrame> streamTrack(
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamTrackMethod(), responseObserver);
    }

    /**
     */
    default void detectSingle(knu.app.grpc.yolo.ImageFrame request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDetectSingleMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service YoloDetectionService.
   */
  public static abstract class YoloDetectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return YoloDetectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service YoloDetectionService.
   */
  public static final class YoloDetectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<YoloDetectionServiceStub> {
    private YoloDetectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloDetectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloDetectionServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- CONFIG MANAGEMENT ---
     * </pre>
     */
    public void setConfig(knu.app.grpc.yolo.SetConfigRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.GetConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * --- STREAM CONTROL ---
     * </pre>
     */
    public void startStreaming(knu.app.grpc.yolo.StartStreamingRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopStreaming(knu.app.grpc.yolo.StopStreamingRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStopStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void restartConnection(knu.app.grpc.yolo.RestartConnectionRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRestartConnectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * --- IMAGE STREAM ---
     * </pre>
     */
    public io.grpc.stub.StreamObserver<knu.app.grpc.yolo.ImageFrame> streamTrack(
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getStreamTrackMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void detectSingle(knu.app.grpc.yolo.ImageFrame request,
        io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDetectSingleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service YoloDetectionService.
   */
  public static final class YoloDetectionServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<YoloDetectionServiceBlockingV2Stub> {
    private YoloDetectionServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloDetectionServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloDetectionServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * --- CONFIG MANAGEMENT ---
     * </pre>
     */
    public com.google.protobuf.Empty setConfig(knu.app.grpc.yolo.SetConfigRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.GetConfigResponse getConfig(com.google.protobuf.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- STREAM CONTROL ---
     * </pre>
     */
    public knu.app.grpc.yolo.StreamStatus startStreaming(knu.app.grpc.yolo.StartStreamingRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getStartStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.StreamStatus stopStreaming(knu.app.grpc.yolo.StopStreamingRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getStopStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.StreamStatus restartConnection(knu.app.grpc.yolo.RestartConnectionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getRestartConnectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- IMAGE STREAM ---
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<knu.app.grpc.yolo.ImageFrame, knu.app.grpc.yolo.TrackingResult>
        streamTrack() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getStreamTrackMethod(), getCallOptions());
    }

    /**
     */
    public knu.app.grpc.yolo.TrackingResult detectSingle(knu.app.grpc.yolo.ImageFrame request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDetectSingleMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service YoloDetectionService.
   */
  public static final class YoloDetectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<YoloDetectionServiceBlockingStub> {
    private YoloDetectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloDetectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloDetectionServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- CONFIG MANAGEMENT ---
     * </pre>
     */
    public com.google.protobuf.Empty setConfig(knu.app.grpc.yolo.SetConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.GetConfigResponse getConfig(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- STREAM CONTROL ---
     * </pre>
     */
    public knu.app.grpc.yolo.StreamStatus startStreaming(knu.app.grpc.yolo.StartStreamingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.StreamStatus stopStreaming(knu.app.grpc.yolo.StopStreamingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.StreamStatus restartConnection(knu.app.grpc.yolo.RestartConnectionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRestartConnectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.yolo.TrackingResult detectSingle(knu.app.grpc.yolo.ImageFrame request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDetectSingleMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service YoloDetectionService.
   */
  public static final class YoloDetectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<YoloDetectionServiceFutureStub> {
    private YoloDetectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloDetectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloDetectionServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- CONFIG MANAGEMENT ---
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setConfig(
        knu.app.grpc.yolo.SetConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.yolo.GetConfigResponse> getConfig(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * --- STREAM CONTROL ---
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.yolo.StreamStatus> startStreaming(
        knu.app.grpc.yolo.StartStreamingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartStreamingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.yolo.StreamStatus> stopStreaming(
        knu.app.grpc.yolo.StopStreamingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStopStreamingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.yolo.StreamStatus> restartConnection(
        knu.app.grpc.yolo.RestartConnectionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRestartConnectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.yolo.TrackingResult> detectSingle(
        knu.app.grpc.yolo.ImageFrame request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDetectSingleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_CONFIG = 0;
  private static final int METHODID_GET_CONFIG = 1;
  private static final int METHODID_START_STREAMING = 2;
  private static final int METHODID_STOP_STREAMING = 3;
  private static final int METHODID_RESTART_CONNECTION = 4;
  private static final int METHODID_DETECT_SINGLE = 5;
  private static final int METHODID_STREAM_TRACK = 6;

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
          serviceImpl.setConfig((knu.app.grpc.yolo.SetConfigRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_CONFIG:
          serviceImpl.getConfig((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.GetConfigResponse>) responseObserver);
          break;
        case METHODID_START_STREAMING:
          serviceImpl.startStreaming((knu.app.grpc.yolo.StartStreamingRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus>) responseObserver);
          break;
        case METHODID_STOP_STREAMING:
          serviceImpl.stopStreaming((knu.app.grpc.yolo.StopStreamingRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus>) responseObserver);
          break;
        case METHODID_RESTART_CONNECTION:
          serviceImpl.restartConnection((knu.app.grpc.yolo.RestartConnectionRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.StreamStatus>) responseObserver);
          break;
        case METHODID_DETECT_SINGLE:
          serviceImpl.detectSingle((knu.app.grpc.yolo.ImageFrame) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult>) responseObserver);
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
        case METHODID_STREAM_TRACK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamTrack(
              (io.grpc.stub.StreamObserver<knu.app.grpc.yolo.TrackingResult>) responseObserver);
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
              knu.app.grpc.yolo.SetConfigRequest,
              com.google.protobuf.Empty>(
                service, METHODID_SET_CONFIG)))
        .addMethod(
          getGetConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              knu.app.grpc.yolo.GetConfigResponse>(
                service, METHODID_GET_CONFIG)))
        .addMethod(
          getStartStreamingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.yolo.StartStreamingRequest,
              knu.app.grpc.yolo.StreamStatus>(
                service, METHODID_START_STREAMING)))
        .addMethod(
          getStopStreamingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.yolo.StopStreamingRequest,
              knu.app.grpc.yolo.StreamStatus>(
                service, METHODID_STOP_STREAMING)))
        .addMethod(
          getRestartConnectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.yolo.RestartConnectionRequest,
              knu.app.grpc.yolo.StreamStatus>(
                service, METHODID_RESTART_CONNECTION)))
        .addMethod(
          getStreamTrackMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              knu.app.grpc.yolo.ImageFrame,
              knu.app.grpc.yolo.TrackingResult>(
                service, METHODID_STREAM_TRACK)))
        .addMethod(
          getDetectSingleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.yolo.ImageFrame,
              knu.app.grpc.yolo.TrackingResult>(
                service, METHODID_DETECT_SINGLE)))
        .build();
  }

  private static abstract class YoloDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    YoloDetectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return knu.app.grpc.yolo.YoloProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("YoloDetectionService");
    }
  }

  private static final class YoloDetectionServiceFileDescriptorSupplier
      extends YoloDetectionServiceBaseDescriptorSupplier {
    YoloDetectionServiceFileDescriptorSupplier() {}
  }

  private static final class YoloDetectionServiceMethodDescriptorSupplier
      extends YoloDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    YoloDetectionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (YoloDetectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new YoloDetectionServiceFileDescriptorSupplier())
              .addMethod(getSetConfigMethod())
              .addMethod(getGetConfigMethod())
              .addMethod(getStartStreamingMethod())
              .addMethod(getStopStreamingMethod())
              .addMethod(getRestartConnectionMethod())
              .addMethod(getStreamTrackMethod())
              .addMethod(getDetectSingleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
