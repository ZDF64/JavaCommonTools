/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class UpdCondition extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7368049808440950135L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UpdCondition\",\"namespace\":\"src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300\",\"fields\":[{\"name\":\"updConditionId\",\"type\":[\"null\",\"long\"],\"default\":null}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<UpdCondition> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UpdCondition> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<UpdCondition> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<UpdCondition> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<UpdCondition> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this UpdCondition to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a UpdCondition from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a UpdCondition instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static UpdCondition fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.Long updConditionId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UpdCondition() {}

  /**
   * All-args constructor.
   * @param updConditionId The new value for updConditionId
   */
  public UpdCondition(java.lang.Long updConditionId) {
    this.updConditionId = updConditionId;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return updConditionId;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: updConditionId = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'updConditionId' field.
   * @return The value of the 'updConditionId' field.
   */
  public java.lang.Long getUpdConditionId() {
    return updConditionId;
  }


  /**
   * Sets the value of the 'updConditionId' field.
   * @param value the value to set.
   */
  public void setUpdConditionId(java.lang.Long value) {
    this.updConditionId = value;
  }

  /**
   * Creates a new UpdCondition RecordBuilder.
   * @return A new UpdCondition RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder newBuilder() {
    return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder();
  }

  /**
   * Creates a new UpdCondition RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UpdCondition RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder newBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder other) {
    if (other == null) {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder();
    } else {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder(other);
    }
  }

  /**
   * Creates a new UpdCondition RecordBuilder by copying an existing UpdCondition instance.
   * @param other The existing instance to copy.
   * @return A new UpdCondition RecordBuilder
   */
  public static src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder newBuilder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition other) {
    if (other == null) {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder();
    } else {
      return new src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder(other);
    }
  }

  /**
   * RecordBuilder for UpdCondition instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UpdCondition>
    implements org.apache.avro.data.RecordBuilder<UpdCondition> {

    private java.lang.Long updConditionId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.updConditionId)) {
        this.updConditionId = data().deepCopy(fields()[0].schema(), other.updConditionId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing UpdCondition instance
     * @param other The existing instance to copy.
     */
    private Builder(src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.updConditionId)) {
        this.updConditionId = data().deepCopy(fields()[0].schema(), other.updConditionId);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'updConditionId' field.
      * @return The value.
      */
    public java.lang.Long getUpdConditionId() {
      return updConditionId;
    }


    /**
      * Sets the value of the 'updConditionId' field.
      * @param value The value of 'updConditionId'.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder setUpdConditionId(java.lang.Long value) {
      validate(fields()[0], value);
      this.updConditionId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'updConditionId' field has been set.
      * @return True if the 'updConditionId' field has been set, false otherwise.
      */
    public boolean hasUpdConditionId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'updConditionId' field.
      * @return This builder.
      */
    public src.main.java.zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition.Builder clearUpdConditionId() {
      updConditionId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UpdCondition build() {
      try {
        UpdCondition record = new UpdCondition();
        record.updConditionId = fieldSetFlags()[0] ? this.updConditionId : (java.lang.Long) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UpdCondition>
    WRITER$ = (org.apache.avro.io.DatumWriter<UpdCondition>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UpdCondition>
    READER$ = (org.apache.avro.io.DatumReader<UpdCondition>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.updConditionId == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.updConditionId);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.updConditionId = null;
      } else {
        this.updConditionId = in.readLong();
      }

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.updConditionId = null;
          } else {
            this.updConditionId = in.readLong();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










