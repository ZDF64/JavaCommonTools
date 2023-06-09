// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resource/demo.proto

package zdf.learn.com.commonUtils.data.protobuf;

/**
 * <pre>
 *第二个类的名称为FileDeal，我将它用作操作FileBean类。但是在这里不需要对两个类做关联性操作。属性包含如下所示
 * </pre>
 *
 * Protobuf type {@code FileDeal}
 */
public final class FileDeal extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:FileDeal)
    FileDealOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FileDeal.newBuilder() to construct.
  private FileDeal(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FileDeal() {
    dealType_ = com.google.protobuf.ByteString.EMPTY;
    dealResult_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FileDeal();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return zdf.learn.com.commonUtils.data.protobuf.Demo.internal_static_FileDeal_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return zdf.learn.com.commonUtils.data.protobuf.Demo.internal_static_FileDeal_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            zdf.learn.com.commonUtils.data.protobuf.FileDeal.class, zdf.learn.com.commonUtils.data.protobuf.FileDeal.Builder.class);
  }

  public static final int DEALTYPE_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString dealType_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes dealType = 1;</code>
   * @return The dealType.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getDealType() {
    return dealType_;
  }

  public static final int DEALRESULT_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private volatile java.lang.Object dealResult_ = "";
  /**
   * <code>string dealResult = 5;</code>
   * @return The dealResult.
   */
  @java.lang.Override
  public java.lang.String getDealResult() {
    java.lang.Object ref = dealResult_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      dealResult_ = s;
      return s;
    }
  }
  /**
   * <code>string dealResult = 5;</code>
   * @return The bytes for dealResult.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDealResultBytes() {
    java.lang.Object ref = dealResult_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      dealResult_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BEAN_FIELD_NUMBER = 9;
  private zdf.learn.com.commonUtils.data.protobuf.FileBean bean_;
  /**
   * <code>.FileBean bean = 9;</code>
   * @return Whether the bean field is set.
   */
  @java.lang.Override
  public boolean hasBean() {
    return bean_ != null;
  }
  /**
   * <code>.FileBean bean = 9;</code>
   * @return The bean.
   */
  @java.lang.Override
  public zdf.learn.com.commonUtils.data.protobuf.FileBean getBean() {
    return bean_ == null ? zdf.learn.com.commonUtils.data.protobuf.FileBean.getDefaultInstance() : bean_;
  }
  /**
   * <code>.FileBean bean = 9;</code>
   */
  @java.lang.Override
  public zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder getBeanOrBuilder() {
    return bean_ == null ? zdf.learn.com.commonUtils.data.protobuf.FileBean.getDefaultInstance() : bean_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!dealType_.isEmpty()) {
      output.writeBytes(1, dealType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(dealResult_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, dealResult_);
    }
    if (bean_ != null) {
      output.writeMessage(9, getBean());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!dealType_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, dealType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(dealResult_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, dealResult_);
    }
    if (bean_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(9, getBean());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof zdf.learn.com.commonUtils.data.protobuf.FileDeal)) {
      return super.equals(obj);
    }
    zdf.learn.com.commonUtils.data.protobuf.FileDeal other = (zdf.learn.com.commonUtils.data.protobuf.FileDeal) obj;

    if (!getDealType()
        .equals(other.getDealType())) return false;
    if (!getDealResult()
        .equals(other.getDealResult())) return false;
    if (hasBean() != other.hasBean()) return false;
    if (hasBean()) {
      if (!getBean()
          .equals(other.getBean())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DEALTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getDealType().hashCode();
    hash = (37 * hash) + DEALRESULT_FIELD_NUMBER;
    hash = (53 * hash) + getDealResult().hashCode();
    if (hasBean()) {
      hash = (37 * hash) + BEAN_FIELD_NUMBER;
      hash = (53 * hash) + getBean().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(zdf.learn.com.commonUtils.data.protobuf.FileDeal prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *第二个类的名称为FileDeal，我将它用作操作FileBean类。但是在这里不需要对两个类做关联性操作。属性包含如下所示
   * </pre>
   *
   * Protobuf type {@code FileDeal}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FileDeal)
      zdf.learn.com.commonUtils.data.protobuf.FileDealOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return zdf.learn.com.commonUtils.data.protobuf.Demo.internal_static_FileDeal_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return zdf.learn.com.commonUtils.data.protobuf.Demo.internal_static_FileDeal_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              zdf.learn.com.commonUtils.data.protobuf.FileDeal.class, zdf.learn.com.commonUtils.data.protobuf.FileDeal.Builder.class);
    }

    // Construct using zdf.learn.com.commonUtils.data.protobuf.FileDeal.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      dealType_ = com.google.protobuf.ByteString.EMPTY;
      dealResult_ = "";
      bean_ = null;
      if (beanBuilder_ != null) {
        beanBuilder_.dispose();
        beanBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return zdf.learn.com.commonUtils.data.protobuf.Demo.internal_static_FileDeal_descriptor;
    }

    @java.lang.Override
    public zdf.learn.com.commonUtils.data.protobuf.FileDeal getDefaultInstanceForType() {
      return zdf.learn.com.commonUtils.data.protobuf.FileDeal.getDefaultInstance();
    }

    @java.lang.Override
    public zdf.learn.com.commonUtils.data.protobuf.FileDeal build() {
      zdf.learn.com.commonUtils.data.protobuf.FileDeal result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public zdf.learn.com.commonUtils.data.protobuf.FileDeal buildPartial() {
      zdf.learn.com.commonUtils.data.protobuf.FileDeal result = new zdf.learn.com.commonUtils.data.protobuf.FileDeal(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(zdf.learn.com.commonUtils.data.protobuf.FileDeal result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.dealType_ = dealType_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.dealResult_ = dealResult_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.bean_ = beanBuilder_ == null
            ? bean_
            : beanBuilder_.build();
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof zdf.learn.com.commonUtils.data.protobuf.FileDeal) {
        return mergeFrom((zdf.learn.com.commonUtils.data.protobuf.FileDeal)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(zdf.learn.com.commonUtils.data.protobuf.FileDeal other) {
      if (other == zdf.learn.com.commonUtils.data.protobuf.FileDeal.getDefaultInstance()) return this;
      if (other.getDealType() != com.google.protobuf.ByteString.EMPTY) {
        setDealType(other.getDealType());
      }
      if (!other.getDealResult().isEmpty()) {
        dealResult_ = other.dealResult_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.hasBean()) {
        mergeBean(other.getBean());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              dealType_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 42: {
              dealResult_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 42
            case 74: {
              input.readMessage(
                  getBeanFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000004;
              break;
            } // case 74
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.ByteString dealType_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes dealType = 1;</code>
     * @return The dealType.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getDealType() {
      return dealType_;
    }
    /**
     * <code>bytes dealType = 1;</code>
     * @param value The dealType to set.
     * @return This builder for chaining.
     */
    public Builder setDealType(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      dealType_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bytes dealType = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDealType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      dealType_ = getDefaultInstance().getDealType();
      onChanged();
      return this;
    }

    private java.lang.Object dealResult_ = "";
    /**
     * <code>string dealResult = 5;</code>
     * @return The dealResult.
     */
    public java.lang.String getDealResult() {
      java.lang.Object ref = dealResult_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dealResult_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string dealResult = 5;</code>
     * @return The bytes for dealResult.
     */
    public com.google.protobuf.ByteString
        getDealResultBytes() {
      java.lang.Object ref = dealResult_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dealResult_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string dealResult = 5;</code>
     * @param value The dealResult to set.
     * @return This builder for chaining.
     */
    public Builder setDealResult(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      dealResult_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string dealResult = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearDealResult() {
      dealResult_ = getDefaultInstance().getDealResult();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string dealResult = 5;</code>
     * @param value The bytes for dealResult to set.
     * @return This builder for chaining.
     */
    public Builder setDealResultBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      dealResult_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private zdf.learn.com.commonUtils.data.protobuf.FileBean bean_;
    private com.google.protobuf.SingleFieldBuilderV3<
        zdf.learn.com.commonUtils.data.protobuf.FileBean, zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder, zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder> beanBuilder_;
    /**
     * <code>.FileBean bean = 9;</code>
     * @return Whether the bean field is set.
     */
    public boolean hasBean() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>.FileBean bean = 9;</code>
     * @return The bean.
     */
    public zdf.learn.com.commonUtils.data.protobuf.FileBean getBean() {
      if (beanBuilder_ == null) {
        return bean_ == null ? zdf.learn.com.commonUtils.data.protobuf.FileBean.getDefaultInstance() : bean_;
      } else {
        return beanBuilder_.getMessage();
      }
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public Builder setBean(zdf.learn.com.commonUtils.data.protobuf.FileBean value) {
      if (beanBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        bean_ = value;
      } else {
        beanBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public Builder setBean(
        zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder builderForValue) {
      if (beanBuilder_ == null) {
        bean_ = builderForValue.build();
      } else {
        beanBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public Builder mergeBean(zdf.learn.com.commonUtils.data.protobuf.FileBean value) {
      if (beanBuilder_ == null) {
        if (((bitField0_ & 0x00000004) != 0) &&
          bean_ != null &&
          bean_ != zdf.learn.com.commonUtils.data.protobuf.FileBean.getDefaultInstance()) {
          getBeanBuilder().mergeFrom(value);
        } else {
          bean_ = value;
        }
      } else {
        beanBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public Builder clearBean() {
      bitField0_ = (bitField0_ & ~0x00000004);
      bean_ = null;
      if (beanBuilder_ != null) {
        beanBuilder_.dispose();
        beanBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder getBeanBuilder() {
      bitField0_ |= 0x00000004;
      onChanged();
      return getBeanFieldBuilder().getBuilder();
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    public zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder getBeanOrBuilder() {
      if (beanBuilder_ != null) {
        return beanBuilder_.getMessageOrBuilder();
      } else {
        return bean_ == null ?
            zdf.learn.com.commonUtils.data.protobuf.FileBean.getDefaultInstance() : bean_;
      }
    }
    /**
     * <code>.FileBean bean = 9;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        zdf.learn.com.commonUtils.data.protobuf.FileBean, zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder, zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder> 
        getBeanFieldBuilder() {
      if (beanBuilder_ == null) {
        beanBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            zdf.learn.com.commonUtils.data.protobuf.FileBean, zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder, zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder>(
                getBean(),
                getParentForChildren(),
                isClean());
        bean_ = null;
      }
      return beanBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:FileDeal)
  }

  // @@protoc_insertion_point(class_scope:FileDeal)
  private static final zdf.learn.com.commonUtils.data.protobuf.FileDeal DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new zdf.learn.com.commonUtils.data.protobuf.FileDeal();
  }

  public static zdf.learn.com.commonUtils.data.protobuf.FileDeal getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FileDeal>
      PARSER = new com.google.protobuf.AbstractParser<FileDeal>() {
    @java.lang.Override
    public FileDeal parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<FileDeal> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FileDeal> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public zdf.learn.com.commonUtils.data.protobuf.FileDeal getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

