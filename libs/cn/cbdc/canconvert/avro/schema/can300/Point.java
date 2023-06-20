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
public class Point extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -642430344083099329L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Point\",\"namespace\":\"cn.cbdc.canconvert.avro.schema.can300\",\"fields\":[{\"name\":\"latitude\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"longitude\",\"type\":[\"null\",\"double\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Point> ENCODER =
      new BinaryMessageEncoder<Point>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Point> DECODER =
      new BinaryMessageDecoder<Point>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Point> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Point> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Point>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Point to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Point from a ByteBuffer. */
  public static Point fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Double latitude;
  @Deprecated public java.lang.Double longitude;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Point() {}

  /**
   * All-args constructor.
   * @param latitude The new value for latitude
   * @param longitude The new value for longitude
   */
  public Point(java.lang.Double latitude, java.lang.Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return latitude;
    case 1: return longitude;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: latitude = (java.lang.Double)value$; break;
    case 1: longitude = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'latitude' field.
   * @return The value of the 'latitude' field.
   */
  public java.lang.Double getLatitude() {
    return latitude;
  }

  /**
   * Sets the value of the 'latitude' field.
   * @param value the value to set.
   */
  public void setLatitude(java.lang.Double value) {
    this.latitude = value;
  }

  /**
   * Gets the value of the 'longitude' field.
   * @return The value of the 'longitude' field.
   */
  public java.lang.Double getLongitude() {
    return longitude;
  }

  /**
   * Sets the value of the 'longitude' field.
   * @param value the value to set.
   */
  public void setLongitude(java.lang.Double value) {
    this.longitude = value;
  }

  /**
   * Creates a new Point RecordBuilder.
   * @return A new Point RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Point.Builder newBuilder() {
    return new cn.cbdc.canconvert.avro.schema.can300.Point.Builder();
  }

  /**
   * Creates a new Point RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Point RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Point.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.Point.Builder other) {
    return new cn.cbdc.canconvert.avro.schema.can300.Point.Builder(other);
  }

  /**
   * Creates a new Point RecordBuilder by copying an existing Point instance.
   * @param other The existing instance to copy.
   * @return A new Point RecordBuilder
   */
  public static cn.cbdc.canconvert.avro.schema.can300.Point.Builder newBuilder(cn.cbdc.canconvert.avro.schema.can300.Point other) {
    return new cn.cbdc.canconvert.avro.schema.can300.Point.Builder(other);
  }

  /**
   * RecordBuilder for Point instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Point>
    implements org.apache.avro.data.RecordBuilder<Point> {

    private java.lang.Double latitude;
    private java.lang.Double longitude;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.Point.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.latitude)) {
        this.latitude = data().deepCopy(fields()[0].schema(), other.latitude);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.longitude)) {
        this.longitude = data().deepCopy(fields()[1].schema(), other.longitude);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Point instance
     * @param other The existing instance to copy.
     */
    private Builder(cn.cbdc.canconvert.avro.schema.can300.Point other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.latitude)) {
        this.latitude = data().deepCopy(fields()[0].schema(), other.latitude);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.longitude)) {
        this.longitude = data().deepCopy(fields()[1].schema(), other.longitude);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'latitude' field.
      * @return The value.
      */
    public java.lang.Double getLatitude() {
      return latitude;
    }

    /**
      * Sets the value of the 'latitude' field.
      * @param value The value of 'latitude'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Point.Builder setLatitude(java.lang.Double value) {
      validate(fields()[0], value);
      this.latitude = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'latitude' field has been set.
      * @return True if the 'latitude' field has been set, false otherwise.
      */
    public boolean hasLatitude() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'latitude' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Point.Builder clearLatitude() {
      latitude = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'longitude' field.
      * @return The value.
      */
    public java.lang.Double getLongitude() {
      return longitude;
    }

    /**
      * Sets the value of the 'longitude' field.
      * @param value The value of 'longitude'.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Point.Builder setLongitude(java.lang.Double value) {
      validate(fields()[1], value);
      this.longitude = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'longitude' field has been set.
      * @return True if the 'longitude' field has been set, false otherwise.
      */
    public boolean hasLongitude() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'longitude' field.
      * @return This builder.
      */
    public cn.cbdc.canconvert.avro.schema.can300.Point.Builder clearLongitude() {
      longitude = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Point build() {
      try {
        Point record = new Point();
        record.latitude = fieldSetFlags()[0] ? this.latitude : (java.lang.Double) defaultValue(fields()[0]);
        record.longitude = fieldSetFlags()[1] ? this.longitude : (java.lang.Double) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Point>
    WRITER$ = (org.apache.avro.io.DatumWriter<Point>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Point>
    READER$ = (org.apache.avro.io.DatumReader<Point>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}