// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: model.proto at 115:1
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

public final class Semester extends AndroidMessage<Semester, Semester.Builder> {
  public static final ProtoAdapter<Semester> ADAPTER = new ProtoAdapter_Semester();

  public static final Parcelable.Creator<Semester> CREATOR = AndroidMessage.newCreator(ADAPTER);
  public static final Integer DEFAULT_YEAR = 0;
  public static final String DEFAULT_SEASON = "";
  private static final long serialVersionUID = 0L;
  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  @Nullable
  public final Integer year;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @Nullable
  public final String season;

  public Semester(@Nullable Integer year, @Nullable String season) {
    this(year, season, ByteString.EMPTY);
  }

  public Semester(@Nullable Integer year, @Nullable String season, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.year = year;
    this.season = season;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Semester)) {
      return false;
    }
    Semester o = (Semester) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(year, o.year)
        && Internal.equals(season, o.season);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (year != null ? year.hashCode() : 0);
      result = result * 37 + (season != null ? season.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.year = year;
    builder.season = season;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (year != null) {
      builder.append(", year=").append(year);
    }
    if (season != null) {
      builder.append(", season=").append(season);
    }
    return builder.replace(0, 2, "Semester{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<Semester, Builder> {
    public Integer year;

    public String season;

    public Builder() {
    }

    @Override
    public Semester build() {
      return new Semester(year, season, super.buildUnknownFields());
    }

    public Builder season(String season) {
      this.season = season;
      return this;
    }

    public Builder year(Integer year) {
      this.year = year;
      return this;
    }
  }

  private static final class ProtoAdapter_Semester extends ProtoAdapter<Semester> {
    ProtoAdapter_Semester() {
      super(FieldEncoding.LENGTH_DELIMITED, Semester.class);
    }

    @Override
    public Semester decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1; ) {
        switch (tag) {
          case 1:
            builder.year(ProtoAdapter.INT32.decode(reader));
            break;
          case 2:
            builder.season(ProtoAdapter.STRING.decode(reader));
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
    public void encode(ProtoWriter writer, Semester value) throws IOException {
      if (value.year != null) {
        ProtoAdapter.INT32.encodeWithTag(writer, 1, value.year);
      }
      if (value.season != null) {
        ProtoAdapter.STRING.encodeWithTag(writer, 2, value.season);
      }
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public int encodedSize(Semester value) {
      return (value.year != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, value.year) : 0)
          + (value.season != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value.season) : 0)
          + value.unknownFields().size();
    }

    @Override
    public Semester redact(Semester value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
