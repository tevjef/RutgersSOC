// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: model.proto at 101:1
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

public final class Registration extends AndroidMessage<Registration, Registration.Builder> {
  public static final ProtoAdapter<Registration> ADAPTER = new ProtoAdapter_Registration();

  public static final Parcelable.Creator<Registration> CREATOR = AndroidMessage.newCreator(ADAPTER);
  public static final Long DEFAULT_ID = 0L;
  public static final Long DEFAULT_UNIVERSITY_ID = 0L;
  public static final String DEFAULT_PERIOD = "";
  public static final Long DEFAULT_PERIOD_DATE = 0L;
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
  public final Long university_id;

  @WireField(
      tag = 3,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @Nullable
  public final String period;

  @WireField(
      tag = 4,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long period_date;

  public Registration(
      @Nullable Long id,
      @Nullable Long university_id,
      @Nullable String period,
      @Nullable Long period_date) {
    this(id, university_id, period, period_date, ByteString.EMPTY);
  }

  public Registration(
      @Nullable Long id,
      @Nullable Long university_id,
      @Nullable String period,
      @Nullable Long period_date,
      ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.id = id;
    this.university_id = university_id;
    this.period = period;
    this.period_date = period_date;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Registration)) {
      return false;
    }
    Registration o = (Registration) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(id, o.id)
        && Internal.equals(university_id, o.university_id)
        && Internal.equals(period, o.period)
        && Internal.equals(period_date, o.period_date);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (id != null ? id.hashCode() : 0);
      result = result * 37 + (university_id != null ? university_id.hashCode() : 0);
      result = result * 37 + (period != null ? period.hashCode() : 0);
      result = result * 37 + (period_date != null ? period_date.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.id = id;
    builder.university_id = university_id;
    builder.period = period;
    builder.period_date = period_date;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (id != null) {
      builder.append(", id=").append(id);
    }
    if (university_id != null) {
      builder.append(", university_id=").append(university_id);
    }
    if (period != null) {
      builder.append(", period=").append(period);
    }
    if (period_date != null) {
      builder.append(", period_date=").append(period_date);
    }
    return builder.replace(0, 2, "Registration{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<Registration, Builder> {
    public Long id;

    public Long university_id;

    public String period;

    public Long period_date;

    public Builder() {
    }

    @Override
    public Registration build() {
      return new Registration(id, university_id, period, period_date, super.buildUnknownFields());
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder period(String period) {
      this.period = period;
      return this;
    }

    public Builder period_date(Long period_date) {
      this.period_date = period_date;
      return this;
    }

    public Builder university_id(Long university_id) {
      this.university_id = university_id;
      return this;
    }
  }

  private static final class ProtoAdapter_Registration extends ProtoAdapter<Registration> {
    ProtoAdapter_Registration() {
      super(FieldEncoding.LENGTH_DELIMITED, Registration.class);
    }

    @Override
    public Registration decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1; ) {
        switch (tag) {
          case 1:
            builder.id(ProtoAdapter.INT64.decode(reader));
            break;
          case 2:
            builder.university_id(ProtoAdapter.INT64.decode(reader));
            break;
          case 3:
            builder.period(ProtoAdapter.STRING.decode(reader));
            break;
          case 4:
            builder.period_date(ProtoAdapter.INT64.decode(reader));
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
    public void encode(ProtoWriter writer, Registration value) throws IOException {
      if (value.id != null) {
        ProtoAdapter.INT64.encodeWithTag(writer, 1, value.id);
      }
      if (value.university_id != null) {
        ProtoAdapter.INT64.encodeWithTag(writer, 2, value.university_id);
      }
      if (value.period != null) {
        ProtoAdapter.STRING.encodeWithTag(writer, 3, value.period);
      }
      if (value.period_date != null) {
        ProtoAdapter.INT64.encodeWithTag(writer, 4, value.period_date);
      }
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public int encodedSize(Registration value) {
      return (value.id != null ? ProtoAdapter.INT64.encodedSizeWithTag(1, value.id) : 0)
          + (value.university_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(
          2,
          value.university_id
      ) : 0)
          + (value.period != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value.period) : 0)
          + (value.period_date != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value.period_date)
                                       : 0)
          + value.unknownFields().size();
    }

    @Override
    public Registration redact(Registration value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
