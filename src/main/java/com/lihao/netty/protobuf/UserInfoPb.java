// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/proto/UserInfoPb.proto

package com.lihao.netty.protobuf;

public final class UserInfoPb {
  private UserInfoPb() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_UserInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_UserInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037src/main/proto/UserInfoPb.proto\022\005proto" +
      "\"K\n\010UserInfo\022\020\n\010userName\030\001 \001(\t\022\017\n\007addres" +
      "s\030\002 \001(\t\022\014\n\004desc\030\003 \001(\t\022\016\n\006userId\030\004 \001(\005B(\n" +
      "\030com.lihao.netty.protobufB\nUserInfoPbP\001b" +
      "\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_UserInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_UserInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_UserInfo_descriptor,
        new java.lang.String[] { "UserName", "Address", "Desc", "UserId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}