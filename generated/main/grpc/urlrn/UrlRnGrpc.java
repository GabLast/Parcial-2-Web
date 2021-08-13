package urlrn;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: UrlRn.proto")
public final class UrlRnGrpc {

  private UrlRnGrpc() {}

  public static final String SERVICE_NAME = "urlrn.UrlRn";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<urlrn.UrlRnOuterClass.UrlRequest,
      urlrn.UrlRnOuterClass.UrlResponse> getGetUrlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUrl",
      requestType = urlrn.UrlRnOuterClass.UrlRequest.class,
      responseType = urlrn.UrlRnOuterClass.UrlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<urlrn.UrlRnOuterClass.UrlRequest,
      urlrn.UrlRnOuterClass.UrlResponse> getGetUrlMethod() {
    io.grpc.MethodDescriptor<urlrn.UrlRnOuterClass.UrlRequest, urlrn.UrlRnOuterClass.UrlResponse> getGetUrlMethod;
    if ((getGetUrlMethod = UrlRnGrpc.getGetUrlMethod) == null) {
      synchronized (UrlRnGrpc.class) {
        if ((getGetUrlMethod = UrlRnGrpc.getGetUrlMethod) == null) {
          UrlRnGrpc.getGetUrlMethod = getGetUrlMethod =
              io.grpc.MethodDescriptor.<urlrn.UrlRnOuterClass.UrlRequest, urlrn.UrlRnOuterClass.UrlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUrl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  urlrn.UrlRnOuterClass.UrlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  urlrn.UrlRnOuterClass.UrlResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UrlRnMethodDescriptorSupplier("getUrl"))
              .build();
        }
      }
    }
    return getGetUrlMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UrlRnStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UrlRnStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UrlRnStub>() {
        @java.lang.Override
        public UrlRnStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UrlRnStub(channel, callOptions);
        }
      };
    return UrlRnStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UrlRnBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UrlRnBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UrlRnBlockingStub>() {
        @java.lang.Override
        public UrlRnBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UrlRnBlockingStub(channel, callOptions);
        }
      };
    return UrlRnBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UrlRnFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UrlRnFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UrlRnFutureStub>() {
        @java.lang.Override
        public UrlRnFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UrlRnFutureStub(channel, callOptions);
        }
      };
    return UrlRnFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UrlRnImplBase implements io.grpc.BindableService {

    /**
     */
    public void getUrl(urlrn.UrlRnOuterClass.UrlRequest request,
        io.grpc.stub.StreamObserver<urlrn.UrlRnOuterClass.UrlResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUrlMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUrlMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                urlrn.UrlRnOuterClass.UrlRequest,
                urlrn.UrlRnOuterClass.UrlResponse>(
                  this, METHODID_GET_URL)))
          .build();
    }
  }

  /**
   */
  public static final class UrlRnStub extends io.grpc.stub.AbstractAsyncStub<UrlRnStub> {
    private UrlRnStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UrlRnStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UrlRnStub(channel, callOptions);
    }

    /**
     */
    public void getUrl(urlrn.UrlRnOuterClass.UrlRequest request,
        io.grpc.stub.StreamObserver<urlrn.UrlRnOuterClass.UrlResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUrlMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UrlRnBlockingStub extends io.grpc.stub.AbstractBlockingStub<UrlRnBlockingStub> {
    private UrlRnBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UrlRnBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UrlRnBlockingStub(channel, callOptions);
    }

    /**
     */
    public urlrn.UrlRnOuterClass.UrlResponse getUrl(urlrn.UrlRnOuterClass.UrlRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUrlMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UrlRnFutureStub extends io.grpc.stub.AbstractFutureStub<UrlRnFutureStub> {
    private UrlRnFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UrlRnFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UrlRnFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<urlrn.UrlRnOuterClass.UrlResponse> getUrl(
        urlrn.UrlRnOuterClass.UrlRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUrlMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_URL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UrlRnImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UrlRnImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_URL:
          serviceImpl.getUrl((urlrn.UrlRnOuterClass.UrlRequest) request,
              (io.grpc.stub.StreamObserver<urlrn.UrlRnOuterClass.UrlResponse>) responseObserver);
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

  private static abstract class UrlRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UrlRnBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return urlrn.UrlRnOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UrlRn");
    }
  }

  private static final class UrlRnFileDescriptorSupplier
      extends UrlRnBaseDescriptorSupplier {
    UrlRnFileDescriptorSupplier() {}
  }

  private static final class UrlRnMethodDescriptorSupplier
      extends UrlRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UrlRnMethodDescriptorSupplier(String methodName) {
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
      synchronized (UrlRnGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UrlRnFileDescriptorSupplier())
              .addMethod(getGetUrlMethod())
              .build();
        }
      }
    }
    return result;
  }
}
