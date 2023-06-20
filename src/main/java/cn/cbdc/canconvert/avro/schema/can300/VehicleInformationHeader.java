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
public class VehicleInformationHeader extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -312430159293631687L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"VehicleInformationHeader\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300\",\"fields\":[{\"name\":\"dataCapacityNumber\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"timeZoneOffset\",\"type\":[\"null\",\"int\"],\"default\":null}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<VehicleInformationHeader> ENCODER =
      new BinaryMessageEncoder<VehicleInformationHeader>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<VehicleInformationHeader> DECODER =
      new BinaryMessageDecoder<VehicleInformationHeader>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<VehicleInformationHeader> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<VehicleInformationHeader> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<VehicleInformationHeader>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this VehicleInformationHeader to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a VehicleInformationHeader from a ByteBuffer. */
  public static VehicleInformationHeader fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Integer dataCapacityNumber;
  @Deprecated public java.lang.Integer timeZoneOffset;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public VehicleInformationHeader() {}

  /**
   * All-args constructor.
   * @param dataCapacityNumber The new value for dataCapacityNumber
   * @param timeZoneOffset The new value for timeZoneOffset
   */
  public VehicleInformationHeader(java.lang.Integer dataCapacityNumber, java.lang.Integer timeZoneOffset) {
    this.dataCapacityNumber = dataCapacityNumber;
    this.timeZoneOffset = timeZoneOffset;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return dataCapacityNumber;
    case 1: return timeZoneOffset;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: dataCapacityNumber = (java.lang.Integer)value$; break;
    case 1: timeZoneOffset = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'dataCapacityNumber' field.
   * @return The value of the 'dataCapacityNumber' field.
   */
  public java.lang.Integer getDataCapacityNumber() {
    return dataCapacityNumber;
  }

  /**
   * Sets the value of the 'dataCapacityNumber' field.
   * @param value the value to set.
   */
  public void setDataCapacityNumber(java.lang.Integer value) {
    this.dataCapacityNumber = value;
  }

  /**
   * Gets the value of the 'timeZoneOffset' field.
   * @return The value of the 'timeZoneOffset' field.
   */
  public java.lang.Integer getTimeZoneOffset() {
    return timeZoneOffset;
  }

  /**
   * Sets the value of the 'timeZoneOffset' field.
   * @param value the value to set.
   */
  public void setTimeZoneOffset(java.lang.Integer value) {
    this.timeZoneOffset = value;
  }

  /**
   * Creates a new VehicleInformationHeader RecordBuilder.
   * @return A new VehicleInformationHeader RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder newBuilder() {
    return new cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder();
  }

  /**
   * Creates a new VehicleInformationHeader RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new VehicleInformationHeader RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder other) {
    return new cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder(other);
  }

  /**
   * Creates a new VehicleInformationHeader RecordBuilder by copying an existing VehicleInformationHeader instance.
   * @param other The existing instance to copy.
   * @return A new VehicleInformationHeader RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader other) {
    return new cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder(other);
  }

  /**
   * RecordBuilder for VehicleInformationHeader instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<VehicleInformationHeader>
    implements org.apache.avro.data.RecordBuilder<VehicleInformationHeader> {

    private java.lang.Integer dataCapacityNumber;
    private java.lang.Integer timeZoneOffset;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.dataCapacityNumber)) {
        this.dataCapacityNumber = data().deepCopy(fields()[0].schema(), other.dataCapacityNumber);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeZoneOffset)) {
        this.timeZoneOffset = data().deepCopy(fields()[1].schema(), other.timeZoneOffset);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing VehicleInformationHeader instance
     * @param other The existing instance to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.dataCapacityNumber)) {
        this.dataCapacityNumber = data().deepCopy(fields()[0].schema(), other.dataCapacityNumber);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeZoneOffset)) {
        this.timeZoneOffset = data().deepCopy(fields()[1].schema(), other.timeZoneOffset);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'dataCapacityNumber' field.
      * @return The value.
      */
    public java.lang.Integer getDataCapacityNumber() {
      return dataCapacityNumber;
    }

    /**
      * Sets the value of the 'dataCapacityNumber' field.
      * @param value The value of 'dataCapacityNumber'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder setDataCapacityNumber(java.lang.Integer value) {
      validate(fields()[0], value);
      this.dataCapacityNumber = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'dataCapacityNumber' field has been set.
      * @return True if the 'dataCapacityNumber' field has been set, false otherwise.
      */
    public boolean hasDataCapacityNumber() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'dataCapacityNumber' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder clearDataCapacityNumber() {
      dataCapacityNumber = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'timeZoneOffset' field.
      * @return The value.
      */
    public java.lang.Integer getTimeZoneOffset() {
      return timeZoneOffset;
    }

    /**
      * Sets the value of the 'timeZoneOffset' field.
      * @param value The value of 'timeZoneOffset'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder setTimeZoneOffset(java.lang.Integer value) {
      validate(fields()[1], value);
      this.timeZoneOffset = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'timeZoneOffset' field has been set.
      * @return True if the 'timeZoneOffset' field has been set, false otherwise.
      */
    public boolean hasTimeZoneOffset() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'timeZoneOffset' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.VehicleInformationHeader.Builder clearTimeZoneOffset() {
      timeZoneOffset = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public VehicleInformationHeader build() {
      try {
        VehicleInformationHeader record = new VehicleInformationHeader();
        record.dataCapacityNumber = fieldSetFlags()[0] ? this.dataCapacityNumber : (java.lang.Integer) defaultValue(fields()[0]);
        record.timeZoneOffset = fieldSetFlags()[1] ? this.timeZoneOffset : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<VehicleInformationHeader>
    WRITER$ = (org.apache.avro.io.DatumWriter<VehicleInformationHeader>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<VehicleInformationHeader>
    READER$ = (org.apache.avro.io.DatumReader<VehicleInformationHeader>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}