// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: model.proto at 76:1
package com.tevinjeffrey.rutgersct.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.squareup.wire.AndroidMessage;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;

import java.io.IOException;

import okio.ByteString;

public final class Instructor extends AndroidMessage<Instructor, Instructor.Builder> {
  public static final ProtoAdapter<Instructor> ADAPTER = new ProtoAdapter_Instructor();

  public static final Parcelable.Creator<Instructor> CREATOR = AndroidMessage.newCreator(ADAPTER);
  public static final Long DEFAULT_ID = 0L;
  public static final Long DEFAULT_SECTION_ID = 0L;
  public static final String DEFAULT_NAME = "";
  public static final Integer DEFAULT_INDEX = 0;
  private static final long serialVersionUID = 0L;
  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long id;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long section_id;

  @WireField(
      tag = 3,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @Nullable
  public final String name;

  @WireField(
      tag = 4,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  @Nullable
  public final Integer index;

  public Instructor(
      @Nullable Long id,
      @Nullable Long section_id,
      @Nullable String name,
      @Nullable Integer index) {
    this(id, section_id, name, index, ByteString.EMPTY);
  }

  public Instructor(
      @Nullable Long id,
      @Nullable Long section_id,
      @Nullable String name,
      @Nullable Integer index,
      ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.id = id;
    this.section_id = section_id;
    this.name = name;
    this.index = index;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Instructor)) {
      return false;
    }
    Instructor o = (Instructor) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(id, o.id)
        && Internal.equals(section_id, o.section_id)
        && Internal.equals(name, o.name)
        && Internal.equals(index, o.index);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (id != null ? id.hashCode() : 0);
      result = result * 37 + (section_id != null ? section_id.hashCode() : 0);
      result = result * 37 + (name != null ? name.hashCode() : 0);
      result = result * 37 + (index != null ? index.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.id = id;
    builder.section_id = section_id;
    builder.name = name;
    builder.index = index;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (id != null) {
      builder.append(", id=").append(id);
    }
    if (section_id != null) {
      builder.append(", section_id=").append(section_id);
    }
    if (name != null) {
      builder.append(", name=").append(name);
    }
    if (index != null) {
      builder.append(", index=").append(index);
    }
    return builder.replace(0, 2, "Instructor{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<Instructor, Builder> {
    public Long id;

    public Long section_id;

    public String name;

    public Integer index;

    public Builder() {
    }

    @Override
    public Instructor build() {
      return new Instructor(id, section_id, name, index, super.buildUnknownFields());
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder index(Integer index) {
      this.index = index;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder section_id(Long section_id) {
      this.section_id = section_id;
      return this;
    }
  }

  private static final class ProtoAdapter_Instructor extends ProtoAdapter<Instructor> {
    ProtoAdapter_Instructor() {
      super(FieldEncoding.LENGTH_DELIMITED, Instructor.class);
    }

    @Override
    public Instructor decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1; ) {
        switch (tag) {
          case 1:
            builder.id(ProtoAdapter.INT64.decode(reader));
            break;
          case 2:
            builder.section_id(ProtoAdapter.INT64.decode(reader));
            break;
          case 3:
            builder.name(ProtoAdapter.STRING.decode(reader));
            break;
          case 4:
            builder.index(ProtoAdapter.INT32.decode(reader));
            break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public void encode(ProtoWriter writer, Instructor value) throws IOException {
      if (value.id != null) {
        ProtoAdapter.INT64.encodeWithTag(writer, 1, value.id);
      }
      if (value.section_id != null) {
        ProtoAdapter.INT64.encodeWithTag(writer, 2, value.section_id);
      }
      if (value.name != null) {
        ProtoAdapter.STRING.encodeWithTag(writer, 3, value.name);
      }
      if (value.index != null) {
        ProtoAdapter.INT32.encodeWithTag(writer, 4, value.index);
      }
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public int encodedSize(Instructor value) {
      return (value.id != null ? ProtoAdapter.INT64.encodedSizeWithTag(1, value.id) : 0)
          + (value.section_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(2, value.section_id)
                                      : 0)
          + (value.name != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value.name) : 0)
          + (value.index != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, value.index) : 0)
          + value.unknownFields().size();
    }

    @Override
    public Instructor redact(Instructor value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
