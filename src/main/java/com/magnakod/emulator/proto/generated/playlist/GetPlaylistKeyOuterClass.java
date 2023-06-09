package com.magnakod.emulator.proto.generated.playlist;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GetPlaylistKey.proto

public final class GetPlaylistKeyOuterClass {
  private GetPlaylistKeyOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GetPlaylistKeyOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GetPlaylistKey)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional bytes playlistKey = 1;</code>
     * @return Whether the playlistKey field is set.
     */
    boolean hasPlaylistKey();
    /**
     * <code>optional bytes playlistKey = 1;</code>
     * @return The playlistKey.
     */
    com.google.protobuf.ByteString getPlaylistKey();

    /**
     * <code>optional bytes playlistKeyArr = 6;</code>
     * @return Whether the playlistKeyArr field is set.
     */
    boolean hasPlaylistKeyArr();
    /**
     * <code>optional bytes playlistKeyArr = 6;</code>
     * @return The playlistKeyArr.
     */
    com.google.protobuf.ByteString getPlaylistKeyArr();
  }
  /**
   * Protobuf type {@code GetPlaylistKey}
   */
  public static final class GetPlaylistKey extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:GetPlaylistKey)
      GetPlaylistKeyOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GetPlaylistKey.newBuilder() to construct.
    private GetPlaylistKey(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GetPlaylistKey() {
      playlistKey_ = com.google.protobuf.ByteString.EMPTY;
      playlistKeyArr_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GetPlaylistKey();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GetPlaylistKeyOuterClass.internal_static_GetPlaylistKey_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GetPlaylistKeyOuterClass.internal_static_GetPlaylistKey_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetPlaylistKeyOuterClass.GetPlaylistKey.class, GetPlaylistKeyOuterClass.GetPlaylistKey.Builder.class);
    }

    private int bitField0_;
    public static final int PLAYLISTKEY_FIELD_NUMBER = 1;
    private com.google.protobuf.ByteString playlistKey_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes playlistKey = 1;</code>
     * @return Whether the playlistKey field is set.
     */
    @java.lang.Override
    public boolean hasPlaylistKey() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional bytes playlistKey = 1;</code>
     * @return The playlistKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPlaylistKey() {
      return playlistKey_;
    }

    public static final int PLAYLISTKEYARR_FIELD_NUMBER = 6;
    private com.google.protobuf.ByteString playlistKeyArr_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes playlistKeyArr = 6;</code>
     * @return Whether the playlistKeyArr field is set.
     */
    @java.lang.Override
    public boolean hasPlaylistKeyArr() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional bytes playlistKeyArr = 6;</code>
     * @return The playlistKeyArr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPlaylistKeyArr() {
      return playlistKeyArr_;
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
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeBytes(1, playlistKey_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeBytes(6, playlistKeyArr_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, playlistKey_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, playlistKeyArr_);
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
      if (!(obj instanceof GetPlaylistKeyOuterClass.GetPlaylistKey)) {
        return super.equals(obj);
      }
      GetPlaylistKeyOuterClass.GetPlaylistKey other = (GetPlaylistKeyOuterClass.GetPlaylistKey) obj;

      if (hasPlaylistKey() != other.hasPlaylistKey()) return false;
      if (hasPlaylistKey()) {
        if (!getPlaylistKey()
            .equals(other.getPlaylistKey())) return false;
      }
      if (hasPlaylistKeyArr() != other.hasPlaylistKeyArr()) return false;
      if (hasPlaylistKeyArr()) {
        if (!getPlaylistKeyArr()
            .equals(other.getPlaylistKeyArr())) return false;
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
      if (hasPlaylistKey()) {
        hash = (37 * hash) + PLAYLISTKEY_FIELD_NUMBER;
        hash = (53 * hash) + getPlaylistKey().hashCode();
      }
      if (hasPlaylistKeyArr()) {
        hash = (37 * hash) + PLAYLISTKEYARR_FIELD_NUMBER;
        hash = (53 * hash) + getPlaylistKeyArr().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static GetPlaylistKeyOuterClass.GetPlaylistKey parseFrom(
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
    public static Builder newBuilder(GetPlaylistKeyOuterClass.GetPlaylistKey prototype) {
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
     * Protobuf type {@code GetPlaylistKey}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GetPlaylistKey)
        GetPlaylistKeyOuterClass.GetPlaylistKeyOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return GetPlaylistKeyOuterClass.internal_static_GetPlaylistKey_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return GetPlaylistKeyOuterClass.internal_static_GetPlaylistKey_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                GetPlaylistKeyOuterClass.GetPlaylistKey.class, GetPlaylistKeyOuterClass.GetPlaylistKey.Builder.class);
      }

      // Construct using GetPlaylistKeyOuterClass.GetPlaylistKey.newBuilder()
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
        playlistKey_ = com.google.protobuf.ByteString.EMPTY;
        playlistKeyArr_ = com.google.protobuf.ByteString.EMPTY;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return GetPlaylistKeyOuterClass.internal_static_GetPlaylistKey_descriptor;
      }

      @java.lang.Override
      public GetPlaylistKeyOuterClass.GetPlaylistKey getDefaultInstanceForType() {
        return GetPlaylistKeyOuterClass.GetPlaylistKey.getDefaultInstance();
      }

      @java.lang.Override
      public GetPlaylistKeyOuterClass.GetPlaylistKey build() {
        GetPlaylistKeyOuterClass.GetPlaylistKey result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public GetPlaylistKeyOuterClass.GetPlaylistKey buildPartial() {
        GetPlaylistKeyOuterClass.GetPlaylistKey result = new GetPlaylistKeyOuterClass.GetPlaylistKey(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(GetPlaylistKeyOuterClass.GetPlaylistKey result) {
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.playlistKey_ = playlistKey_;
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.playlistKeyArr_ = playlistKeyArr_;
          to_bitField0_ |= 0x00000002;
        }
        result.bitField0_ |= to_bitField0_;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof GetPlaylistKeyOuterClass.GetPlaylistKey) {
          return mergeFrom((GetPlaylistKeyOuterClass.GetPlaylistKey)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(GetPlaylistKeyOuterClass.GetPlaylistKey other) {
        if (other == GetPlaylistKeyOuterClass.GetPlaylistKey.getDefaultInstance()) return this;
        if (other.hasPlaylistKey()) {
          setPlaylistKey(other.getPlaylistKey());
        }
        if (other.hasPlaylistKeyArr()) {
          setPlaylistKeyArr(other.getPlaylistKeyArr());
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
                playlistKey_ = input.readBytes();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 50: {
                playlistKeyArr_ = input.readBytes();
                bitField0_ |= 0x00000002;
                break;
              } // case 50
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

      private com.google.protobuf.ByteString playlistKey_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes playlistKey = 1;</code>
       * @return Whether the playlistKey field is set.
       */
      @java.lang.Override
      public boolean hasPlaylistKey() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>optional bytes playlistKey = 1;</code>
       * @return The playlistKey.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getPlaylistKey() {
        return playlistKey_;
      }
      /**
       * <code>optional bytes playlistKey = 1;</code>
       * @param value The playlistKey to set.
       * @return This builder for chaining.
       */
      public Builder setPlaylistKey(com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        playlistKey_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes playlistKey = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearPlaylistKey() {
        bitField0_ = (bitField0_ & ~0x00000001);
        playlistKey_ = getDefaultInstance().getPlaylistKey();
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString playlistKeyArr_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes playlistKeyArr = 6;</code>
       * @return Whether the playlistKeyArr field is set.
       */
      @java.lang.Override
      public boolean hasPlaylistKeyArr() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>optional bytes playlistKeyArr = 6;</code>
       * @return The playlistKeyArr.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getPlaylistKeyArr() {
        return playlistKeyArr_;
      }
      /**
       * <code>optional bytes playlistKeyArr = 6;</code>
       * @param value The playlistKeyArr to set.
       * @return This builder for chaining.
       */
      public Builder setPlaylistKeyArr(com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        playlistKeyArr_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes playlistKeyArr = 6;</code>
       * @return This builder for chaining.
       */
      public Builder clearPlaylistKeyArr() {
        bitField0_ = (bitField0_ & ~0x00000002);
        playlistKeyArr_ = getDefaultInstance().getPlaylistKeyArr();
        onChanged();
        return this;
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


      // @@protoc_insertion_point(builder_scope:GetPlaylistKey)
    }

    // @@protoc_insertion_point(class_scope:GetPlaylistKey)
    private static final GetPlaylistKeyOuterClass.GetPlaylistKey DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new GetPlaylistKeyOuterClass.GetPlaylistKey();
    }

    public static GetPlaylistKeyOuterClass.GetPlaylistKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GetPlaylistKey>
        PARSER = new com.google.protobuf.AbstractParser<GetPlaylistKey>() {
      @java.lang.Override
      public GetPlaylistKey parsePartialFrom(
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

    public static com.google.protobuf.Parser<GetPlaylistKey> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GetPlaylistKey> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public GetPlaylistKeyOuterClass.GetPlaylistKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetPlaylistKey_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetPlaylistKey_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024GetPlaylistKey.proto\"j\n\016GetPlaylistKey" +
      "\022\030\n\013playlistKey\030\001 \001(\014H\000\210\001\001\022\033\n\016playlistKe" +
      "yArr\030\006 \001(\014H\001\210\001\001B\016\n\014_playlistKeyB\021\n\017_play" +
      "listKeyArrb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_GetPlaylistKey_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GetPlaylistKey_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetPlaylistKey_descriptor,
        new java.lang.String[] { "PlaylistKey", "PlaylistKeyArr", "PlaylistKey", "PlaylistKeyArr", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
