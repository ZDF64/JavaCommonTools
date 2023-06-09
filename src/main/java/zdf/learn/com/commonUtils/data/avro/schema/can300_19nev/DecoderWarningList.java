/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package zdf.learn.com.commonUtils.data.avro.schema.can300_19nev;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class DecoderWarningList extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1411993559287793325L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DecoderWarningList\",\"namespace\":\"zdf.learn.com.commonUtils.data.avro.schema.can300_19nev\",\"fields\":[{\"name\":\"message\",\"type\":\"string\"},{\"name\":\"type\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"DecodeWarningStatus\",\"symbols\":[\"OUT_OF_RANGE\",\"ABNORMAL\",\"INVALID\",\"UNSETTLED\",\"INDETERMINATE\",\"UNIT_ERROR\",\"NULL_OR_INVALID\",\"VERSION_ERROR\",\"UNCORRECTION\",\"ABNORMAL_INVALID\",\"DEFAULT_NOT_SET\",\"CALCULATE_ERROR\"]}]}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<DecoderWarningList> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<DecoderWarningList> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<DecoderWarningList> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<DecoderWarningList> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<DecoderWarningList> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this DecoderWarningList to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a DecoderWarningList from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a DecoderWarningList instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static DecoderWarningList fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence message;
  private zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus type;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public DecoderWarningList() {}

  /**
   * All-args constructor.
   * @param message The new value for message
   * @param type The new value for type
   */
  public DecoderWarningList(java.lang.CharSequence message, zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus type) {
    this.message = message;
    this.type = type;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return message;
    case 1: return type;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: message = (java.lang.CharSequence)value$; break;
    case 1: type = (zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public java.lang.CharSequence getMessage() {
    return message;
  }


  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.CharSequence value) {
    this.message = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus value) {
    this.type = value;
  }

  /**
   * Creates a new DecoderWarningList RecordBuilder.
   * @return A new DecoderWarningList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder newBuilder() {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder();
  }

  /**
   * Creates a new DecoderWarningList RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new DecoderWarningList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder other) {
    if (other == null) {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder();
    } else {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder(other);
    }
  }

  /**
   * Creates a new DecoderWarningList RecordBuilder by copying an existing DecoderWarningList instance.
   * @param other The existing instance to copy.
   * @return A new DecoderWarningList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList other) {
    if (other == null) {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder();
    } else {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder(other);
    }
  }

  /**
   * RecordBuilder for DecoderWarningList instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DecoderWarningList>
    implements org.apache.avro.data.RecordBuilder<DecoderWarningList> {

    private java.lang.CharSequence message;
    private zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus type;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.message)) {
        this.message = data().deepCopy(fields()[0].schema(), other.message);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing DecoderWarningList instance
     * @param other The existing instance to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.message)) {
        this.message = data().deepCopy(fields()[0].schema(), other.message);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public java.lang.CharSequence getMessage() {
      return message;
    }


    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder setMessage(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.message = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder clearMessage() {
      message = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder setType(zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DecoderWarningList build() {
      try {
        DecoderWarningList record = new DecoderWarningList();
        record.message = fieldSetFlags()[0] ? this.message : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<DecoderWarningList>
    WRITER$ = (org.apache.avro.io.DatumWriter<DecoderWarningList>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<DecoderWarningList>
    READER$ = (org.apache.avro.io.DatumReader<DecoderWarningList>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.message);

    if (this.type == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeEnum(this.type.ordinal());
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.message = in.readString(this.message instanceof Utf8 ? (Utf8)this.message : null);

      if (in.readIndex() != 1) {
        in.readNull();
        this.type = null;
      } else {
        this.type = zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus.values()[in.readEnum()];
      }

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.message = in.readString(this.message instanceof Utf8 ? (Utf8)this.message : null);
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.type = null;
          } else {
            this.type = zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus.values()[in.readEnum()];
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










