// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: model.proto at 90:1
package com.tevinjeffrey.rutgersct.data.uctapi.model;

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
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class Metadata extends AndroidMessage<Metadata, Metadata.Builder> {
  public static final ProtoAdapter<Metadata> ADAPTER = new ProtoAdapter_Metadata();

  public static final Parcelable.Creator<Metadata> CREATOR = AndroidMessage.newCreator(ADAPTER);

  private static final long serialVersionUID = 0L;

  public static final Long DEFAULT_ID = 0L;

  public static final Long DEFAULT_UNIVERSITY_ID = 0L;

  public static final Long DEFAULT_SUBJECT_ID = 0L;

  public static final Long DEFAULT_COURSE_ID = 0L;

  public static final Long DEFAULT_SECTION_ID = 0L;

  public static final Long DEFAULT_MEETING_ID = 0L;

  public static final String DEFAULT_TITLE = "";

  public static final String DEFAULT_CONTENT = "";

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
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long subject_id;

  @WireField(
      tag = 4,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long course_id;

  @WireField(
      tag = 5,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long section_id;

  @WireField(
      tag = 6,
      adapter = "com.squareup.wire.ProtoAdapter#INT64"
  )
  @Nullable
  public final Long meeting_id;

  @WireField(
      tag = 7,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @Nullable
  public final String title;

  @WireField(
      tag = 8,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @Nullable
  public final String content;

  public Metadata(@Nullable Long id, @Nullable Long university_id, @Nullable Long subject_id, @Nullable Long course_id, @Nullable Long section_id, @Nullable Long meeting_id, @Nullable String title, @Nullable String content) {
    this(id, university_id, subject_id, course_id, section_id, meeting_id, title, content, ByteString.EMPTY);
  }

  public Metadata(@Nullable Long id, @Nullable Long university_id, @Nullable Long subject_id, @Nullable Long course_id, @Nullable Long section_id, @Nullable Long meeting_id, @Nullable String title, @Nullable String content, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.id = id;
    this.university_id = university_id;
    this.subject_id = subject_id;
    this.course_id = course_id;
    this.section_id = section_id;
    this.meeting_id = meeting_id;
    this.title = title;
    this.content = content;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.id = id;
    builder.university_id = university_id;
    builder.subject_id = subject_id;
    builder.course_id = course_id;
    builder.section_id = section_id;
    builder.meeting_id = meeting_id;
    builder.title = title;
    builder.content = content;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Metadata)) return false;
    Metadata o = (Metadata) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(id, o.id)
        && Internal.equals(university_id, o.university_id)
        && Internal.equals(subject_id, o.subject_id)
        && Internal.equals(course_id, o.course_id)
        && Internal.equals(section_id, o.section_id)
        && Internal.equals(meeting_id, o.meeting_id)
        && Internal.equals(title, o.title)
        && Internal.equals(content, o.content);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (id != null ? id.hashCode() : 0);
      result = result * 37 + (university_id != null ? university_id.hashCode() : 0);
      result = result * 37 + (subject_id != null ? subject_id.hashCode() : 0);
      result = result * 37 + (course_id != null ? course_id.hashCode() : 0);
      result = result * 37 + (section_id != null ? section_id.hashCode() : 0);
      result = result * 37 + (meeting_id != null ? meeting_id.hashCode() : 0);
      result = result * 37 + (title != null ? title.hashCode() : 0);
      result = result * 37 + (content != null ? content.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (id != null) builder.append(", id=").append(id);
    if (university_id != null) builder.append(", university_id=").append(university_id);
    if (subject_id != null) builder.append(", subject_id=").append(subject_id);
    if (course_id != null) builder.append(", course_id=").append(course_id);
    if (section_id != null) builder.append(", section_id=").append(section_id);
    if (meeting_id != null) builder.append(", meeting_id=").append(meeting_id);
    if (title != null) builder.append(", title=").append(title);
    if (content != null) builder.append(", content=").append(content);
    return builder.replace(0, 2, "Metadata{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<Metadata, Builder> {
    public Long id;

    public Long university_id;

    public Long subject_id;

    public Long course_id;

    public Long section_id;

    public Long meeting_id;

    public String title;

    public String content;

    public Builder() {
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder university_id(Long university_id) {
      this.university_id = university_id;
      return this;
    }

    public Builder subject_id(Long subject_id) {
      this.subject_id = subject_id;
      return this;
    }

    public Builder course_id(Long course_id) {
      this.course_id = course_id;
      return this;
    }

    public Builder section_id(Long section_id) {
      this.section_id = section_id;
      return this;
    }

    public Builder meeting_id(Long meeting_id) {
      this.meeting_id = meeting_id;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    @Override
    public Metadata build() {
      return new Metadata(id, university_id, subject_id, course_id, section_id, meeting_id, title, content, super.buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_Metadata extends ProtoAdapter<Metadata> {
    ProtoAdapter_Metadata() {
      super(FieldEncoding.LENGTH_DELIMITED, Metadata.class);
    }

    @Override
    public int encodedSize(Metadata value) {
      return (value.id != null ? ProtoAdapter.INT64.encodedSizeWithTag(1, value.id) : 0)
          + (value.university_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(2, value.university_id) : 0)
          + (value.subject_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value.subject_id) : 0)
          + (value.course_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value.course_id) : 0)
          + (value.section_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(5, value.section_id) : 0)
          + (value.meeting_id != null ? ProtoAdapter.INT64.encodedSizeWithTag(6, value.meeting_id) : 0)
          + (value.title != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, value.title) : 0)
          + (value.content != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, value.content) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, Metadata value) throws IOException {
      if (value.id != null) ProtoAdapter.INT64.encodeWithTag(writer, 1, value.id);
      if (value.university_id != null) ProtoAdapter.INT64.encodeWithTag(writer, 2, value.university_id);
      if (value.subject_id != null) ProtoAdapter.INT64.encodeWithTag(writer, 3, value.subject_id);
      if (value.course_id != null) ProtoAdapter.INT64.encodeWithTag(writer, 4, value.course_id);
      if (value.section_id != null) ProtoAdapter.INT64.encodeWithTag(writer, 5, value.section_id);
      if (value.meeting_id != null) ProtoAdapter.INT64.encodeWithTag(writer, 6, value.meeting_id);
      if (value.title != null) ProtoAdapter.STRING.encodeWithTag(writer, 7, value.title);
      if (value.content != null) ProtoAdapter.STRING.encodeWithTag(writer, 8, value.content);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public Metadata decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.id(ProtoAdapter.INT64.decode(reader)); break;
          case 2: builder.university_id(ProtoAdapter.INT64.decode(reader)); break;
          case 3: builder.subject_id(ProtoAdapter.INT64.decode(reader)); break;
          case 4: builder.course_id(ProtoAdapter.INT64.decode(reader)); break;
          case 5: builder.section_id(ProtoAdapter.INT64.decode(reader)); break;
          case 6: builder.meeting_id(ProtoAdapter.INT64.decode(reader)); break;
          case 7: builder.title(ProtoAdapter.STRING.decode(reader)); break;
          case 8: builder.content(ProtoAdapter.STRING.decode(reader)); break;
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
    public Metadata redact(Metadata value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}