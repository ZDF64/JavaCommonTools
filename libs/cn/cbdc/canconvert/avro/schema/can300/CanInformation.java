/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package cn.cbdc.canconvert.avro.schema.can300;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class CanInformation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 527369425749747332L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CanInformation\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300\",\"fields\":[{\"name\":\"canId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"dataLengthAfterCompression\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"time\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"outsideUseDataMap\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}],\"default\":null}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CanInformation> ENCODER =
      new BinaryMessageEncoder<CanInformation>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CanInformation> DECODER =
      new BinaryMessageDecoder<CanInformation>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<CanInformation> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<CanInformation> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<CanInformation>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this CanInformation to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a CanInformation from a ByteBuffer. */
  public static CanInformation fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence canId;
  @Deprecated public java.lang.Long dataLengthAfterCompression;
  @Deprecated public java.lang.CharSequence time;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CanInformation() {}

  /**
   * All-args constructor.
   * @param canId The new value for canId
   * @param dataLengthAfterCompression The new value for dataLengthAfterCompression
   * @param time The new value for time
   * @param outsideUseDataMap The new value for outsideUseDataMap
   */
  public CanInformation(java.lang.CharSequence canId, java.lang.Long dataLengthAfterCompression, java.lang.CharSequence time, java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap) {
    this.canId = canId;
    this.dataLengthAfterCompression = dataLengthAfterCompression;
    this.time = time;
    this.outsideUseDataMap = outsideUseDataMap;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return canId;
    case 1: return dataLengthAfterCompression;
    case 2: return time;
    case 3: return outsideUseDataMap;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: canId = (java.lang.CharSequence)value$; break;
    case 1: dataLengthAfterCompression = (java.lang.Long)value$; break;
    case 2: time = (java.lang.CharSequence)value$; break;
    case 3: outsideUseDataMap = (java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'canId' field.
   * @return The value of the 'canId' field.
   */
  public java.lang.CharSequence getCanId() {
    return canId;
  }

  /**
   * Sets the value of the 'canId' field.
   * @param value the value to set.
   */
  public void setCanId(java.lang.CharSequence value) {
    this.canId = value;
  }

  /**
   * Gets the value of the 'dataLengthAfterCompression' field.
   * @return The value of the 'dataLengthAfterCompression' field.
   */
  public java.lang.Long getDataLengthAfterCompression() {
    return dataLengthAfterCompression;
  }

  /**
   * Sets the value of the 'dataLengthAfterCompression' field.
   * @param value the value to set.
   */
  public void setDataLengthAfterCompression(java.lang.Long value) {
    this.dataLengthAfterCompression = value;
  }

  /**
   * Gets the value of the 'time' field.
   * @return The value of the 'time' field.
   */
  public java.lang.CharSequence getTime() {
    return time;
  }

  /**
   * Sets the value of the 'time' field.
   * @param value the value to set.
   */
  public void setTime(java.lang.CharSequence value) {
    this.time = value;
  }

  /**
   * Gets the value of the 'outsideUseDataMap' field.
   * @return The value of the 'outsideUseDataMap' field.
   */
  public java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getOutsideUseDataMap() {
    return outsideUseDataMap;
  }

  /**
   * Sets the value of the 'outsideUseDataMap' field.
   * @param value the value to set.
   */
  public void setOutsideUseDataMap(java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
    this.outsideUseDataMap = value;
  }

  /**
   * Creates a new CanInformation RecordBuilder.
   * @return A new CanInformation RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder newBuilder() {
    return new cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder();
  }

  /**
   * Creates a new CanInformation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CanInformation RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder other) {
    return new cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder(other);
  }

  /**
   * Creates a new CanInformation RecordBuilder by copying an existing CanInformation instance.
   * @param other The existing instance to copy.
   * @return A new CanInformation RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.CanInformation other) {
    return new cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder(other);
  }

  /**
   * RecordBuilder for CanInformation instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CanInformation>
    implements org.apache.avro.data.RecordBuilder<CanInformation> {

    private java.lang.CharSequence canId;
    private java.lang.Long dataLengthAfterCompression;
    private java.lang.CharSequence time;
    private java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.canId)) {
        this.canId = data().deepCopy(fields()[0].schema(), other.canId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.dataLengthAfterCompression)) {
        this.dataLengthAfterCompression = data().deepCopy(fields()[1].schema(), other.dataLengthAfterCompression);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.time)) {
        this.time = data().deepCopy(fields()[2].schema(), other.time);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.outsideUseDataMap)) {
        this.outsideUseDataMap = data().deepCopy(fields()[3].schema(), other.outsideUseDataMap);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing CanInformation instance
     * @param other The existing instance to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.CanInformation other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.canId)) {
        this.canId = data().deepCopy(fields()[0].schema(), other.canId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.dataLengthAfterCompression)) {
        this.dataLengthAfterCompression = data().deepCopy(fields()[1].schema(), other.dataLengthAfterCompression);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.time)) {
        this.time = data().deepCopy(fields()[2].schema(), other.time);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.outsideUseDataMap)) {
        this.outsideUseDataMap = data().deepCopy(fields()[3].schema(), other.outsideUseDataMap);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'canId' field.
      * @return The value.
      */
    public java.lang.CharSequence getCanId() {
      return canId;
    }

    /**
      * Sets the value of the 'canId' field.
      * @param value The value of 'canId'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder setCanId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.canId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'canId' field has been set.
      * @return True if the 'canId' field has been set, false otherwise.
      */
    public boolean hasCanId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'canId' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder clearCanId() {
      canId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'dataLengthAfterCompression' field.
      * @return The value.
      */
    public java.lang.Long getDataLengthAfterCompression() {
      return dataLengthAfterCompression;
    }

    /**
      * Sets the value of the 'dataLengthAfterCompression' field.
      * @param value The value of 'dataLengthAfterCompression'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder setDataLengthAfterCompression(java.lang.Long value) {
      validate(fields()[1], value);
      this.dataLengthAfterCompression = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'dataLengthAfterCompression' field has been set.
      * @return True if the 'dataLengthAfterCompression' field has been set, false otherwise.
      */
    public boolean hasDataLengthAfterCompression() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'dataLengthAfterCompression' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder clearDataLengthAfterCompression() {
      dataLengthAfterCompression = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'time' field.
      * @return The value.
      */
    public java.lang.CharSequence getTime() {
      return time;
    }

    /**
      * Sets the value of the 'time' field.
      * @param value The value of 'time'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder setTime(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.time = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'time' field has been set.
      * @return True if the 'time' field has been set, false otherwise.
      */
    public boolean hasTime() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'time' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder clearTime() {
      time = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'outsideUseDataMap' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getOutsideUseDataMap() {
      return outsideUseDataMap;
    }

    /**
      * Sets the value of the 'outsideUseDataMap' field.
      * @param value The value of 'outsideUseDataMap'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder setOutsideUseDataMap(java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
      validate(fields()[3], value);
      this.outsideUseDataMap = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'outsideUseDataMap' field has been set.
      * @return True if the 'outsideUseDataMap' field has been set, false otherwise.
      */
    public boolean hasOutsideUseDataMap() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'outsideUseDataMap' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.CanInformation.Builder clearOutsideUseDataMap() {
      outsideUseDataMap = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CanInformation build() {
      try {
        CanInformation record = new CanInformation();
        record.canId = fieldSetFlags()[0] ? this.canId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.dataLengthAfterCompression = fieldSetFlags()[1] ? this.dataLengthAfterCompression : (java.lang.Long) defaultValue(fields()[1]);
        record.time = fieldSetFlags()[2] ? this.time : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.outsideUseDataMap = fieldSetFlags()[3] ? this.outsideUseDataMap : (java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CanInformation>
    WRITER$ = (org.apache.avro.io.DatumWriter<CanInformation>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CanInformation>
    READER$ = (org.apache.avro.io.DatumReader<CanInformation>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
