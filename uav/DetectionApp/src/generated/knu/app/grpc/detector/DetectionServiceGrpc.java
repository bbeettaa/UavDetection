package knu.app.grpc.detector;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class DetectionServiceGrpc {

  private DetectionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "detector.DetectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.detector.ImageRequest,
      knu.app.grpc.detector.ImageResponse> getStreamDetectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamDetect",
      requestType = knu.app.grpc.detector.ImageRequest.class,
      responseType = knu.app.grpc.detector.ImageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<knu.app.grpc.detector.ImageRequest,
      knu.app.grpc.detector.ImageResponse> getStreamDetectMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.detector.ImageRequest, knu.app.grpc.detector.ImageResponse> getStreamDetectMethod;
    if ((getStreamDetectMethod = DetectionServiceGrpc.getStreamDetectMethod) == null) {
      synchronized (DetectionServiceGrpc.class) {
        if ((getStreamDetectMethod = DetectionServiceGrpc.getStreamDetectMethod) == null) {
          DetectionServiceGrpc.getStreamDetectMethod = getStreamDetectMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.detector.ImageRequest, knu.app.grpc.detector.ImageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamDetect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.ImageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.ImageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DetectionServiceMethodDescriptorSupplier("StreamDetect"))
              .build();
        }
      }
    }
    return getStreamDetectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.detector.GetDetectorsResponse> getGetDetectorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDetectors",
      requestType = com.google.protobuf.Empty.class,
      responseType = knu.app.grpc.detector.GetDetectorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      knu.app.grpc.detector.GetDetectorsResponse> getGetDetectorsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, knu.app.grpc.detector.GetDetectorsResponse> getGetDetectorsMethod;
    if ((getGetDetectorsMethod = DetectionServiceGrpc.getGetDetectorsMethod) == null) {
      synchronized (DetectionServiceGrpc.class) {
        if ((getGetDetectorsMethod = DetectionServiceGrpc.getGetDetectorsMethod) == null) {
          DetectionServiceGrpc.getGetDetectorsMethod = getGetDetectorsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, knu.app.grpc.detector.GetDetectorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDetectors"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.GetDetectorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DetectionServiceMethodDescriptorSupplier("GetDetectors"))
              .build();
        }
      }
    }
    return getGetDetectorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.detector.ChooseModelRequest,
      knu.app.grpc.detector.ChooseModelResponse> getChooseModelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChooseModel",
      requestType = knu.app.grpc.detector.ChooseModelRequest.class,
      responseType = knu.app.grpc.detector.ChooseModelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.detector.ChooseModelRequest,
      knu.app.grpc.detector.ChooseModelResponse> getChooseModelMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.detector.ChooseModelRequest, knu.app.grpc.detector.ChooseModelResponse> getChooseModelMethod;
    if ((getChooseModelMethod = DetectionServiceGrpc.getChooseModelMethod) == null) {
      synchronized (DetectionServiceGrpc.class) {
        if ((getChooseModelMethod = DetectionServiceGrpc.getChooseModelMethod) == null) {
          DetectionServiceGrpc.getChooseModelMethod = getChooseModelMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.detector.ChooseModelRequest, knu.app.grpc.detector.ChooseModelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChooseModel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.ChooseModelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.ChooseModelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DetectionServiceMethodDescriptorSupplier("ChooseModel"))
              .build();
        }
      }
    }
    return getChooseModelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<knu.app.grpc.detector.SetConfigRequest,
      com.google.protobuf.Empty> getSetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetConfig",
      requestType = knu.app.grpc.detector.SetConfigRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<knu.app.grpc.detector.SetConfigRequest,
      com.google.protobuf.Empty> getSetConfigMethod() {
    io.grpc.MethodDescriptor<knu.app.grpc.detector.SetConfigRequest, com.google.protobuf.Empty> getSetConfigMethod;
    if ((getSetConfigMethod = DetectionServiceGrpc.getSetConfigMethod) == null) {
      synchronized (DetectionServiceGrpc.class) {
        if ((getSetConfigMethod = DetectionServiceGrpc.getSetConfigMethod) == null) {
          DetectionServiceGrpc.getSetConfigMethod = getSetConfigMethod =
              io.grpc.MethodDescriptor.<knu.app.grpc.detector.SetConfigRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  knu.app.grpc.detector.SetConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DetectionServiceMethodDescriptorSupplier("SetConfig"))
              .build();
        }
      }
    }
    return getSetConfigMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DetectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DetectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DetectionServiceStub>() {
        @java.lang.Override
        public DetectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DetectionServiceStub(channel, callOptions);
        }
      };
    return DetectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static DetectionServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DetectionServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DetectionServiceBlockingV2Stub>() {
        @java.lang.Override
        public DetectionServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DetectionServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return DetectionServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DetectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DetectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DetectionServiceBlockingStub>() {
        @java.lang.Override
        public DetectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DetectionServiceBlockingStub(channel, callOptions);
        }
      };
    return DetectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DetectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DetectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DetectionServiceFutureStub>() {
        @java.lang.Override
        public DetectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DetectionServiceFutureStub(channel, callOptions);
        }
      };
    return DetectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<knu.app.grpc.detector.ImageRequest> streamDetect(
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.ImageResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamDetectMethod(), responseObserver);
    }

    /**
     */
    default void getDetectors(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.GetDetectorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDetectorsMethod(), responseObserver);
    }

    /**
     */
    default void chooseModel(knu.app.grpc.detector.ChooseModelRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.ChooseModelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChooseModelMethod(), responseObserver);
    }

    /**
     */
    default void setConfig(knu.app.grpc.detector.SetConfigRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetConfigMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DetectionService.
   */
  public static abstract class DetectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DetectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DetectionService.
   */
  public static final class DetectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DetectionServiceStub> {
    private DetectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DetectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DetectionServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<knu.app.grpc.detector.ImageRequest> streamDetect(
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.ImageResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getStreamDetectMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void getDetectors(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.GetDetectorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDetectorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void chooseModel(knu.app.grpc.detector.ChooseModelRequest request,
        io.grpc.stub.StreamObserver<knu.app.grpc.detector.ChooseModelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChooseModelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setConfig(knu.app.grpc.detector.SetConfigRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DetectionService.
   */
  public static final class DetectionServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<DetectionServiceBlockingV2Stub> {
    private DetectionServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DetectionServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DetectionServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<knu.app.grpc.detector.ImageRequest, knu.app.grpc.detector.ImageResponse>
        streamDetect() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getStreamDetectMethod(), getCallOptions());
    }

    /**
     */
    public knu.app.grpc.detector.GetDetectorsResponse getDetectors(com.google.protobuf.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetDetectorsMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.detector.ChooseModelResponse chooseModel(knu.app.grpc.detector.ChooseModelRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getChooseModelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setConfig(knu.app.grpc.detector.SetConfigRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service DetectionService.
   */
  public static final class DetectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DetectionServiceBlockingStub> {
    private DetectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DetectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DetectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public knu.app.grpc.detector.GetDetectorsResponse getDetectors(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDetectorsMethod(), getCallOptions(), request);
    }

    /**
     */
    public knu.app.grpc.detector.ChooseModelResponse chooseModel(knu.app.grpc.detector.ChooseModelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChooseModelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setConfig(knu.app.grpc.detector.SetConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DetectionService.
   */
  public static final class DetectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DetectionServiceFutureStub> {
    private DetectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DetectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DetectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.detector.GetDetectorsResponse> getDetectors(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDetectorsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<knu.app.grpc.detector.ChooseModelResponse> chooseModel(
        knu.app.grpc.detector.ChooseModelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChooseModelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setConfig(
        knu.app.grpc.detector.SetConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DETECTORS = 0;
  private static final int METHODID_CHOOSE_MODEL = 1;
  private static final int METHODID_SET_CONFIG = 2;
  private static final int METHODID_STREAM_DETECT = 3;

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
        case METHODID_GET_DETECTORS:
          serviceImpl.getDetectors((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.detector.GetDetectorsResponse>) responseObserver);
          break;
        case METHODID_CHOOSE_MODEL:
          serviceImpl.chooseModel((knu.app.grpc.detector.ChooseModelRequest) request,
              (io.grpc.stub.StreamObserver<knu.app.grpc.detector.ChooseModelResponse>) responseObserver);
          break;
        case METHODID_SET_CONFIG:
          serviceImpl.setConfig((knu.app.grpc.detector.SetConfigRequest) request,
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
        case METHODID_STREAM_DETECT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamDetect(
              (io.grpc.stub.StreamObserver<knu.app.grpc.detector.ImageResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getStreamDetectMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              knu.app.grpc.detector.ImageRequest,
              knu.app.grpc.detector.ImageResponse>(
                service, METHODID_STREAM_DETECT)))
        .addMethod(
          getGetDetectorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              knu.app.grpc.detector.GetDetectorsResponse>(
                service, METHODID_GET_DETECTORS)))
        .addMethod(
          getChooseModelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.detector.ChooseModelRequest,
              knu.app.grpc.detector.ChooseModelResponse>(
                service, METHODID_CHOOSE_MODEL)))
        .addMethod(
          getSetConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              knu.app.grpc.detector.SetConfigRequest,
              com.google.protobuf.Empty>(
                service, METHODID_SET_CONFIG)))
        .build();
  }

  private static abstract class DetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DetectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return knu.app.grpc.detector.DetectorProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DetectionService");
    }
  }

  private static final class DetectionServiceFileDescriptorSupplier
      extends DetectionServiceBaseDescriptorSupplier {
    DetectionServiceFileDescriptorSupplier() {}
  }

  private static final class DetectionServiceMethodDescriptorSupplier
      extends DetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DetectionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DetectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DetectionServiceFileDescriptorSupplier())
              .addMethod(getStreamDetectMethod())
              .addMethod(getGetDetectorsMethod())
              .addMethod(getChooseModelMethod())
              .addMethod(getSetConfigMethod())
              .build();
        }
      }
    }
    return result;
  }
}
