// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resource/demo.proto

package zdf.learn.com.commonUtils.data.protobuf;

public interface FileDealOrBuilder extends
    // @@protoc_insertion_point(interface_extends:FileDeal)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes dealType = 1;</code>
   * @return The dealType.
   */
  com.google.protobuf.ByteString getDealType();

  /**
   * <code>string dealResult = 5;</code>
   * @return The dealResult.
   */
  java.lang.String getDealResult();
  /**
   * <code>string dealResult = 5;</code>
   * @return The bytes for dealResult.
   */
  com.google.protobuf.ByteString
      getDealResultBytes();

  /**
   * <code>.FileBean bean = 9;</code>
   * @return Whether the bean field is set.
   */
  boolean hasBean();
  /**
   * <code>.FileBean bean = 9;</code>
   * @return The bean.
   */
  zdf.learn.com.commonUtils.data.protobuf.FileBean getBean();
  /**
   * <code>.FileBean bean = 9;</code>
   */
  zdf.learn.com.commonUtils.data.protobuf.FileBeanOrBuilder getBeanOrBuilder();
}
