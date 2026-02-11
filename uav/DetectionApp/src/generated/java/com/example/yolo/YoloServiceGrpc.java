package com.example.yolo;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * gRPC сервис
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class YoloServiceGrpc {

  private YoloServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "yolo.YoloService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.yolo.ImageRequest,
      com.example.yolo.ImageResponse> getDetectObjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DetectObjects",
      requestType = com.example.yolo.ImageRequest.class,
      responseType = com.example.yolo.ImageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.yolo.ImageRequest,
      com.example.yolo.ImageResponse> getDetectObjectsMethod() {
    io.grpc.MethodDescriptor<com.example.yolo.ImageRequest, com.example.yolo.ImageResponse> getDetectObjectsMethod;
    if ((getDetectObjectsMethod = YoloServiceGrpc.getDetectObjectsMethod) == null) {
      synchronized (YoloServiceGrpc.class) {
        if ((getDetectObjectsMethod = YoloServiceGrpc.getDetectObjectsMethod) == null) {
          YoloServiceGrpc.getDetectObjectsMethod = getDetectObjectsMethod =
              io.grpc.MethodDescriptor.<com.example.yolo.ImageRequest, com.example.yolo.ImageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DetectObjects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.yolo.ImageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.yolo.ImageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new YoloServiceMethodDescriptorSupplier("DetectObjects"))
              .build();
        }
      }
    }
    return getDetectObjectsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static YoloServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloServiceStub>() {
        @java.lang.Override
        public YoloServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloServiceStub(channel, callOptions);
        }
      };
    return YoloServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static YoloServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloServiceBlockingV2Stub>() {
        @java.lang.Override
        public YoloServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return YoloServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static YoloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloServiceBlockingStub>() {
        @java.lang.Override
        public YoloServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloServiceBlockingStub(channel, callOptions);
        }
      };
    return YoloServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static YoloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YoloServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YoloServiceFutureStub>() {
        @java.lang.Override
        public YoloServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YoloServiceFutureStub(channel, callOptions);
        }
      };
    return YoloServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void detectObjects(com.example.yolo.ImageRequest request,
        io.grpc.stub.StreamObserver<com.example.yolo.ImageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDetectObjectsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service YoloService.
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public static abstract class YoloServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return YoloServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service YoloService.
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public static final class YoloServiceStub
      extends io.grpc.stub.AbstractAsyncStub<YoloServiceStub> {
    private YoloServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloServiceStub(channel, callOptions);
    }

    /**
     */
    public void detectObjects(com.example.yolo.ImageRequest request,
        io.grpc.stub.StreamObserver<com.example.yolo.ImageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDetectObjectsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service YoloService.
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public static final class YoloServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<YoloServiceBlockingV2Stub> {
    private YoloServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.example.yolo.ImageResponse detectObjects(com.example.yolo.ImageRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDetectObjectsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service YoloService.
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public static final class YoloServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<YoloServiceBlockingStub> {
    private YoloServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.yolo.ImageResponse detectObjects(com.example.yolo.ImageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDetectObjectsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service YoloService.
   * <pre>
   * gRPC сервис
   * </pre>
   */
  public static final class YoloServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<YoloServiceFutureStub> {
    private YoloServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YoloServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YoloServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.yolo.ImageResponse> detectObjects(
        com.example.yolo.ImageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDetectObjectsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DETECT_OBJECTS = 0;

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
        case METHODID_DETECT_OBJECTS:
          serviceImpl.detectObjects((com.example.yolo.ImageRequest) request,
              (io.grpc.stub.StreamObserver<com.example.yolo.ImageResponse>) responseObserver);
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
          getDetectObjectsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.yolo.ImageRequest,
              com.example.yolo.ImageResponse>(
                service, METHODID_DETECT_OBJECTS)))
        .build();
  }

  private static abstract class YoloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    YoloServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.yolo.YoloProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("YoloService");
    }
  }

  private static final class YoloServiceFileDescriptorSupplier
      extends YoloServiceBaseDescriptorSupplier {
    YoloServiceFileDescriptorSupplier() {}
  }

  private static final class YoloServiceMethodDescriptorSupplier
      extends YoloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    YoloServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (YoloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new YoloServiceFileDescriptorSupplier())
              .addMethod(getDetectObjectsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
